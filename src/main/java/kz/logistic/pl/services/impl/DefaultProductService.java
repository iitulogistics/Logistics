package kz.logistic.pl.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import kz.logistic.pl.models.entities.ProductsEntity;
import kz.logistic.pl.models.pojos.Product;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import kz.logistic.pl.repositories.ProductRepository;
import kz.logistic.pl.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class DefaultProductService implements ProductService {

  private ProductRepository productRepository;

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void addProduct(
    String productNameKk, String productNameRu, String productNameEn, String description,
    Long sellerCompanyId) {
    ProductsEntity productsEntity = new ProductsEntity();
    productsEntity.setProductNameEn(productNameEn);
    productsEntity.setProductNameKk(productNameKk);
    productsEntity.setProductNameRu(productNameRu);
    productsEntity.setProductDescription(description);
    productsEntity.setSellerCompanyId(sellerCompanyId);
    productRepository.save(productsEntity);
    log.info("New product added:\nName: "
      + productNameEn + "\nseller company id: " + sellerCompanyId);
  }

  @Override
  public void addProductJson(ProductJson productJson) {
    ProductsEntity productsEntity = new ProductsEntity();
    productsEntity.setProductNameKk(productJson.getProductNameKk());
    productsEntity.setProductNameRu(productJson.getProductNameRu());
    productsEntity.setProductNameEn(productJson.getProductNameEn());
    productsEntity.setProductDescription(productJson.getDescription());
    productsEntity.setSellerCompanyId(productJson.getSellerCompanyId());
    productRepository.save(productsEntity);
    log.info("New product added:\nName: "
      + productJson.getProductNameEn() + "\nseller company id: "
      + productJson.getSellerCompanyId());
  }

  @Override
  public List<Product> showAllProducts() {
    List<ProductsEntity> productsEntityList = new ArrayList<ProductsEntity>();
    return productsEntityList.stream().map(productsEntity -> DefaultProduct.builder()
      .productId(productsEntity.getProductId())
      .productNameKk(productsEntity.getProductNameKk())
      .productNameRu(productsEntity.getProductNameRu())
      .productNameEn(productsEntity.getProductNameEn())
      .productDescription(productsEntity.getProductDescription())
      .sellerCompanyId(productsEntity.getSellerCompanyId())
      .build()).collect(Collectors.toList());
  }
}