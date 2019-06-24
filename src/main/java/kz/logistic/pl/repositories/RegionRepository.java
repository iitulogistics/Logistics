package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

  @Query("select reg from RegionEntity reg where reg.regionNameEn = ?1 and reg.countryId = ?2")
  ArrayList<RegionEntity> findByRegionNameEnAndCountryId(String regionNameEn, Long countryId);



  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update RegionEntity reg set reg.regionNameEn=?2, " + " reg.regionNameRu=?3, reg.regionNameKk=?4, reg.countryId=?5 where reg.regionId = ?1")
    void updateRegionById(Long id, String regionNameKk, String regionNameRu, String regionNameEn, Long countryId);


}
