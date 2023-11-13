package xyz.miyayu.inventoryserver.controller;

import java.util.Collections;
import java.util.HashMap;
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

  //統計API実装
  @SuppressWarnings("checkstyle:Indentation")
  @GetMapping("/summary")
  public ResponseEntity<Map<String, Integer>>
  getStatisticsByProductId(@RequestParam int productId) {
    Map<String, Integer> statistics = new HashMap<>();

    //指定された商品IDのデータを取得
    Iterable<Treasurer> treasurerList =
        treasurerRepository.findByProductId(productId).orElse(Collections.emptyList());

    //Countカラムの統計を計算
    int totalCount = 0;

    for (Treasurer treasurer : treasurerList) {
      long count = treasurer.getCount();
      totalCount += (int) count;
    }

    statistics.put("total", totalCount);

    return new ResponseEntity<>(statistics, HttpStatus.OK);
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
