package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "seller_company")
public class SellerCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seller_id;
    @Column(name = "name_kk")
    private String nameKk;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "bin")
    private Long bin;
    @Column(name = "enail")
    private String email;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "order_id")
    private String orderId;
}
