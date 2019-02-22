package kz.logistic.pl.services.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    Long sellerCompanyId, Long productSubCategoryId, Long specialCharacteristicsId, Long productCategoryId) {
    ProductsEntity productsEntity = new ProductsEntity();
    productsEntity.setProductNameEn(productNameEn);
    productsEntity.setProductNameKk(productNameKk);
    productsEntity.setProductNameRu(productNameRu);
    productsEntity.setProductDescription(description);
    productsEntity.setSellerCompanyId(sellerCompanyId);
    productsEntity.setProductSubcategoryId(productSubCategoryId);
    productsEntity.setSpecialCharacteristicId(specialCharacteristicsId);
    productsEntity.setProductCategoryId(productCategoryId);
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
    productsEntity.setSpecialCharacteristicId(productJson.getSpecialCharacteristicsId());
    productsEntity.setProductSubcategoryId(productJson.getSubCategoryId());
    productsEntity.setProductCategoryId(productJson.getCategoryId());
    productRepository.save(productsEntity);
    log.info("New product added:\nName: "
      + productJson.getProductNameEn() + "\nseller company id: "
      + productJson.getSellerCompanyId());
  }

  @Override
  public List<Product> showAllProducts() {
    List<ProductsEntity> productsEntityList = this.productRepository.findAll();
    return productsEntityList.stream().map(productsEntity -> DefaultProduct.builder()
      .productId(productsEntity.getProductId())
      .productNameKk(productsEntity.getProductNameKk())
      .productNameRu(productsEntity.getProductNameRu())
      .productNameEn(productsEntity.getProductNameEn())
      .productDescription(productsEntity.getProductDescription())
      .sellerCompanyId(productsEntity.getSellerCompanyId())
      .build()).collect(Collectors.toList());
  }

  @Override
  public DefaultProduct showProduct(Long productId) {
    ProductsEntity productsEntity = this.productRepository.findById(productId).orElse(null);
    return DefaultProduct.builder()
      .productId(productsEntity.getProductId())
      .productNameEn(productsEntity.getProductNameEn())
      .productNameKk(productsEntity.getProductNameKk())
      .productNameRu(productsEntity.getProductNameRu())
      .productDescription(productsEntity.getProductDescription())
      .sellerCompanyId(productsEntity.getSellerCompanyId()).build();
  }

  @Override
  public String updateProduct(Long productId, ProductJson productJson) {
    ProductsEntity productsEntity = this.productRepository.findById(productId).orElse(null);
    if (Objects.nonNull(productsEntity)) {
      if (productJson.getProductNameEn() != null) {
        productsEntity.setProductNameEn(productJson.getProductNameEn());
      }
      if (productJson.getProductNameKk() != null) {
        productsEntity.setProductNameKk(productJson.getProductNameKk());
      }
      if (productJson.getProductNameRu() != null) {
        productsEntity.setProductNameRu(productJson.getProductNameRu());
      }
      if (productJson.getDescription() != null) {
        productsEntity.setProductDescription(productJson.getDescription());
      }
      if (productJson.getSellerCompanyId() != null) {
        productsEntity.setSellerCompanyId(productJson.getSellerCompanyId());
      }
      this.productRepository.save(productsEntity);
      log.info("Updated " + productsEntity.getProductNameEn() + " product" + new Date());
      return "Продукт обновлен";
    } else {
      return "Продукт с таким id не существует";
    }
  }

  @Override
  public String deleteProduct(Long productId) {
    ProductsEntity productsEntity = this.productRepository.findById(productId).orElse(null);
    if (Objects.nonNull(productsEntity)) {
      log.info("Deleted " + productsEntity.getProductNameEn() + " city" + new Date());
      this.productRepository.deleteById(productsEntity.getProductId());
      return "Продукт удален";
    } else {
      return "Продукт с таким id не существует";
    }
  }

}