package kz.logistic.pl.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.models.entities.RegionEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Region;
import kz.logistic.pl.models.pojos.impl.DefaultRegion;
import kz.logistic.pl.models.pojos.json.RegionJson;
import kz.logistic.pl.repositories.RegionRepository;
import kz.logistic.pl.services.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;



@Slf4j
public class DefaultRegionService implements RegionService {

  private RegionRepository regionRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setRegionRepository(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
    LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<Region> showAllRegions() {
    List<RegionEntity> regionEntities = regionRepository.findAll();
    return regionEntities.stream().map(regionEntity -> DefaultRegion.builder()
      .regionId(regionEntity.getRegionId())
      .regionName(localizedMessageBuilderFactory.builder()
        .en(regionEntity.getRegionNameEn())
        .kk(regionEntity.getRegionNameKk())
        .ru(regionEntity.getRegionNameRu()).build())
      .countryId(regionEntity.getCountryId()).build()).collect(Collectors.toList());
  }

  public boolean exists(String regionNameEn, Long countryId) {
    ArrayList<RegionEntity> regionEntities =
      this.regionRepository.findByRegionNameEnAndCountryId(regionNameEn, countryId);
    return regionEntities.size() > 0;
  }

  @Override
  public String addRegion(
    String regionNameEn, String regionNameKk, String regionNameRu, Long countryId) {
    if (exists(regionNameEn, countryId)) {
      return "В этой стране такая область уже существует";
    }
    RegionEntity regionEntity = new RegionEntity();
    regionEntity.setRegionNameEn(regionNameEn);
    regionEntity.setRegionNameKk(regionNameKk);
    regionEntity.setRegionNameRu(regionNameRu);
    regionEntity.setCountryId(countryId);

    this.regionRepository.save(regionEntity);
    log.info("Added new region " + regionNameEn + " " + new Date());
    return "Область добавлена";
  }

  @Override
  public String addRegionJson(RegionJson regionJson) {
    if (exists(regionJson.getRegionNameEn(), regionJson.getCountryId())) {
      return "В этой стране такая область уже существует";
    }
    RegionEntity regionEntity = new RegionEntity();
    regionEntity.setRegionNameRu(regionJson.getRegionNameRu());
    regionEntity.setRegionNameKk(regionJson.getRegionNameKk());
    regionEntity.setRegionNameEn(regionJson.getRegionNameEn());
    regionEntity.setCountryId(regionJson.getCountryId());

    this.regionRepository.save(regionEntity);
    log.info("Added new region " + regionJson.getRegionNameEn() + " " + new Date());
    return "Область добавлена посредством JSON";
  }

  @Override
  public DefaultRegion showRegion(Long regionId) {
    RegionEntity regionEntity = this.regionRepository.findById(regionId).orElse(null);

    return DefaultRegion.builder()
      .regionName(
        localizedMessageBuilderFactory.builder()
          .en(regionEntity.getRegionNameEn())
          .kk(regionEntity.getRegionNameKk())
          .ru(regionEntity.getRegionNameRu()).build())
      .regionId(regionEntity.getRegionId())
      .countryId(regionEntity.getCountryId()).build();
  }

  @Override
  public String updateRegion(Long regionId, RegionJson regionJson) {
    RegionEntity regionEntity = regionRepository.findById(regionId).orElse(null);
    if (Objects.nonNull(regionEntity)) {
      if (regionJson.getRegionNameKk() != null) {
        regionEntity.setRegionNameKk(regionJson.getRegionNameKk());
      }
      if (regionJson.getRegionNameRu() != null) {
        regionEntity.setRegionNameRu(regionJson.getRegionNameRu());
      }
      if (regionJson.getRegionNameEn() != null) {
        regionEntity.setRegionNameEn(regionJson.getRegionNameEn());
      }
      if (regionJson.getCountryId() != null) {
        regionEntity.setCountryId(regionJson.getCountryId());
      }
      this.regionRepository.save(regionEntity);
      log.info("Updated " + regionJson.getRegionNameRu() + " region" + new Date());
      return "Регион обновлен";
    } else {
      return "Региона с таким id не существует";
    }
  }

  @Override
  public String deleteRegion(Long regionId) {
    RegionEntity regionEntity = this.regionRepository.findById(regionId).orElse(null);
    if (Objects.nonNull(regionEntity)) {
      log.info("Updated " + regionEntity.getRegionNameRu() + " region" + new Date());
      this.regionRepository.delete(regionEntity);
      return "Регион удален";
    } else {
      return "Региона с таким id не существует";
    }
  }

}
