package kz.logistic.pl.models.pojos.impl;

import java.util.Objects;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.SellerCompany;
import lombok.Builder;


@Builder
public class DefaultSellerCompany implements SellerCompany {

  private Long sellerCompanyId;
  private LocalizedMessage sellerCompanyName;
  private String sellerCompanyPhone;
  private String sellerCompanyMobilePhone;
  private String sellerCompanyBin;
  private String sellerCompanyEmail;
  private Long sellerCategoryId;
  private String username;
  private String password;
  private Long loginId;

  @Override
  public Long getSellerCompanyId() {
    return sellerCompanyId;
  }

  @Override
  public LocalizedMessage getSellerCompanyName() {
    return sellerCompanyName;
  }

  @Override
  public String getSellerCompanyPhone() {
    return sellerCompanyPhone;
  }

  @Override
  public String getSellerCompanyMobilePhone() {
    return sellerCompanyMobilePhone;
  }

  @Override
  public String getSellerCompanyBin() {
    return sellerCompanyBin;
  }

  @Override
  public String getSellerCompanyEmail() {
    return sellerCompanyEmail;
  }

  @Override
  public Long getLoginId() {
    return loginId;
  }

  @Override
  public String getLoginName() {
    return username;
  }

  @Override
  public String getLoginPassword() {
    return password;
  }

  @Override
  public long getSellerCategoryId() {
    if (Objects.isNull(sellerCategoryId)) {
      return 0;
    } else {
      return sellerCategoryId;
    }
  }
}
