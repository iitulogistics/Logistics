package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "warehouse_address")
public class WarehouseAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wh_address_id;
    @Column(name = "region_name")
    private String region_name;
    @Column(name = "city_name")
    private String city_name;
    @Column(name = "street_name")
    private String street_name;
    @Column(name = "zip_code")
    private String zip_code;
}
