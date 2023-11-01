package xyz.miyayu.inventoryserver.Repository;

import org.springframework.data.repository.CrudRepository;
import xyz.miyayu.inventoryserver.Entity.Manager;

//CrudRepository:Spring Data JPAが提供するインターフェイス、基本的なCRUD操作メソッドを提供
//CRUDはCreate・Read・Update・Deleteを指す
/* <Manager, Integer>:CrudRepositoryの型パラメータ、
一つ目のパラメータはリポジトリが操作するエンティティのタイプ
二つ目はエンティティの主キーのタイプを示す
以上の場合は"Manager"エンティティの主キーは"Integer"型であることを指す
 */


//インターフェイスの宣言
public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
