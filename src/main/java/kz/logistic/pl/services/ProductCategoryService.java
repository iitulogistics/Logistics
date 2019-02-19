package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.json.ProductCategoryJson;


public interface ProductCategoryService {

  List<ProductCategory> showAllProduct();

  void addCategory(String categoryNameKk, String categoryNameRu,
                   String categoryNameEn, String addInfo);

  void addCategoryJson(ProductCategoryJson productCategoryJson);
}
