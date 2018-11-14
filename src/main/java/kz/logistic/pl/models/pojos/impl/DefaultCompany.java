package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Company;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Builder;

@Builder
public class DefaultCompany implements Company {

  private Long id;
  private LocalizedMessage companyName;
  private String companyPhoneNumber;

  @Override
  public long getCompanyId() {
    return id;
  }

  @Override
  public LocalizedMessage getCompanyName() {
    return companyName;
  }

  @Override
  public String getCompanyPhoneNumber() {
    return companyPhoneNumber;
  }
}
