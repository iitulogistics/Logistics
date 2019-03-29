package kz.logistic.pl.services;


import kz.logistic.pl.models.pojos.Address;
import kz.logistic.pl.models.pojos.impl.DefaultAddress;
import kz.logistic.pl.models.pojos.json.AddressJson;

import java.util.List;

public interface AddressService {

  List<Address> showAllAddresses();

  DefaultAddress showAddress(Long addressId);

  String addAddress(String streetNameKk, String streetNameRu, String streetNameEn,
                    Long inhLocalityId, Long districtId, String buildingNumber, String flatNumber,
                    String zipCode, Integer addressAssign);

  String addAddressJson(AddressJson addressJson);

  String updateAddress(Long addressId, AddressJson addressJson);

  String deleteAddress(Long addressId);

}
