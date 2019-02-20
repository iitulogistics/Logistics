package kz.logistic.pl.repositories;

import java.util.ArrayList;
import kz.logistic.pl.models.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<CityEntity, Long> {

  ArrayList<CityEntity> findByCountryIdAndCityNameEn(Long countryId, String cityNameEn);

}
