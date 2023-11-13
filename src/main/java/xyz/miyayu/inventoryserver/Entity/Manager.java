//JPAを使用して、テーブルとマッピングされるエンティティを表すクラス

package xyz.miyayu.inventoryserver.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity //エンティティの宣言
@Table(name = "ManagerMaster") //テーブルのマッピング
@Getter
@Setter
public class Manager {

  @Id //idフィールド　テーブルの主キーであることを示すアノテーション
  //主キーの生成戦略を示すアノテーション → 下の場合はIDENTITY戦略が使われ、データベースが自動的に値を生成
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "ManagerName") //nameフィールド → テーブルのどのカラムとマッピングされるかを示す
  private String name;

}
