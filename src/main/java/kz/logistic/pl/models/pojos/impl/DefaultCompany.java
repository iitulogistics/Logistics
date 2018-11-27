package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Company;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Builder;

@Builder
public class DefaultCompany implements Company {

  private Long id;
  private LocalizedMessage companyName;
  private String companyPhoneNumber;
  private String mobilePhoneNumber;
  private String email;
  private String bin;

  @Override
  public String getBin() {
    return bin;
  }

  @Override
  public String getMobilePhone() {
    return mobilePhoneNumber;
  }

  @Override
  public String getEmail() {
    return email;
  }

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
