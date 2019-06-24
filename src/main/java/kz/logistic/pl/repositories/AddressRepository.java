package kz.logistic.pl.repositories;


import kz.logistic.pl.models.entities.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public interface AddressRepository extends JpaRepository<AddressesEntity, Long> {

  @Query("select address from AddressesEntity address where address.streetNameEn = ?1 " +
    "and address.buildingNumber = ?2 and address.flatNumber = ?3 and address.zipCode = ?4")
  ArrayList<AddressesEntity> findLocation(
    String streetNameEn, String buildingNumber, String flatNumber, String zipCode);

}

