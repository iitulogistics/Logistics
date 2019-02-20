package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.ProductCategory;
import lombok.Builder;

@Builder
public class DefaultProductCategory implements ProductCategory {
  private Long id;
  private LocalizedMessage categoryName;
  private String addInfo;

  @Override
  public long getProductCategoryId() {
    return id;
  }

  @Override
  public LocalizedMessage getCategoryName() {
    return categoryName;
  }

  @Override
  public String getAddInfo() {
    return addInfo;
  }
}
