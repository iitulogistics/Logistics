package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.ProductSubCategory;
import kz.logistic.pl.models.pojos.json.ProductSubCategoryJson;


public interface ProductSubCategoryService {

  //Показать  все подкатеогрии
  List<ProductSubCategory> showAllProductSubCategory();

  //Добавить новую подкатегорию
  void addProductSubCategory(String subCategoryNameKk, String subCategoryNameRu,
                             String subCategoryNameEn, Long productCategoryId,
                             String subCategoryAddInfo);

  void addProductSubCategoryJson(ProductSubCategoryJson productSubCategoryJson);

  String updateProductSubCategory(
    Long productSubCategoryId, ProductSubCategoryJson productSubCategoryJson);

  String deleteProductSubCategory(Long productCategoryId);

  ProductSubCategory showProductSubCategory(Long productSubCategoryId);

}
