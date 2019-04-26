package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

  @Query("select c from CountryEntity c where c.countryNameEn = ?1")
  ArrayList<CountryEntity> existCountry(String countryNameEn);

  @Query("select c from CountryEntity c where c.countryNameEn like %?1% " +
    "or c.countryNameRu like %?2% " +
    "or c.countryNameKk like %?3% " +
    "or c.countryId = ?4")
  ArrayList<CountryEntity> findCountryByNameContainsOrIdEquals
    (String pattern1,
     String pattern2,
     String pattern3,
     Long countryId);


}
