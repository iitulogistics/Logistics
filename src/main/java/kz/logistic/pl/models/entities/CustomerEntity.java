package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column(name = "customer_name_kk")
    private String customer_name_kk;
    @Column(name = "customer_name_ru")
    private String customer_name_ru;
    @Column(name = "customer_name_en")
    private String customer_name_en;
    @Column(name = "iin_or_bin")
    private String iin_or_bin;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "mobile_phone")
    private String mobile_phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "add_info")
    private String add_info;

}
