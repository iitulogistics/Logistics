package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "shipper")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipper_id;
    @Column(name = "shipper_name_kk")
    private String shipperNameKk;
    @Column(name = "shipper_name_ru")
    private String shipperNameRu;
    @Column(name = "shipper_name_en")
    private String shipperNameEn;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "email")
    private String email;
    @Column(name = "bin")
    private Long bin;
    @Column(name = "address")
    private String address;
    @Column(name = "shipping_id")
    private String shippingId;
}
