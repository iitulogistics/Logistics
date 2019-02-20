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
@Table(name = "region")
public class RegionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long regionId;

  @Column(name = "region_name_kk")
  private String regionNameKk;

  @Column(name = "region_name_ru")
  private String regionNameRu;

  @Column(name = "region_name_en")
  private String regionNameEn;

  @Column(name = "country_id")
  private Long countryId;

}
