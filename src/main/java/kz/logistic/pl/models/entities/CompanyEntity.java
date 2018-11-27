package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "company")
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
  private String bin;
  @Column(name = "email")
  private String email;

  @OneToOne(mappedBy = "companyEntity", cascade = CascadeType.ALL,
          fetch = FetchType.LAZY, optional = false)
  private LoginEntity loginEntity;

}
