package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "city")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @Column(name = "country_name_kk")
    private String countryNameKk;

    @Column(name = "country_name_ru")
    private String countryNameRu;

    @Column(name = "country_name_en")
    private String countryNameEn;

}
