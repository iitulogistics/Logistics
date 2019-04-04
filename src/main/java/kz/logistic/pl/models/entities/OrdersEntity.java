package kz.logistic.pl.models.entities;


import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@Entity
@Data
@Table(name = "orders")
public class OrdersEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(name = "order_number")
  private Long orderNumber;

  @Column(name = "product_id")
  private Long productId;
  @Column(name = "seller_company_id")
  private Long sellerCompanyId;

  @Column(name = "order_date")
  @Temporal(TemporalType.DATE)
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
