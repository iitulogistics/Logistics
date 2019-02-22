package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.Product;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {

  void addProduct(String productNameKk, String productNameRu, String productNameEn,
                  String description, Long sellerCompanyId,
                  Long productSubCategoryId, Long specialCharacteristicsId, Long productCategoryId);

  void addProductJson(ProductJson productJson);

  List<Product> showAllProducts();

  DefaultProduct showProduct(Long productId);

  String updateProduct(Long productId, ProductJson productJson);

  String deleteProduct(Long productId);
}