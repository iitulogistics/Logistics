package kz.logistic.pl.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "special_characteristic")
public class SpecialCharacteristicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long special_characteristic_id;

    @Column(name = "characteristic_name_kk")
    private String characteristicNameKk;

    @Column(name = "characteristic_name_ru")
    private String characteristicNameRu;

    @Column(name = "characteristic_name_en")
    private String characteristicNameEn;

    @Column(name = "add_info")
    private String addInfo;

}
