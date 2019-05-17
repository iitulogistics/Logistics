package kz.logistic.pl.services;

import java.util.List;

import kz.logistic.pl.models.entities.CountryEntity;
import kz.logistic.pl.models.pojos.Country;
import kz.logistic.pl.models.pojos.impl.DefaultCountry;
import kz.logistic.pl.models.pojos.json.CountryJson;

public interface CountryService {

    List<Country> showAllCountries();

    DefaultCountry showCountry(Long countryId);

    CountryEntity addCountry(String countryNameKk, String countryNameRu, String countryNameEn);

    String addCountryJson(CountryJson countryJson);

    String updateCountry(Long countryId, CountryJson countryJson);

    String deleteCountry(Long countryId);

    List<Country> search(String value);
}
