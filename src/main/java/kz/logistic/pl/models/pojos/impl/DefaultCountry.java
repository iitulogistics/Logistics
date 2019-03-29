package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Country;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Builder;

@Builder
public class DefaultCountry implements Country {

  private Long countryId;
  private LocalizedMessage countryName;

  @Override
  public Long getCountryId() {
    return countryId;
  }

  @Override
  public LocalizedMessage getCountryName() {
    return countryName;
  }

}
