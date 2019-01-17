package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @Column(name = "order_number")
    private Long orderNumber;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "seller_id")
    private Long sellerId;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "product_count")
    private Long productCount;
    @Column(name = "unit_price")
    private Long unitPrice;
    @Column(name = "total_price")
    private Long total_price;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "status")
    private String status;
}
