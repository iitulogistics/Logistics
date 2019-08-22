package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.SpecialCharacteristicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SpecialCharacteristicRepository extends JpaRepository<SpecialCharacteristicEntity, Long> {

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update SpecialCharacteristicEntity s set s.characteristicNameEn = ?2, s.characteristicNameRu = ?3," +
    "s.characteristicNameKk = ?4, s.addInfo = ?5 where s.specialCharacteristicId = ?1")
  void updateSpecialCharacteristicById(long id, String characteristicNameEn, String characteristicNameRu,
                                       String characteristicNameKk, String addInfo);
}
