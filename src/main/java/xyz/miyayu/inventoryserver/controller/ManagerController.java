package xyz.miyayu.inventoryserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.miyayu.inventoryserver.Entity.Manager;
import xyz.miyayu.inventoryserver.Repository.ManagerRepository;

@RestController //コントローラークラスに付与するアノテーション
@RequestMapping("/manager") //runで始まるURL
public class ManagerController {
  @Autowired
  private ManagerRepository managerRepository;

  //Managerをすべて取得する　GET
  @GetMapping("")
  public Iterable<Manager> getAllManagers() {
    return managerRepository.findAll();
  }

  @GetMapping("/selectID")
  public Manager getManagerById(@RequestParam int id) {
    // Optionalを使用してエンティティを取得 エンティティが存在しない場合はnullを返すか、例外をスロー
    return managerRepository.findById(id).orElse(null);
  }

  //Managerを追加する　Postの処理
  @PostMapping("/add") //Pos Req
  public @ResponseBody String addNewUser(@RequestParam String name) {
    /*
      @RequestBodyは返される文字列がビュー名ではなくレスポンスであることを意味する
      @RequestParamはGETまたはPOSTリクエストのパラメータを意味する
     */
    Manager n = new Manager();
    n.setName(name);
    managerRepository.save(n);
    return "DataSaved";
  }
}
