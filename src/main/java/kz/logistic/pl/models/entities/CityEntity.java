package kz.logistic.pl.models.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(name = "city_name_kk")
    private String cityNameKk;

    @Column(name = "city_name_ru")
    private String cityNameRu;

    @Column(name = "city_name_en")
    private String cityNameEn;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "country_id")
    private Long countryId;
}
