package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> showAllProduct();

    void addCategory(String categoryNameKk, String categoryNameRu,
                     String categoryNameEn, String addInfo);
}
