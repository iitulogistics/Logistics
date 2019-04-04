package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.SpecialCharacteristic;
import kz.logistic.pl.models.pojos.json.SpecialCharacteristicJson;

import java.util.List;

public interface SpecialCharacteristicService {

    SpecialCharacteristic getCharacteristicById(Long id);

    List<SpecialCharacteristic> showAllCharacteristic();

    void addSpecialCharacteristic(
        String characteristicNameKk,
        String characteristicNameRu,
        String characteristicNameEn,
        String addInfo
    );

    void addSpecialCharacteristicJson(SpecialCharacteristicJson specialCharacteristicJson);

    String updateCharacteristic(Long characteristicId, SpecialCharacteristicJson specialCharacteristicJson);

    String deleteCharacteristic(Long characteristicId);

}
