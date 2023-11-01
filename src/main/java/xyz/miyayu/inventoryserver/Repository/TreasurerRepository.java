package xyz.miyayu.inventoryserver.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import xyz.miyayu.inventoryserver.Entity.Treasurer;

public interface TreasurerRepository extends CrudRepository<Treasurer, Integer> {
  Optional<Iterable<Treasurer>> findByProductId(Integer productId);
}
