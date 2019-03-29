package kz.logistic.pl.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kz.logistic.pl.models.entities.CityEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.City;
import kz.logistic.pl.models.pojos.impl.DefaultCity;
import kz.logistic.pl.models.pojos.json.CityJson;
import kz.logistic.pl.repositories.CityRepository;
import kz.logistic.pl.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class DefaultCityService implements CityService {

  private CityRepository cityRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setCityRepository(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
      LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<City> showAllCities() {
    List<CityEntity> cityEntities = this.cityRepository.findAll();
    return cityEntities.stream().map(cityEntity -> DefaultCity.builder()
      .cityId(cityEntity.getCityId())
      .cityName(
        localizedMessageBuilderFactory.builder()
          .en(cityEntity.getCityNameEn())
          .kk(cityEntity.getCityNameKk())
          .ru(cityEntity.getCityNameRu()).build())
      .regionId(cityEntity.getRegionId())
      .countryId(cityEntity.getCountryId())
      .build()).collect(Collectors.toList());
  }

  @Override
  public DefaultCity showCity(Long cityId) {
    CityEntity cityEntity = this.cityRepository.findById(cityId).orElse(null);

    return DefaultCity.builder()
      .cityId(cityEntity.getCityId())
      .cityName(
        localizedMessageBuilderFactory.builder()
        .en(cityEntity.getCityNameEn())
        .kk(cityEntity.getCityNameKk())
        .ru(cityEntity.getCityNameRu()).build())
      .regionId(cityEntity.getRegionId())
      .countryId(cityEntity.getCountryId()).build();
  }

  public boolean exists(Long countryId, String cityNameEn) {
    ArrayList<CityEntity> cityEntity =
        this.cityRepository.findByCountryIdAndCityNameEn(countryId, cityNameEn);
    return cityEntity.size() > 0;
  }

  @Override
  public String addCity(String cityNameKk, String cityNameRu,
                        String cityNameEn, Long regionId, Long countryId) {

    if (exists(countryId, cityNameEn)) {
      return "В этой стране такой город уже существует";
    }
    CityEntity cityEntity = new CityEntity();
    cityEntity.setCityNameEn(cityNameEn);
    cityEntity.setCityNameRu(cityNameRu);
    cityEntity.setCityNameKk(cityNameKk);
    cityEntity.setRegionId(regionId);
    cityEntity.setCountryId(countryId);


    this.cityRepository.save(cityEntity);
    log.info("Added new city " + cityNameRu + " " + new Date());
    return "Новый город добавлен";
  }

  @Override
  public String addCityJson(CityJson cityJson) {
    if (exists(cityJson.getCountryId(), cityJson.getCityNameEn())) {
      return "В этой стране такой город уже существует";
    }
    CityEntity cityEntity = new CityEntity();
    cityEntity.setCityNameKk(cityJson.getCityNameKk());
    cityEntity.setCityNameRu(cityJson.getCityNameRu());
    cityEntity.setCityNameEn(cityJson.getCityNameEn());
    cityEntity.setRegionId(cityJson.getRegionId());
    cityEntity.setCountryId(cityJson.getCountryId());

    this.cityRepository.save(cityEntity);
    log.info("Added new city " + cityJson.getCityNameRu() + " via JSON " + new Date());
    return "Новый город добавлен посредством JSON";
  }

  @Override
  public String updateCity(Long cityId, CityJson cityJson) {
    CityEntity cityEntity = cityRepository.findById(cityId).orElse(null);
    if (Objects.nonNull(cityEntity)) {
      if (cityJson.getCityNameKk() != null) {
        cityEntity.setCityNameKk(cityJson.getCityNameKk());
      }
      if (cityJson.getCityNameRu() != null) {
        cityEntity.setCityNameRu(cityJson.getCityNameRu());
      }
      if (cityJson.getCityNameEn() != null) {
        cityEntity.setCityNameEn(cityJson.getCityNameEn());
      }
      if (cityJson.getRegionId() != null) {
        cityEntity.setRegionId(cityJson.getRegionId());
      }
      if (cityJson.getCountryId() != null) {
        cityEntity.setCountryId(cityJson.getCountryId());
      }
      this.cityRepository.save(cityEntity);
      log.info("Updated " + cityJson.getCityNameRu() + " city" + new Date());
      return "Город обновлен";
    } else {
      return "Город с таким id не существует";
    }
  }

  @Override
  public String deleteCity(Long cityId) {
    CityEntity cityEntity = this.cityRepository.findById(cityId).orElse(null);
    if (Objects.nonNull(cityEntity)) {
      log.info("Deleted " + cityEntity.getCityNameRu() + " city" + new Date());
      this.cityRepository.delete(cityEntity);
      return "Город удален";
    } else {
      return "Город с таким id не существует";
    }
  }
}
