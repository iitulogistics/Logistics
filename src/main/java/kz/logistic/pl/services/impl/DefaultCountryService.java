package kz.logistic.pl.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.models.entities.CountryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Country;
import kz.logistic.pl.models.pojos.impl.DefaultCountry;
import kz.logistic.pl.models.pojos.json.CountryJson;
import kz.logistic.pl.repositories.CountryRepository;
import kz.logistic.pl.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class DefaultCountryService implements CountryService {

  private CountryRepository countryRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setCountryRepository(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
    LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<Country> showAllCountries() {
    List<CountryEntity> countryList = countryRepository.findAll();
    return countryList.stream().map(countryEntity -> DefaultCountry.builder()
      .countryId(countryEntity.getCountryId())
      .countryName(localizedMessageBuilderFactory.builder()
        .en(countryEntity.getCountryNameEn())
        .kk(countryEntity.getCountryNameKk())
        .ru(countryEntity.getCountryNameRu()).build())
      .build()).collect(Collectors.toList());
  }

  @Override
  public DefaultCountry showCountry(Long countryId) {
    CountryEntity countryEntity =  this.countryRepository.findById(countryId).orElse(null);
    return DefaultCountry.builder()
      .countryId(countryEntity.getCountryId())
      .countryName(localizedMessageBuilderFactory.builder()
      .en(countryEntity.getCountryNameEn())
      .kk(countryEntity.getCountryNameKk())
      .ru(countryEntity.getCountryNameRu()).build())
      .build();
  }

  public boolean exists(String countryNameEn) {
    ArrayList<CountryEntity> entities = countryRepository.findByCountryNameEn(countryNameEn);
    return entities.size() > 0;
  }

  @Override
  public String addCountry(String countryNameKk, String countryNameRu, String countryNameEn) {
    if (exists(countryNameEn)) {
      return "Страна с таким названием уже существует";
    }
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setCountryNameEn(countryNameEn);
    countryEntity.setCountryNameKk(countryNameKk);
    countryEntity.setCountryNameRu(countryNameRu);

    this.countryRepository.save(countryEntity);
    log.info("Added new country " + countryNameEn + " " + new Date());
    return "Страна добавлена";
  }

  @Override
  public String addCountryJson(CountryJson countryJson) {
    if (exists(countryJson.getCountryNameEn())) {
      return "Страна с таким названием уже существует";
    }
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setCountryNameRu(countryJson.getCountryNameRu());
    countryEntity.setCountryNameEn(countryJson.getCountryNameEn());
    countryEntity.setCountryNameKk(countryJson.getCountryNameKk());

    this.countryRepository.save(countryEntity);
    log.info("Added new country " + countryJson.getCountryNameEn() + " via JSON " + new Date());
    return "Страна добавлена посредством JSON";
  }

  @Override
  public String updateCountry(Long countryId, CountryJson countryJson) {
    CountryEntity countryEntity = this.countryRepository.findById(countryId).orElse(null);

    if (Objects.nonNull(countryEntity)) {
      if (countryJson.getCountryNameKk() != null) {
        countryEntity.setCountryNameKk(countryJson.getCountryNameKk());
      }
      if (countryJson.getCountryNameRu() != null) {
        countryEntity.setCountryNameRu(countryJson.getCountryNameRu());
      }
      if (countryJson.getCountryNameEn() != null) {
        countryEntity.setCountryNameEn(countryJson.getCountryNameEn());
      }
      this.countryRepository.save(countryEntity);
      log.info("Updated " + countryJson.getCountryNameEn() + " country " + new Date());
      return "Страна обновлена";
    } else {
      return "Страны с таким id не существует";
    }
  }

  @Override
  public String deleteCountry(Long countryId) {
    CountryEntity countryEntity = this.countryRepository.findById(countryId).orElse(null);
    if (Objects.nonNull(countryEntity)) {
      log.info("Deleted " + countryEntity.getCountryNameEn() + "country " + new Date());
      this.countryRepository.delete(countryEntity);
      return "Страна удалена";
    } else {
      return "Страны с таким id не существует";
    }
  }
}
