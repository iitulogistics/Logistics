package kz.logistic.pl.repositories;

import java.util.ArrayList;

import kz.logistic.pl.models.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

  @Query("select reg from RegionEntity reg where reg.regionNameEn = ?1 and reg.countryId = ?2")
  ArrayList<RegionEntity> findByRegionNameEnAndCountryId(String regionNameEn, Long countryId);

}
