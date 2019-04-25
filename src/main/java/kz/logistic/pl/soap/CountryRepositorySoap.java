package kz.logistic.pl.soap;

import kz.logistic.pl.models.entities.CountryEntity;
import kz.logistic.pl.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.logistics.Country;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public String deleteCountryId(Long id){
      CountryEntity entity = this.repository.findById(id).orElse(null);
       if (entity !=null){
         this.repository.delete(entity);
         return "Country by ID "+id+" deleted";
       } else {
         return "Country by ID "+id+" does not found in database";
       }
    }
}
