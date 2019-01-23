package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "shipping")
public class ShippingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipping_id;
    @Column(name = "shipper_id")
    private Integer shipper_id;
    @Column(name = "order_id")
    private Integer order_id;
    @Column(name = "order_number")
    private Integer order_number;
    @Column(name = "shipping_address")
    private String shipping_address;
    @Column(name = "accept_date")
    private Date accept_date;
    @Column(name = "shipping_date")
    private Date shipping_date;
    @Column(name = "add_info")
    private String add_info;
}
