package kz.logistic.pl.repositories;


import kz.logistic.pl.models.entities.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface AddressRepository extends JpaRepository<AddressesEntity, Long> {

  @Query("select address from AddressesEntity address where address.streetNameEn = ?1 " +
    "and address.buildingNumber = ?2 and address.flatNumber = ?3 and address.zipCode = ?4")
  ArrayList<AddressesEntity> findLocation(
    String streetNameEn, String buildingNumber, String flatNumber, String zipCode);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update AddressesEntity a set a.districtId = ?2, a.ihnLocalityId = ?3, a.streetNameKk =?4, " +
    "a.streetNameRu =?5, a.streetNameEn =?6, a.buildingNumber =?7, " +
    "a.flatNumber =?8, a.zipCode =?9, a.addressAssign =?10 " +
    "where a.addressId =?1")
  void updateAddressById(Long id, long districtId, long ihnLocalityId,
                         String streetNameKk, String streetNameRu, String streetNameEn,
                         String buildingNumber, String flatNumber, String zipCode,
                         Integer addressAssign);
}

