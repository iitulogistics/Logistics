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
@Table(name = "shipping")
public class ShippingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long shippingId;
  @Column(name = "shipper_id")
  private Long shipperId;
  @Column(name = "order_id")
  private Long orderId;
  @Column(name = "order_number")
  private Integer orderNumber;
  @Column(name = "shipping_address")
  private String shippingAddress;
  @Column(name = "accept_date")
  private Date acceptDate;
  @Column(name = "shipping_date")
  private Date shippingDate;
  @Column(name = "add_info")
  private String addInfo;
}
