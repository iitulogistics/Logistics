package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Product;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {

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

  void addProductExcel(MultipartFile multipartFile);

  List<Product> showAllProducts();

  List<Product> showProductBySeller(Long sellerCompanyId);

  List<Product> showProductByCategoryId(Long productCategoryId);

  List<Product> showProductBySubCategoryId(Long productSubCategoryId);

  DefaultProduct showProduct(Long productId);

  String updateProduct(Long productId, ProductJson productJson);

  String deleteProduct(Long productId);

  String addPhoto(Long id, MultipartFile file);

  byte[] getPhoto(String name) throws IOException;

  List<Product> getProductsByName(String name);

  List<Product> getProductsByCategoryId(Long id);
}