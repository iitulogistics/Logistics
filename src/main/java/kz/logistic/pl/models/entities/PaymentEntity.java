package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @Column(name = "payment_amount")
    private Integer paymentAmount;

    @Column(name = "payment_state")
    private Integer paymentState;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "cc_id")
    private Integer ccId;

    @Column(name = "payment_status")
    private Integer paymentStatus;
}
