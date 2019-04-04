package kz.logistic.pl.models.pojos;

public interface City {
    Long getCityId();

    LocalizedMessage getCityName();

    Long getRegionId();

    Long getCountryId();
}
