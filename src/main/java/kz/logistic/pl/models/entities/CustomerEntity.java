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
@Table(name = "customer")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;

  //@Column(name = "login_entity_id")
  //private Long loginEntityId;
  @Column(name = "customer_name_kk")
  private String customerNameKk;
  @Column(name = "customer_name_ru")
  private String customerNameRu;
  @Column(name = "customer_name_en")
  private String customerNameEn;
  @Column(name = "iin_or_bin")
  private String iinOrBin;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "mobile_phone")
  private String mobilePhone;
  @Column(name = "email")
  private String email;
  @Column(name = "add_info")
  private String addInfo;

  @OneToOne(mappedBy = "customerEntity", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
  private LoginEntity loginEntity;

}
