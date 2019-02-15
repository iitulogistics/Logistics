package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.City;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Builder;

@Builder
public class DefaultCity implements City {

    private Long cityId;
    private LocalizedMessage cityName;
    private Long regionId;
    private Long countryId;

    @Override
    public Long getCityId() {
        return cityId;
    }

    @Override
    public LocalizedMessage getCityName() {
        return cityName;
    }

    @Override
    public Long getRegionId() {
        return regionId;
    }

    @Override
    public Long getCountryId() {
        return countryId;
    }
}
