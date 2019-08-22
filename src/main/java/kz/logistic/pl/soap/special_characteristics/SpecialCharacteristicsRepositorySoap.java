package kz.logistic.pl.soap.special_characteristics;

import kz.logistic.pl.models.entities.SpecialCharacteristicEntity;
import kz.logistic.pl.repositories.SpecialCharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.special_characteristics.SpecialCharacteristics;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpecialCharacteristicsRepositorySoap {
  private static final Map<Long, SpecialCharacteristics> characteristicsMap = new HashMap<>();

  private SpecialCharacteristicRepository characteristicsRepository;

  @Autowired(required = false)
  public void setSpecialCharacteristicsRepository(SpecialCharacteristicRepository characteristicsRepository) {
    this.characteristicsRepository = characteristicsRepository;
  }

  @PostConstruct
  public void initData() {
    List<SpecialCharacteristicEntity> entities = this.characteristicsRepository.findAll();
    entities.forEach(characteristicsEntity -> {
      SpecialCharacteristics characteristics = convertToSpecialCharacteristics(characteristicsEntity);

      characteristicsMap.put(characteristics.getSpecialCharacteristicId(), characteristics);
    });
  }

  public SpecialCharacteristics findSpecialCharacteristicsId(Long id) {
    return characteristicsMap.get(id);
  }

  public SpecialCharacteristics addSpecialCharacteristics(String characteristicNameEn, String characteristicNameRu,
                                                          String characteristicNameKk, String addInfo) {
    SpecialCharacteristicEntity characteristicssEntity = new SpecialCharacteristicEntity();
    characteristicssEntity.setCharacteristicNameEn(characteristicNameEn);
    characteristicssEntity.setCharacteristicNameRu(characteristicNameRu);
    characteristicssEntity.setCharacteristicNameKk(characteristicNameKk);
    characteristicssEntity.setAddInfo(addInfo);

    characteristicsRepository.save(characteristicssEntity);

    SpecialCharacteristics characteristics = convertToSpecialCharacteristics(characteristicssEntity);
    characteristicsMap.put(characteristics.getSpecialCharacteristicId(), characteristics);

    return characteristics;
  }

  public SpecialCharacteristics updateSpecialCharacteristics(long id, String characteristicNameEn,
                                                             String characteristicNameRu,
                                                             String characteristicNameKk,
                                                             String addInfo) {
    SpecialCharacteristics characteristics = characteristicsMap.get(id);
    characteristics.setCharacteristicsNameEn(characteristicNameEn);
    characteristics.setCharacteristicsNameRu(characteristicNameRu);
    characteristics.setCharacteristicsNameKk(characteristicNameKk);
    characteristics.setAddInfo(addInfo);

    characteristicsRepository.updateSpecialCharacteristicById(id, characteristicNameEn,
      characteristicNameRu, characteristicNameKk, addInfo);
    return characteristics;
  }

  public String deleteSpecialCharacteristics(Long id) {
    SpecialCharacteristicEntity characteristicssEntity = this.characteristicsRepository.findById(id).orElse(null);

    if (characteristicssEntity != null) {
      this.characteristicsRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private SpecialCharacteristics convertToSpecialCharacteristics(SpecialCharacteristicEntity entity) {
    SpecialCharacteristics characteristics = new SpecialCharacteristics();
    characteristics.setSpecialCharacteristicId(entity.getSpecialCharacteristicId());
    characteristics.setCharacteristicsNameEn(entity.getCharacteristicNameEn());
    characteristics.setCharacteristicsNameRu(entity.getCharacteristicNameRu());
    characteristics.setCharacteristicsNameKk(entity.getCharacteristicNameKk());
    characteristics.setAddInfo(entity.getAddInfo());

    return characteristics;
  }
}
