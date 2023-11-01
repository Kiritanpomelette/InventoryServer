package xyz.miyayu.inventoryserver.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.miyayu.inventoryserver.Entity.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
