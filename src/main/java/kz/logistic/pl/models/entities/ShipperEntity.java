package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "shipper")
public class ShipperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipper_id;
    @Column(name = "shipper_name_kk")
    private String shipper_name_kk;
    @Column(name = "shipper_name_ru")
    private String shipper_name_ru;
    @Column(name = "shipper_name_en")
    private String shipper_name_en;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "mobile_phone")
    private String mobile_phone;
    @Column(name = "bin")
    private String bin;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
}
