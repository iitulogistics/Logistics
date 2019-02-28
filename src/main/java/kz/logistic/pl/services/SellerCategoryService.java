package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.SellerCategory;
import kz.logistic.pl.models.pojos.impl.DefaultSellerCategory;
import kz.logistic.pl.models.pojos.json.SellerCategoryJson;

import java.util.List;

public interface SellerCategoryService {

  List<SellerCategory> showAllSellerCategories();

  DefaultSellerCategory showSellerCategory(Long sellerCategoryId);


  String addSellerCategory(
    String sellerCategoryNameKk, String sellerCategoryNameRu,
    String sellerCategoryNameEn, String addInfo);

  String addSellerCategoryJson(SellerCategoryJson sellerCategoryJson);

  String updateSellerCategory(Long sellerCategoryId, SellerCategoryJson sellerCategoryJson);

  String deleteSellerCategory(Long sellerCategoryId);

}
