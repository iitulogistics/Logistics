package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "addresses")
public class AddressesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "ihn_locality_id")
    private Long ihnLocalityId;

    @Column(name = "street_name_kk")
    private String streetNameKk;

    @Column(name = "street_name_ru")
    private String streetNameRu;

    @Column(name = "street_name_en")
    private String streetNameEn;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "flat_number")
    private String flatNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "address_assign")
    private Integer addressAssign;

}