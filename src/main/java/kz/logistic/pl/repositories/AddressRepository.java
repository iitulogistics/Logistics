package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AddressRepository extends JpaRepository<AddressesEntity, Long> {

  ArrayList<AddressesEntity> findByStreetNameEnAndBuildingNumberAndFlatNumberAndZipCode(
    String streetNameEn, String buildingNumber, String flatNumber, String zipCode);

}