package kz.logistic.pl.models.pojos;

public interface Address {

  Long getAddressId();

  LocalizedMessage getStreetName();

  Long getDistrictId();

  Long getInhLocalityId();

  String getBuildingNumber();

  String getFlatNumber();

  String getZipCode();

  Integer getAddressAssign();

}
