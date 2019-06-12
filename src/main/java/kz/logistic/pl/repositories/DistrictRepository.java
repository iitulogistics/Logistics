package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {

    @Modifying
    @Transactional
    @Query("update DistrictEntity d set d.districtNameEn = ?2, d.districtNameRu = ?3, d.districtNameKk = ?4, d.districtId = ?1")
    void updateDistrictById(Long id, String nameEn, String nameRu, String nameKk);
}
