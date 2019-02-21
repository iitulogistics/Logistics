package kz.logistic.pl.models.pojos;

public interface Product {

  Long getProductId();

  String getProductNameKk();

  String getProductNameRu();

  String getProductNameEn();

  Long getProductCategoryId();

  Long getProductSubcategoryId();

  String getProductDescription();

  Integer getWeight();

  Integer getPrice();

  Long getSellerCompanyId();

  String getSerialNumber();

  String getUniqueIdNumber();

  String getManufacturer();

  Long getSpecialCharacteristicsId();

}