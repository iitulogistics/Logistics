package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.ProductSubCategory;

import java.util.List;

public interface ProductSubCategoryService {

    //Показать  все подкатеогрии
    List<ProductSubCategory> showAllProductSubCategory();

    //Добавить новую подкатегорию
    void addProductSubCategory(String subCategoryNameKk, String subCategoryNameRu,
                               String subCategoryNameEn, Integer productCategoryId,
                               String subCategoryAddInfo);
}
