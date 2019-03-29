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
  private Long sellerCategoryId;

  @OneToOne(mappedBy = "sellerCompanyEntity", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
  private LoginEntity loginEntity;
}
