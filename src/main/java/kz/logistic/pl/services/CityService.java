package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.City;
import kz.logistic.pl.models.pojos.json.CityJson;

import java.util.List;

public interface CityService {

    List<City> showAllCities();

    void addCity(String cityNameKk, String cityNameRu,
                 String cityNameEn, Long regionId, Long countryId);

    void addCityJson(CityJson cityJson);
}
