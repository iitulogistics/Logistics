package kz.logistic.pl.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;


@Setter
@Getter
@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @Column(name = "country_name_kk")
    private String countryNameKk;

    @Column(name = "country_name_ru")
    private String countryNameRu;

    @Column(name = "country_name_en")
    @Setter(AccessLevel.NONE)
    private String countryNameEn;

    public void setCountryNameEn(String countryNameEn) {
        if (Charset.forName("US-ASCII").newEncoder().canEncode(countryNameEn)) {
            this.countryNameEn = countryNameEn;
        } else
            this.countryNameEn = null;
    }
}
