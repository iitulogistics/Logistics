package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "iin_or_bin")
    private Long iinOrBin;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "add_info")
    private String addInfo;
}
