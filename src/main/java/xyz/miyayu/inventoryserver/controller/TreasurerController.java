package xyz.miyayu.inventoryserver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.miyayu.inventoryserver.Entity.Treasurer;
import xyz.miyayu.inventoryserver.Repository.TreasurerRepository;

@RestController
@RequestMapping("/treasurer")
public class TreasurerController {
  @Autowired
  private TreasurerRepository treasurerRepository;

  @GetMapping("")
  public Iterable<Treasurer> getAllTreasurer() {
    return treasurerRepository.findAll();
  }

  //Id検索API
  @GetMapping("/selectID")
  public Iterable<Treasurer> getTreasurerById(@RequestParam int productId) {
    // Optionalを使用してエンティティを取得 エンティティが存在しない場合はnullを返すか、例外をスロー
    return treasurerRepository.findByProductId(productId).orElse(null);
  }

  //日にち統計API
  @GetMapping("/dailyStatistics")
  public ResponseEntity<List<Map<String, Object>>> getDailyStatistics(@RequestParam int productId) {
    List<Object[]> results = treasurerRepository.getDailyStatistics(productId);
    List<Map<String, Object>> statisticsList = new ArrayList<>();

    for (Object[] result : results) {
      Map<String, Object> statistics = new HashMap<>();
      statistics.put("date", result[0]);
      statistics.put("total", result[1]);
      statisticsList.add(statistics);
    }

    return new ResponseEntity<>(statisticsList, HttpStatus.OK);
  }

  //週統計API
  @GetMapping("/weeklyStatistics")
  public ResponseEntity<List<Map<String, Object>>> getWeeklyStatisticsByProductId(@RequestParam int productId) {
    List<Object[]> results = treasurerRepository.getWeeklyStatisticsByProductId(productId);
    List<Map<String, Object>> statisticsList = new ArrayList<>();

    for (Object[] result : results) {
      Map<String, Object> statistics = new LinkedHashMap<>();
      Date startDate = (Date) result[1];
      Date endDate = (Date) result[2];
      long total = (long) result[3];

      statistics.put("start_date", startDate.toString());
      statistics.put("end_date", endDate.toString());
      statistics.put("total", total);

      statisticsList.add(statistics);
    }

    return new ResponseEntity<>(statisticsList, HttpStatus.OK);
  }

  //月統計API
  @GetMapping("/monthlyStatistics")
  public ResponseEntity<List<Map<String, Object>>> getMonthlyStatistics(@RequestParam int productId) {
    List<Object[]> results = treasurerRepository.getMonthlyStatistics(productId);
    List<Map<String, Object>> statisticsList = new ArrayList<>();

    for (Object[] result : results) {
      Map<String, Object> statistics = new HashMap<>();
      statistics.put("month", result[0]);
      statistics.put("total", result[1]);
      statisticsList.add(statistics);
    }

    return new ResponseEntity<>(statisticsList, HttpStatus.OK);
  }

  //追加API
  @PostMapping("")
  public @ResponseBody
  String addNewTreasurer(@RequestParam int productId, int managerId, int count) {
    Treasurer n = new Treasurer();
    n.setProductId(managerId);
    n.setManagerId(productId);
    n.setCount(count);
    treasurerRepository.save(n);
    return "DataSaved";
  }
}
