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
    private Long wh_id;
    @Column(name = "seller_company_id")
    private Long sellerCompanyId;
    @Column(name = "address")
    private AddressEntity address;
}
