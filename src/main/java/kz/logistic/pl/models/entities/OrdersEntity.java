package kz.logistic.pl.models.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "orders")
public class OrdersEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;
  @Column(name = "order_number")
  private Integer orderNumber;
  @Column(name = "product_id")
  private Long productId;
  @Column(name = "seller_company_id")
  private Long sellerCompanyId;
  @Column(name = "order_date")
  private Date orderDate;
  @Column(name = "product_count")
  private Integer productCount;
  @Column(name = "unit_price")
  private Integer unitPrice;
  @Column(name = "total_price")
  private Integer totalPrice;
  @Column(name = "customer_id")
  private Long customerId;
  @Column(name = "delivering_status")
  private String deliveringStatus;
  @Column(name = "product_amount")
  private Integer productAmount;
  @Column(name = "order_amount")
  private Integer orderAmount;
}
