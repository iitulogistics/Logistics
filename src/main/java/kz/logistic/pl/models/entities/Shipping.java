package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipping_id;
    @Column(name = "order_number")
    private Long orderNumber;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "accept_date")
    private Date acceptDate;
    @Column(name = "shipping_date")
    private Date shippingDate;
    @Column(name = "add_info")
    private String addInfo;
}
