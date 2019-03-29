package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.Region;
import lombok.Builder;

@Builder
public class DefaultRegion implements Region {

  private Long regionId;
  private LocalizedMessage regionName;
  private Long countryId;

  @Override
  public Long getRegionId() {
    return regionId;
  }

  @Override
  public LocalizedMessage getRegionName() {
    return regionName;
  }

  @Override
  public Long getCountryId() {
    return countryId;
  }
}
