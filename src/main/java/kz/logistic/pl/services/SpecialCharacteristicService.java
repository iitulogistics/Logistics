package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.SpecialCharacteristic;
import kz.logistic.pl.models.pojos.json.SpecialCharacteristicJson;

import java.util.List;

public interface SpecialCharacteristicService {

    SpecialCharacteristic getCharacteristicById(Long id);

    List<SpecialCharacteristic> showAllCharacteristic();

    String addSpecialCharacteristic(
        String characteristicNameKk,
        String characteristicNameRu,
        String characteristicNameEn,
        String addInfo
    );

    String addSpecialCharacteristicJson(SpecialCharacteristicJson specialCharacteristicJson);

    String updateCharacteristic(Long characteristicId, SpecialCharacteristicJson specialCharacteristicJson);

    String deleteCharacteristic(Long characteristicId);

}
