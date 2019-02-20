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
@Table(name = "district")
public class DistrictEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long districtId;

  @Column(name = "district_name_kk")
  private String districtNameKk;

  @Column(name = "district_name_ru")
  private String districtNameRu;

  @Column(name = "district_name_en")
  private String districtNameEn;

  @Column(name = "region_id")
  private Long regionId;

  @Column(name = "city_id")
  private Long cityId;

}
