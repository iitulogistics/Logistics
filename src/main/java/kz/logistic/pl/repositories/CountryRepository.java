package kz.logistic.pl.repositories;

import java.util.ArrayList;
import kz.logistic.pl.models.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

  ArrayList<CountryEntity> findByCountryNameEn(String countryNameEn);

  ArrayList<CountryEntity> findByCountryNameEnContainsOrCountryNameKkContainsOrCountryNameRuContainsOrCountryIdEquals
    (String pattern1,
     String pattern2,
     String pattern3,
     Long countryId);

}
