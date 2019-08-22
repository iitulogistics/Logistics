package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ShipperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShipperRepository extends JpaRepository<ShipperEntity, Long> {

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update ShipperEntity s set s.shipperNameEn = ?2, s.shipperNameKk = ?3, s.shipperNameRu = ?4," +
    "s.address = ?5, s.mobilePhone = ?6, s.phoneNumber = ?7, s.email = ?8, s.bin = ?9 where s.shipperId = ?1")
  void updateShipperById(long id, String shipperNameEn, String shipperNameKk, String shipperNameRu,
                         String address, String mobilePhone, String phoneNumber, String email,
                         String bin);
}
