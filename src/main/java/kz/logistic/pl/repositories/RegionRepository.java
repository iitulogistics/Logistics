package kz.logistic.pl.repositories;

import java.util.ArrayList;
import kz.logistic.pl.models.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

  ArrayList<RegionEntity> findByRegionNameEnAndCountryId(String regionNameEn, Long countryId);

}
