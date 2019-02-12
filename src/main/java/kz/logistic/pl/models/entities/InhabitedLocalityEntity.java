package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table
public class InhabitedLocalityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ihnLocalityId;

    @Column(name = "ihn_locality_name_kk")
    private String ihnLocalityNameKk;

    @Column(name = "ihn_locality_name_ru")
    private String ihnLocalityNameRu;

    @Column(name = "ihn_locality_name_en")
    private String ihnLocalityNameEn;

    @Column(name = "district_id")
    private Long districtId;
}
