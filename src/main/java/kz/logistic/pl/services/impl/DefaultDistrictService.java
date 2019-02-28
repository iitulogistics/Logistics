package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.DistrictEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.District;
import kz.logistic.pl.models.pojos.impl.DefaultDistrict;
import kz.logistic.pl.models.pojos.json.DistrictJson;
import kz.logistic.pl.repositories.DistrictRepository;
import kz.logistic.pl.services.DistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultDistrictService implements DistrictService {

  private DistrictRepository districtRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setDistrictRepository(DistrictRepository districtRepository) {
    this.districtRepository = districtRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
    LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<District> showAllDistricts() {
    List<DistrictEntity> districtEntities = this.districtRepository.findAll();
    return districtEntities.stream().map(districtEntity -> DefaultDistrict.builder()
      .cityId(districtEntity.getCityId())
      .districtId(districtEntity.getDistrictId())
      .regionId(districtEntity.getRegionId())
      .districtName(localizedMessageBuilderFactory.builder()
        .en(districtEntity.getDistrictNameEn())
        .kk(districtEntity.getDistrictNameKk())
        .ru(districtEntity.getDistrictNameRu()).build()).build()).collect(Collectors.toList());
  }

  @Override
  public DefaultDistrict showDistrict(Long districtId) {
    DistrictEntity districtEntity = this.districtRepository.findById(districtId).orElse(null);
    return DefaultDistrict.builder().districtId(districtEntity.getDistrictId())
      .regionId(districtEntity.getRegionId())
      .cityId(districtEntity.getCityId())
      .districtName(localizedMessageBuilderFactory.builder()
        .ru(districtEntity.getDistrictNameRu())
        .kk(districtEntity.getDistrictNameKk())
        .en(districtEntity.getDistrictNameEn()).build()).build();
  }

  @Override
  public String addDistrict(String districtNameKk, String districtNameEn, String districtNameRu, Long regionId, Long cityid) {
    DistrictEntity districtEntity = new DistrictEntity();
    districtEntity.setCityId(cityid);
    districtEntity.setRegionId(regionId);
    districtEntity.setDistrictNameEn(districtNameEn);
    districtEntity.setDistrictNameKk(districtNameKk);
    districtEntity.setDistrictNameRu(districtNameRu);

    this.districtRepository.save(districtEntity);
    log.info("Added new district " + districtNameEn + " " + new Date());
    return "Новый район добавлен";
  }

  @Override
  public String addDistrictJson(DistrictJson districtJson) {
    DistrictEntity districtEntity = new DistrictEntity();
    districtEntity.setCityId(districtJson.getCityId());
    districtEntity.setRegionId(districtJson.getRegionId());
    districtEntity.setDistrictNameEn(districtJson.getDistrictNameEn());
    districtEntity.setDistrictNameKk(districtJson.getDistrictNameKk());
    districtEntity.setDistrictNameRu(districtJson.getDistrictNameRu());

    this.districtRepository.save(districtEntity);
    log.info("Added new district " + districtJson.getDistrictNameRu() + " via JSON" + new Date());
    return "Новый район добавлен посредством JSON";
  }

  @Override
  public String updateDistrict(Long districtId, DistrictJson districtJson) {
    DistrictEntity districtEntity = this.districtRepository.findById(districtId).orElse(null);
    if (Objects.nonNull(districtEntity)) {
      if (districtJson.getCityId() != null) {
        districtEntity.setCityId(districtJson.getCityId());
      }
      if (districtJson.getRegionId() != null) {
        districtEntity.setRegionId(districtJson.getRegionId());
      }
      if (districtJson.getDistrictNameRu() != null) {
        districtEntity.setDistrictNameRu(districtJson.getDistrictNameRu());
      }
      if (districtJson.getDistrictNameKk() != null) {
        districtEntity.setDistrictNameKk(districtJson.getDistrictNameKk());
      }
      if (districtJson.getDistrictNameEn() != null) {
        districtEntity.setDistrictNameEn(districtJson.getDistrictNameEn());
      }
    }
    this.districtRepository.save(districtEntity);
    log.info("Updated district " + districtJson.getDistrictNameRu() + " " + new Date());
    return "Район обнавлен";
  }

  @Override
  public String deleteDistrict(Long districtId) {
    DistrictEntity districtEntity = this.districtRepository.findById(districtId).orElse(null);
    this.districtRepository.deleteById(districtEntity.getDistrictId());
    log.info("Deleted district " + districtEntity.getDistrictNameEn() + " " + new Date());
    return "Район удален";
  }
}
