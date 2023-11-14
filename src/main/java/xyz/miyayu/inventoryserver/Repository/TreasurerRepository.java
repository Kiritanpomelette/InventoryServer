package xyz.miyayu.inventoryserver.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import xyz.miyayu.inventoryserver.Entity.Treasurer;

public interface TreasurerRepository extends CrudRepository<Treasurer, Integer> {
  Optional<Iterable<Treasurer>> findByProductId(long productId);

  // 月ごとの統計データを取得（最新のデータのみ）
  @Query("SELECT MONTH(t.date) AS month, SUM(t.count) AS total FROM Treasurer t WHERE t.productId = :productId GROUP BY MONTH(t.date) ORDER BY MONTH(t.date) DESC LIMIT 1")
  List<Object[]> getMonthlyStatistics(@Param("productId") int productId);

  // 日にちごとの統計データを取得（最新のデータのみ）
  @Query("SELECT DATE(t.date) AS date, SUM(t.count) AS total FROM Treasurer t WHERE t.productId = :productId GROUP BY DATE(t.date) ORDER BY DATE(t.date) DESC LIMIT 1")
  List<Object[]> getDailyStatistics(@Param("productId") int productId);

  // 週ごとの統計データを取得（最新のデータのみ）
  @Query("SELECT WEEK(t.date) AS week, MIN(t.date) AS start_date, MAX(t.date) AS end_date, SUM(t.count) AS total FROM Treasurer t WHERE t.productId = :productId GROUP BY WEEK(t.date) ORDER BY WEEK(t.date) DESC LIMIT 1")
  List<Object[]> getWeeklyStatisticsByProductId(@Param("productId") int productId);
}
