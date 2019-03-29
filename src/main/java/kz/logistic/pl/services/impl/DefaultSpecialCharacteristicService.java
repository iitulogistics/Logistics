package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.SpecialCharacteristicEntity;
import kz.logistic.pl.models.pojos.SpecialCharacteristic;
import kz.logistic.pl.models.pojos.impl.DefaultSpecialCharacteristic;
import kz.logistic.pl.models.pojos.json.SpecialCharacteristicJson;
import kz.logistic.pl.repositories.SpecialCharacteristicRepository;
import kz.logistic.pl.services.SpecialCharacteristicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultSpecialCharacteristicService implements SpecialCharacteristicService {

  private SpecialCharacteristicRepository specialCharacteristicRepository;

  @Autowired
  public void setSpecialCharacteristicRepository(SpecialCharacteristicRepository specialCharacteristicRepository) {
    this.specialCharacteristicRepository = specialCharacteristicRepository;
  }

  @Override
  public SpecialCharacteristic getCharacteristicById(Long id) {
    SpecialCharacteristicEntity specialCharacteristicEntity = this.specialCharacteristicRepository.findById(id).
      orElse(null);
    return DefaultSpecialCharacteristic.builder()
      .characteristicId(id)
      .characteristicNameKk(specialCharacteristicEntity.getCharacteristicNameKk())
      .characteristicNameRu(specialCharacteristicEntity.getCharacteristicNameRu())
      .characteristicNameEn(specialCharacteristicEntity.getCharacteristicNameEn())
      .addInfo(specialCharacteristicEntity.getAddInfo()).build();
  }

  @Override
  public List<SpecialCharacteristic> showAllCharacteristic() {
    List<SpecialCharacteristicEntity> specialCharacteristicEntities = this.specialCharacteristicRepository.findAll();
    return specialCharacteristicEntities.stream().map(specialCharacteristicEntity -> DefaultSpecialCharacteristic
      .builder()
      .characteristicId(specialCharacteristicEntity.getSpecialCharacteristicId())
      .characteristicNameKk(specialCharacteristicEntity.getCharacteristicNameKk())
      .characteristicNameRu(specialCharacteristicEntity.getCharacteristicNameRu())
      .characteristicNameEn(specialCharacteristicEntity.getCharacteristicNameEn())
      .addInfo(specialCharacteristicEntity.getAddInfo())
      .build()).collect(Collectors.toList());
  }

  @Override
  public void addSpecialCharacteristic(
    String characteristicNameKk,
    String characteristicNameRu,
    String characteristicNameEn,
    String addInfo
  ) {
    SpecialCharacteristicEntity specialCharacteristicEntity = new SpecialCharacteristicEntity();
    specialCharacteristicEntity.setCharacteristicNameKk(characteristicNameKk);
    specialCharacteristicEntity.setCharacteristicNameRu(characteristicNameRu);
    specialCharacteristicEntity.setCharacteristicNameEn(characteristicNameEn);
    specialCharacteristicEntity.setAddInfo(addInfo);
    this.specialCharacteristicRepository.save(specialCharacteristicEntity);
    log.info("New special characteristic added, name: " + characteristicNameEn
      + ". Time: " + new Date());
  }

  @Override
  public void addSpecialCharacteristicJson(SpecialCharacteristicJson specialCharacteristicJson) {
    SpecialCharacteristicEntity specialCharacteristicEntity = new SpecialCharacteristicEntity();
    specialCharacteristicEntity.setCharacteristicNameKk(specialCharacteristicJson.getCharacteristicNameKk());
    specialCharacteristicEntity.setCharacteristicNameRu(specialCharacteristicJson.getCharacteristicNameRu());
    specialCharacteristicEntity.setCharacteristicNameEn(specialCharacteristicJson.getCharacteristicNameEn());
    specialCharacteristicEntity.setAddInfo(specialCharacteristicJson.getAddInfo());
    this.specialCharacteristicRepository.save(specialCharacteristicEntity);
    log.info("New special characteristic added via JSON, name: " + specialCharacteristicJson.getCharacteristicNameEn()
      + ". Time: " + new Date());
  }

  @Override
  public String updateCharacteristic(Long characteristicId, SpecialCharacteristicJson specialCharacteristicJson) {
    SpecialCharacteristicEntity specialCharacteristicEntity = this.specialCharacteristicRepository.findById
      (characteristicId).orElse(null);

    if (Objects.nonNull(specialCharacteristicEntity)) {
      if (specialCharacteristicJson.getCharacteristicNameKk() != null)
        specialCharacteristicEntity.setCharacteristicNameKk(specialCharacteristicJson.getCharacteristicNameKk());
      if (specialCharacteristicJson.getCharacteristicNameRu() != null)
        specialCharacteristicEntity.setCharacteristicNameRu(specialCharacteristicJson.getCharacteristicNameRu());
      if (specialCharacteristicJson.getCharacteristicNameEn() != null)
        specialCharacteristicEntity.setCharacteristicNameEn(specialCharacteristicJson.getCharacteristicNameEn());
      if (specialCharacteristicJson.getAddInfo() != null)
        specialCharacteristicEntity.setAddInfo(specialCharacteristicJson.getAddInfo());
      specialCharacteristicRepository.save(specialCharacteristicEntity);
      return "Характеристика обновлена";
    }
    return "Характеристики с данным ID не существует";
  }

  @Override
  public String deleteCharacteristic(Long characteristicId) {
    SpecialCharacteristicEntity specialCharacteristicEntity = this.specialCharacteristicRepository.findById
      (characteristicId).orElse(null);
    if (Objects.nonNull(specialCharacteristicEntity)) {
      this.specialCharacteristicRepository.delete(specialCharacteristicEntity);
      return "Характеристика удалена";
    }
    return "Характеристики с данным ID не существует";
  }
}
