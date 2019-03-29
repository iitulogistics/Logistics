package kz.logistic.pl.services;

import java.util.List;

import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.impl.DefaultProductCategory;
import kz.logistic.pl.models.pojos.json.ProductCategoryJson;


public interface ProductCategoryService {

  List<ProductCategory> showAllProduct();

  DefaultProductCategory showProductCategory(Long productCategoryId);

  String addCategory(String categoryNameKk, String categoryNameRu,
                   String categoryNameEn, String addInfo);

  String  addCategoryJson(ProductCategoryJson productCategoryJson);

  String updateProductCategory(Long productCategoryId, ProductCategoryJson productCategoryJson);

  String deleteProductCategory(Long productCategoryId);
}
