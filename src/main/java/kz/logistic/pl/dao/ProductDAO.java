package kz.logistic.pl.dao;

import kz.logistic.pl.models.entities.ProductsEntity;
import kz.logistic.pl.models.pojos.json.ProductJson;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductDAO {
  List<ProductsEntity> getAllProduct();
  ProductsEntity getProductById(Long id);
  ProductsEntity addNewProduct(ProductsEntity product);
  void addProductExcel(MultipartFile multipartFile);
  List<ProductsEntity> getProductByName(String name);
  void addProduct(String productNameKk,
                  String productNameRu,
                  String productNameEn,
                  Long productCategoryId,
                  Long productSubcategoryId,
                  String uniqueIdNumber,
                  String serialNumber,
                  String manufacturer,
                  String size,
                  Integer weight,
                  Integer price,
                  String productDescription,
                  Long sellerCompanyId,
                  Long specialCharacteristicsId);

  void addProductJson(ProductJson productJson);
}
