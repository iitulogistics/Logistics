package kz.logistic.pl.soap.address;

import kz.logistic.pl.models.entities.AddressesEntity;
import kz.logistic.pl.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.address.Address;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddressRepositorySoap {
  private static final Map<Long, Address> addressMap = new HashMap<>();

  private AddressRepository addressRepository;

  @Autowired(required = false)
  public void setAddressRepository(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @PostConstruct
  public void initData() {
    List<AddressesEntity> entities = this.addressRepository.findAll();
    entities.forEach(addressEntity -> {
      Address address = convertToAddress(addressEntity);
      addressMap.put(address.getAddressId(), address);
    });
  }

  public Address findAddressId(Long id) {
    return addressMap.get(id);
  }

  public Address addAddress(long districtId,
                            long ihnLocalityId,
                            String streetNameKk,
                            String streetNameRu,
                            String streetNameEn,
                            String buildingNumber,
                            String flatNumber,
                            String zipCode,
                            Integer addressAssign) {
    AddressesEntity addressesEntity = new AddressesEntity();
    addressesEntity.setAddressAssign(addressAssign);
    addressesEntity.setBuildingNumber(buildingNumber);
    addressesEntity.setDistrictId(districtId);
    addressesEntity.setFlatNumber(flatNumber);
    addressesEntity.setIhnLocalityId(ihnLocalityId);
    addressesEntity.setStreetNameEn(streetNameEn);
    addressesEntity.setStreetNameRu(streetNameRu);
    addressesEntity.setStreetNameKk(streetNameKk);
    addressesEntity.setZipCode(zipCode);
    addressRepository.save(addressesEntity);

    Address address = convertToAddress(addressesEntity);
    addressMap.put(address.getAddressId(), address);

    return address;
  }

  public Address updateAddress(long id,
                               long districtId,
                               long ihnLocalityId,
                               String streetNameKk,
                               String streetNameRu,
                               String streetNameEn,
                               String buildingNumber,
                               String flatNumber,
                               String zipCode,
                               Integer addressAssign) {
    Address address = addressMap.get(id);
    address.setAddressAssign(addressAssign);
    address.setBuildingNumber(buildingNumber);
    address.setDistrictId(districtId);
    address.setFlatNumber(flatNumber);
    address.setIhnLocalityId(ihnLocalityId);
    address.setStreetNameEn(streetNameEn);
    address.setStreetNameRu(streetNameRu);
    address.setStreetNameKk(streetNameKk);
    address.setZipCode(zipCode);

    addressRepository.updateAddressById(id, districtId, ihnLocalityId, streetNameKk,
      streetNameRu, streetNameEn, buildingNumber,
      flatNumber, zipCode, addressAssign);

    return address;
  }

  public String deleteAddressId(Long id) {
    AddressesEntity addressesEntity = addressRepository.findById(id).orElse(null);

    if (addressesEntity != null) {
      this.addressRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Address convertToAddress(AddressesEntity entity) {
    Address address = new Address();
    address.setAddressId(entity.getAddressId());
    address.setAddressAssign(entity.getAddressAssign());
    address.setBuildingNumber(entity.getBuildingNumber());
    address.setDistrictId(entity.getDistrictId());
    address.setFlatNumber(entity.getFlatNumber());
    address.setIhnLocalityId(entity.getIhnLocalityId());
    address.setStreetNameEn(entity.getStreetNameEn());
    address.setStreetNameRu(entity.getStreetNameRu());
    address.setStreetNameKk(entity.getStreetNameKk());
    address.setZipCode(entity.getZipCode());

    return address;
  }

}
