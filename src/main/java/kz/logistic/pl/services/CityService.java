package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.City;
import kz.logistic.pl.models.pojos.json.CityJson;


public interface CityService {

  List<City> showAllCities();

  String addCity(String cityNameKk, String cityNameRu,
                 String cityNameEn, Long regionId, Long countryId);

  String addCityJson(CityJson cityJson);

  String updateCity(Long cityId, CityJson cityJson);

  String deleteCity(Long cityId);

}
