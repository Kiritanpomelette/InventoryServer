//JPAを使用して、テーブルとマッピングされるエンティティを表すクラス

package xyz.miyayu.inventoryserver.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //エンティティの宣言
@Table(name = "ManagerMaster") //テーブルのマッピング
public class Manager {

  @Id //idフィールド　テーブルの主キーであることを示すアノテーション

  //主キーの生成戦略を示すアノテーション → 下の場合はIDENTITY戦略が使われ、データベースが自動的に値を生成
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "ManagerName") //nameフィールド → テーブルのどのカラムとマッピングされるかを示す
  private String name;

  //以下はゲッターとセッター　他のクラスからこのクラスのフィールドにアクセスすることや、値を変更することができる
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
