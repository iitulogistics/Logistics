package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

    ArrayList<RegionEntity> findByRegionNameEnAndCountryId(String regionNameEn, Long countryId);

}
