package kz.logistic.pl.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentId;

  @Column(name = "payment_amount")
  private Integer paymentAmount;

  @Column(name = "paymentState")
  private Integer paymentState;

  @Column(name = "timestamp")
  private Date timestamp;

  @Column(name = "order_id")
  private Long orderId;

  @Column(name = "cc_id")
  private Long ccId;

  @Column(name = "payment_status")
  private Integer paymentStatus;
}
