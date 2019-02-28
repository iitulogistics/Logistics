package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.District;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Builder;

@Builder
public class DefaultDistrict implements District {

  private Long districtId;
  private LocalizedMessage districtName;
  private Long regionId;
  private Long cityId;

  @Override
  public Long getDistrictId() {
    return districtId;
  }

  @Override
  public LocalizedMessage getDistrictName() {
    return districtName;
  }

  @Override
  public Long getRegionId() {
    return regionId;
  }

  @Override
  public Long getCityId() {
    return cityId;
  }

}
