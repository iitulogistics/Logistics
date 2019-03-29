package kz.logistic.pl.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "shipper")
public class ShipperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipperId;
    @Column(name = "shipper_name_kk")
    private String shipperNameKk;
    @Column(name = "shipper_name_ru")
    private String shipperNameRu;
    @Column(name = "shipper_name_en")
    private String shipperNameEn;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "bin")
    private String bin;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "shipperEntity", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private LoginEntity loginEntity;
}
