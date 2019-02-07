package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.json.ProductCategoryJson;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> showAllProduct();

    void addCategory(String categoryNameKk, String categoryNameRu,
                     String categoryNameEn, String addInfo);

    void addCategoryJson(ProductCategoryJson productCategoryJson);
}
