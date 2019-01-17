package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouse_id;
    @Column(name = "wh_name_kk")
    private String whNameKk;
    @Column(name = "wh_name_ru")
    private String whNameRu;
    @Column(name = "wh_name_en")
    private String whNameEn;
    @Column(name = "wh_address_id")
    private String whAddressId;
    @Column(name = "seller_company_id")
    private String sellerCompanyId;
    @Column(name = "seller_id")
    private String sellerId;
}
