package xyz.miyayu.inventoryserver.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;


@Entity
@DynamicInsert
@Table(name = "TreasurerMaster")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Treasurer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private int id;

  @Column(name = "ProductId", nullable = false, columnDefinition = "int default 0")
  private long productId;

  @Column(name = "ManagerId", nullable = false, columnDefinition = "int default 0")
  private long managerId;

  @Column(name = "Date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
  private Date date;

  @Column(name = "Count", columnDefinition = "int default 0")
  private long count;
}
