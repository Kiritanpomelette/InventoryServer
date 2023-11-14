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
  public ResponseEntity<Map<String, Object>> getLatestDailyStatistics(@RequestParam int productId) {
    List<Object[]> results = treasurerRepository.getDailyStatistics(productId);
    if (results.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    Object[] latestResult = results.get(0);
    Map<String, Object> latestStatistics = new HashMap<>();
    latestStatistics.put("date", latestResult[0]);
    latestStatistics.put("total", latestResult[1]);

    return new ResponseEntity<>(latestStatistics, HttpStatus.OK);
  }


  //週統計API
  @GetMapping("/weeklyStatistics")
  public ResponseEntity<Map<String, Object>> getLatestWeeklyStatisticsByProductId(@RequestParam int productId) {
    List<Object[]> results = treasurerRepository.getWeeklyStatisticsByProductId(productId);
    if (results.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    Map<String, Object> latestStatistics = new LinkedHashMap<>();
    Object[] result = results.get(0);
    Date startDate = (Date) result[1];
    Date endDate = (Date) result[2];
    long total = (long) result[3];

    latestStatistics.put("start_date", startDate.toString());
    latestStatistics.put("end_date", endDate.toString());
    latestStatistics.put("total", total);

    return new ResponseEntity<>(latestStatistics, HttpStatus.OK);
  }

  //月統計API
  @GetMapping("/monthlyStatistics")
  public ResponseEntity<Map<String, Object>> getLatestMonthlyStatistics(@RequestParam int productId) {
    List<Object[]> results = treasurerRepository.getMonthlyStatistics(productId);
    if (results.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    Map<String, Object> latestStatistics = new HashMap<>();
    Object[] latestResult = results.get(0);
    latestStatistics.put("month", latestResult[0]);
    latestStatistics.put("total", latestResult[1]);

    return new ResponseEntity<>(latestStatistics, HttpStatus.OK);
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
