package kz.logistic.pl.repositories;

import java.util.ArrayList;

import kz.logistic.pl.models.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CityRepository extends JpaRepository<CityEntity, Long> {

  @Query("select c from CityEntity c where c.countryId = ?1 and c.cityNameEn = ?2")
  ArrayList<CityEntity> checkCityInCountry(Long countryId, String cityNameEn);




    @Modifying
    @Transactional
    @Query("update CityEntity c set c.cityNameEn = ?2, c.cityNameRu = ?3, c.cityNameKk = ?4, c.cityId = ?1")
    void updateCityById(Long id, String nameKk, String nameRu, String nameEn);


}
