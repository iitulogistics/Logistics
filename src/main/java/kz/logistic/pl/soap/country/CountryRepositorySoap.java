package kz.logistic.pl.soap.country;

import kz.logistic.pl.models.entities.CountryEntity;
import kz.logistic.pl.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.country.Country;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryRepositorySoap {
    private static final Map<Long, Country> countryMap = new HashMap<>();

    private CountryRepository repository;

    @Autowired(required = false)
    public void setRepository(CountryRepository repository) {
        this.repository = repository;
    }

  @PostConstruct
  public void initData() {
    List<CountryEntity> entities = this.repository.findAll();
    entities.forEach(countryEntity -> {
      Country country = new Country();
      country.setId(countryEntity.getCountryId());
      country.setCountryNameKk(countryEntity.getCountryNameKk());
      country.setCountryNameRu(countryEntity.getCountryNameRu());
      country.setCountryNameEn(countryEntity.getCountryNameEn());

            countryMap.put(country.getId(), country);

        });
    }

  public Country findCountryId(Long id) {
    return countryMap.get(id);
  }

  public Country add(String nameKk, String nameRu, String nameEn){
    for(Long key : countryMap.keySet()){
      Country country = countryMap.get(key);
      if(country.getCountryNameEn().equals(nameEn) &&
        country.getCountryNameKk().equals(nameRu) &&
        country.getCountryNameRu().equals(nameRu)) return null;
    }
    CountryEntity  countryEntity = new CountryEntity();

    countryEntity.setCountryNameKk(nameKk);
    countryEntity.setCountryNameRu(nameRu);
    countryEntity.setCountryNameEn(nameEn);
    repository.save(countryEntity);

    Country country = new Country();
    country.setCountryNameEn(countryEntity.getCountryNameEn());
    country.setCountryNameRu(countryEntity.getCountryNameRu());
    country.setCountryNameKk(countryEntity.getCountryNameKk());
    country.setId(countryEntity.getCountryId());

        countryMap.put(country.getId(), country);
    return country;
  }

  public Country updateCountry(Long id, String nameKk, String nameRu, String nameEn) {
    Country c = countryMap.get(id);
    c.setCountryNameEn(nameEn);
    c.setCountryNameRu(nameRu);
    c.setCountryNameKk(nameKk);

    repository.updateCountryById(id, nameEn, nameRu, nameKk);

    return c;
  }

  public Map<Long, Country> getAllCountry(){
    return countryMap;
  }

  public String deleteCountryId(Long id) {
    CountryEntity countryEntity = this.repository.findById(id).orElse(null);
    if (countryEntity != null) {
      this.repository.deleteById(id);
      return "Id" + id + " удалена из базы данных";
    } else {
      return "Id" + id + " не удалена из базы данных";
    }
  }
}
