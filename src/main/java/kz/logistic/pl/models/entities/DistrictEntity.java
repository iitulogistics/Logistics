package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
