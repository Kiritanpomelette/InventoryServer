package xyz.miyayu.inventoryserver.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.DynamicInsert;


@Entity
@DynamicInsert
@Table(name = "TreasurerMaster")
public class Treasurer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "ProductId", length = 10, nullable = false)
  private int productId;
  @Column(name = "ManagerID", length = 10, nullable = false)
  private int managerId;
  //updatable = false：こちら指定することで更新時にnullが入ってしまうのを防ぐ
  @Column(name = "Date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
  private Date date;
  @Column(name = "Count")
  private int count;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getManagerId() {
    return managerId;
  }

  public void setManagerId(int managerId) {
    this.managerId = managerId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
