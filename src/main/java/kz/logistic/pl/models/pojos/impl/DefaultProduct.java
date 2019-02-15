package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DefaultProduct implements Product {

    private Long productId;
    private String productNameKk;
    private String productNameRu;
    private String productNameEn;
    private Long productCategoryId;
    private Long productSubcategoryId;
    private String uniqueIdNumber;
    private String serialNumber;
    private String manufacturer;
    private String size;
    private Integer weight;
    private Integer price;
    private String productDescription;
    private Long sellerCompanyId;
    private Long specialCharacteristicsId;

}