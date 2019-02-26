package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.AddressesEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Address;
import kz.logistic.pl.models.pojos.impl.DefaultAddress;
import kz.logistic.pl.models.pojos.json.AddressJson;
import kz.logistic.pl.repositories.AddressRepository;
import kz.logistic.pl.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultAddressService implements AddressService {

  private AddressRepository addressRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setAddressRepository(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<Address> showAllAddresses() {
    List<AddressesEntity> addressesEntities = this.addressRepository.findAll();
    return addressesEntities.stream().map(addressesEntity ->
      DefaultAddress.builder()
        .addressId(addressesEntity.getAddressId())
        .addressAssign(addressesEntity.getAddressAssign())
        .streetName(localizedMessageBuilderFactory.builder()
          .en(addressesEntity.getStreetNameEn())
          .kk(addressesEntity.getStreetNameKk())
          .ru(addressesEntity.getStreetNameRu()).build())
        .districtId(addressesEntity.getDistrictId())
        .buildingNumber(addressesEntity.getBuildingNumber())
        .flatNumber(addressesEntity.getFlatNumber())
        .inhLocalityId(addressesEntity.getIhnLocalityId())
        .zipCode(addressesEntity.getZipCode()).build()).collect(Collectors.toList());

  }

  public boolean exists(String streetNameEn, String buildingNumber, String flatNumber, String zipCode) {
    ArrayList<AddressesEntity> addressesEntities = this.addressRepository.findByStreetNameEnAndBuildingNumberAndFlatNumberAndZipCode(streetNameEn, buildingNumber, flatNumber, zipCode);
    return addressesEntities.size() > 0;
  }

  @Override
  public DefaultAddress showAddress(Long addressId) {
    AddressesEntity addressesEntity = this.addressRepository.findById(addressId).orElse(null);
    return DefaultAddress.builder().streetName(localizedMessageBuilderFactory.builder()
      .ru(addressesEntity.getStreetNameRu())
      .kk(addressesEntity.getStreetNameKk())
      .en(addressesEntity.getStreetNameEn()).build())
      .zipCode(addressesEntity.getZipCode())
      .inhLocalityId(addressesEntity.getIhnLocalityId())
      .flatNumber(addressesEntity.getFlatNumber())
      .buildingNumber(addressesEntity.getBuildingNumber())
      .districtId(addressesEntity.getDistrictId())
      .addressAssign(addressesEntity.getAddressAssign())
      .addressId(addressesEntity.getAddressId()).build();
  }

  @Override
  public String addAddress(String streetNameKk, String streetNameRu, String streetNameEn, Long inhLocalityId,
                           Long districtId, String buildingNumber, String flatNumber,
                           String zipCode, Integer addressAssign) {
    if (exists(streetNameEn, buildingNumber, flatNumber, zipCode)) {
      return "Этот адресс уже занят";
    }
    AddressesEntity addressesEntity = new AddressesEntity();
    addressesEntity.setStreetNameEn(streetNameEn);
    addressesEntity.setStreetNameKk(streetNameKk);
    addressesEntity.setStreetNameRu(streetNameRu);
    addressesEntity.setIhnLocalityId(inhLocalityId);
    addressesEntity.setAddressAssign(addressAssign);
    addressesEntity.setDistrictId(districtId);
    addressesEntity.setBuildingNumber(buildingNumber);
    addressesEntity.setFlatNumber(flatNumber);
    addressesEntity.setZipCode(zipCode);

    this.addressRepository.save(addressesEntity);
    log.info("Added new address " + streetNameEn + " " + new Date());
    return "Новый адрес добавлен";
  }

  @Override
  public String addAddressJson(AddressJson addressJson) {
    if (exists(addressJson.getStreetNameEn(), addressJson.getBuildingNumber(), addressJson.getFlatNumber(), addressJson.getZipCode())) {
      return "Этот адресс уже занят";
    }
    AddressesEntity addressesEntity = new AddressesEntity();
    addressesEntity.setStreetNameEn(addressJson.getStreetNameEn());
    addressesEntity.setStreetNameKk(addressJson.getStreetNameKk());
    addressesEntity.setStreetNameRu(addressJson.getStreetNameRu());
    addressesEntity.setIhnLocalityId(addressJson.getInhLocalityId());
    addressesEntity.setAddressAssign(addressJson.getAddressAssign());
    addressesEntity.setDistrictId(addressJson.getDistrictId());
    addressesEntity.setBuildingNumber(addressJson.getBuildingNumber());
    addressesEntity.setFlatNumber(addressJson.getFlatNumber());
    addressesEntity.setZipCode(addressJson.getZipCode());

    this.addressRepository.save(addressesEntity);
    log.info("Added new address " + addressJson.getStreetNameEn() + " " + new Date());
    return "Новый адрес добавлен посредством JSON";
  }

  @Override
  public String updateAddress(Long addressId, AddressJson addressJson) {
    AddressesEntity addressesEntity = this.addressRepository.findById(addressId).orElse(null);
    if (Objects.nonNull(addressesEntity)) {
      if (addressJson.getStreetNameEn() != null) {
        addressesEntity.setStreetNameEn(addressJson.getStreetNameEn());
      }
      if (addressJson.getStreetNameRu() != null) {
        addressesEntity.setStreetNameRu(addressJson.getStreetNameRu());
      }
      if (addressJson.getStreetNameKk() != null) {
        addressesEntity.setStreetNameKk(addressJson.getStreetNameKk());
      }
      if (addressJson.getAddressAssign() != null) {
        addressesEntity.setAddressAssign(addressJson.getAddressAssign());
      }
      if (addressJson.getBuildingNumber() != null) {
        addressesEntity.setBuildingNumber(addressJson.getBuildingNumber());
      }
      if (addressJson.getDistrictId() != null) {
        addressesEntity.setDistrictId(addressJson.getDistrictId());
      }
      if (addressJson.getFlatNumber() != null) {
        addressesEntity.setFlatNumber(addressJson.getFlatNumber());
      }
      if (addressJson.getInhLocalityId() != null) {
        addressesEntity.setIhnLocalityId(addressJson.getInhLocalityId());
      }
      if (addressJson.getZipCode() != null) {
        addressesEntity.setZipCode(addressJson.getZipCode());
      }
      this.addressRepository.save(addressesEntity);
      log.info("Updated " + addressJson.getStreetNameEn() + " " + new Date());
      return "Адрес обнавлен";
    } else {
      return "Адрес с таким id не существует";
    }
  }

  @Override
  public String deleteAddress(Long addressId) {
    AddressesEntity addressesEntity = this.addressRepository.findById(addressId).orElse(null);
    if (Objects.nonNull(addressesEntity)) {
      this.addressRepository.deleteById(addressesEntity.getAddressId());
      log.info("Deleted " + addressesEntity.getStreetNameEn() + " address" + new Date());
      return "Адрес успешно удален";
    } else {
      return "Адрес с таким id не существует";
    }
  }
}
