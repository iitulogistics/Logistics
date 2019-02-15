package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Country;
import kz.logistic.pl.models.pojos.json.CountryJson;

import java.util.List;

public interface CountryService {

    List<Country> showAllCountries();

    String addCountry(String countryNameKk, String countryNameRu, String countryNameEn);

    String addCountryJson(CountryJson countryJson);

}
