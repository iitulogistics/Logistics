package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.SellerCategory;
import lombok.Builder;

@Builder
public class DefaultSellerCategory implements SellerCategory {

  private Long sellerCategoryId;
  private LocalizedMessage sellerCategoryName;
  private String addInfo;

  @Override
  public Long getSellerCategoryId() {
    return sellerCategoryId;
  }

  @Override
  public LocalizedMessage getSellerCategoryName() {
    return sellerCategoryName;
  }

  @Override
  public String getSellerCategoryAddInfo() {
    return addInfo;
  }
}
