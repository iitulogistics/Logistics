package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "inhabited_locality")
public class InhabitedLocalityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inhLocalityId;

    @Column(name = "inh_locality_name_kk")
    private String inhLocalityNameKk;

    @Column(name = "inh_locality_name_ru")
    private String inhLocalityNameRu;

    @Column(name = "inh_locality_name_en")
    private String inhLocalityNameEn;

    @Column(name = "district_id")
    private Long districtId;
}
