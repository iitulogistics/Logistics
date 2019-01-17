package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "warehouse_address")
public class WarehouseAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wh_address_id;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "zip_code")
    private String zipCode;
}
