package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "seller_company")
public class SellerCompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerCompanyId;

    @Column(name = "company_name_kk")
    private String companyNameKk;
    @Column(name = "company_name_ru")
    private String companyNameRu;
    @Column(name = "company_name_en")
    private String companyNameEn;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @Column(name = "bin")
    private String bin;
    @Column(name = "email")
    private String email;
    @Column(name = "seller_category_id")
    private Integer sellerCategoryId;

    @OneToOne(mappedBy = "sellerCompanyEntity", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private LoginEntity loginEntity;
}
