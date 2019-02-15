package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Product;
import kz.logistic.pl.models.pojos.json.ProductJson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void addProduct(String productNameKk, String productNameRu, String productNameEn, String description, Long sellerCompanyId);

    void addProductJson(ProductJson productJson);

    List<Product> showAllProducts();
}