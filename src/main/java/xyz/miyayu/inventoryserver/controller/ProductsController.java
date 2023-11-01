package xyz.miyayu.inventoryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.miyayu.inventoryserver.Entity.Products;
import xyz.miyayu.inventoryserver.Repository.ProductsRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {
  @Autowired
  private ProductsRepository productsRepository;

  @GetMapping("")
  public Iterable<Products> getAllProducts() {
    return productsRepository.findAll();
  }

  @GetMapping("/selectID")
  public Products getProductById(@RequestParam int id) {
    // Optionalを使用してエンティティを取得 エンティティが存在しない場合はnullを返すか、例外をスロー
    return productsRepository.findById(id).orElse(null);
  }

  @PostMapping("add")
  public @ResponseBody String addNewProducts(@RequestParam String name, String memo) {
    Products n = new Products();
    n.setName(name);
    n.setMemo(memo);
    productsRepository.save(n);
    return "DataSaved";
  }
}
