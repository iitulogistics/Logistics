package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.SpecialCharacteristic;
import lombok.Builder;

@Builder
public class DefaultSpecialCharacteristic implements SpecialCharacteristic {

    private Long characteristicId;

    private String characteristicNameKk;

    private String characteristicNameRu;

    private String characteristicNameEn;

    private String addInfo;

    @Override
    public Long getCharacteristicId() {
        return characteristicId;
    }

    @Override
    public String getCharacteristicNameKk() {
        return characteristicNameKk;
    }

    @Override
    public String getCharacteristicNameRu() {
        return characteristicNameRu;
    }

    @Override
    public String getCharacteristicNameEn() {
        return characteristicNameEn;
    }

    @Override
    public String getAddInfo() {
        return addInfo;
    }
}
