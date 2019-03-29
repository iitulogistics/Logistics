package kz.logistic.pl.repositories;


import kz.logistic.pl.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}

import kz.logistic.pl.models.entities.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AddressRepository extends JpaRepository<AddressesEntity, Long> {

  ArrayList<AddressesEntity> findByStreetNameEnAndBuildingNumberAndFlatNumberAndZipCode(
    String streetNameEn, String buildingNumber, String flatNumber, String zipCode);

}
>>>>>>> b19471c914f3645b9a6ffbf25fd63fbd4ff38cf2
