package kz.logistic.pl.services;

import java.io.IOException;
import java.util.List;

import kz.logistic.pl.models.pojos.Product;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface ProductService {

    String addProduct(String productNameKk,
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

    String addProductJson(ProductJson productJson);

    List<Product> showAllProducts();

    List<Product> showProductsByCategoryId(Long productCategoryId);

    DefaultProduct showProduct(Long productId);

    String updateProduct(Long productId, ProductJson productJson);

    String deleteProduct(Long productId);

    String addPhoto(Long id, MultipartFile file);

    byte[] getPhoto(String name) throws IOException;

}