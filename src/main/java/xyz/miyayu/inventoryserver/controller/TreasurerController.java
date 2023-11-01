package xyz.miyayu.inventoryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping("/selectID")
  public Iterable<Treasurer> getTreasurerById(@RequestParam int productId) {
    // Optionalを使用してエンティティを取得 エンティティが存在しない場合はnullを返すか、例外をスロー
    return treasurerRepository.findByProductId(productId).orElse(null);
  }

  @PostMapping("")
  public @ResponseBody String addNewTreasurer(@RequestParam int productId, int managerId,
                                              int count) {
    Treasurer n = new Treasurer();
    n.setProductId(managerId);
    n.setManagerId(productId);
    n.setCount(count);
    treasurerRepository.save(n);
    return "DataSaved";
  }
}
