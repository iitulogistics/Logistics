package kz.logistic.pl.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "special_characteristic")
public class SpecialCharacteristicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long specialCharacteristicId;

  @Column(name = "characteristic_name_kk")
  private String characteristicNameKk;

  @Column(name = "characteristic_name_ru")
  private String characteristicNameRu;

  @Column(name = "characteristic_name_en")
  private String characteristicNameEn;

  @Column(name = "add_info")
  private String addInfo;

}
