package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Address;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Builder;

@Builder
public class DefaultAddress implements Address {

  private Long addressId;
  private LocalizedMessage streetName;
  private Long inhLocalityId;
  private Long districtId;
  private String buildingNumber;
  private String flatNumber;
  private String zipCode;
  private Integer addressAssign;

  @Override
  public Long getAddressId() {
    return addressId;
  }

  @Override
  public LocalizedMessage getStreetName() {
    return streetName;
  }

  @Override
  public Long getDistrictId() {
    return districtId;
  }

  @Override
  public Long getInhLocalityId() {
    return inhLocalityId;
  }

  @Override
  public String getBuildingNumber() {
    return buildingNumber;
  }

  @Override
  public String getFlatNumber() {
    return flatNumber;
  }

  @Override
  public String getZipCode() {
    return zipCode;
  }

  @Override
  public Integer getAddressAssign() {
    return addressAssign;
  }

}
