package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import kz.logistic.pl.models.entities.ProductsSubCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.ProductSubCategory;
import kz.logistic.pl.models.pojos.impl.DefaultProductSubCategory;
import kz.logistic.pl.models.pojos.json.ProductSubCategoryJson;
import kz.logistic.pl.repositories.ProductSubCategoryRepository;
import kz.logistic.pl.services.ProductSubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class DefaultProductSubCategoryService implements ProductSubCategoryService {

  private ProductSubCategoryRepository productSubCategoryRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setProductSubCategoryRepository(
    ProductSubCategoryRepository productSubCategoryRepository) {
    this.productSubCategoryRepository = productSubCategoryRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
    LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<ProductSubCategory> showAllProductSubCategory() {
    List<ProductsSubCategoryEntity> entities = this.productSubCategoryRepository.findAll();

    return entities.stream().map(productsSubCategoryEntity -> DefaultProductSubCategory.builder()
      .id(productsSubCategoryEntity.getProductSubcategoryId())
      .subCategoryName(
        localizedMessageBuilderFactory.builder()
          .kk(productsSubCategoryEntity.getSubCategoryNameKk())
          .ru(productsSubCategoryEntity.getSubCategoryNameRu())
          .en(productsSubCategoryEntity.getSubCategoryNameEn()).build())
      .productCategoryId(productsSubCategoryEntity.getProductCategoryId())
      .subCategoryAddInfo(productsSubCategoryEntity.getSubCategoryAddInfo())
      .build()).collect(Collectors.toList());
  }


  @Override
  public void addProductSubCategory(String subSubCategoryNameKk, String subSubCategoryNameRu,
                                    String subSubCategoryNameEn, Long productCategoryId,
                                    String subSubCategoryAddInfo) {
    ProductsSubCategoryEntity subCategoryEntity = new ProductsSubCategoryEntity();
    subCategoryEntity.setSubCategoryNameKk(subSubCategoryNameKk);
    subCategoryEntity.setSubCategoryNameRu(subSubCategoryNameRu);
    subCategoryEntity.setSubCategoryNameEn(subSubCategoryNameEn);
    subCategoryEntity.setProductCategoryId(productCategoryId);
    subCategoryEntity.setSubCategoryAddInfo(subSubCategoryAddInfo);

    this.productSubCategoryRepository.save(subCategoryEntity);
    log.info("Added new ProductSubCategory: " + subSubCategoryNameRu + " " + new Date());
  }

  @Override
  public void addProductSubCategoryJson(ProductSubCategoryJson productSubCategoryJson) {
    ProductsSubCategoryEntity subCategoryEntity = new ProductsSubCategoryEntity();
    subCategoryEntity.setSubCategoryNameKk(productSubCategoryJson.getSubCategoryNameKk());
    subCategoryEntity.setSubCategoryNameRu(productSubCategoryJson.getSubCategoryNameRu());
    subCategoryEntity.setSubCategoryNameEn(productSubCategoryJson.getSubCategoryNameEn());
    subCategoryEntity.setProductCategoryId(productSubCategoryJson.getProductCategoryId());
    subCategoryEntity.setSubCategoryAddInfo(productSubCategoryJson.getSubCategoryAddInfo());

    this.productSubCategoryRepository.save(subCategoryEntity);
    log.info("Added new ProductSubCategory: "
      + productSubCategoryJson.getSubCategoryNameRu() + " via Json " + new Date());
  }

  @Override
  public String updateProductSubCategory(
    Long productSubCategoryId, ProductSubCategoryJson productSubCategoryJson) {
    ProductsSubCategoryEntity productsSubCategoryEntity =
      this.productSubCategoryRepository.findById(productSubCategoryId).orElse(null);

    if (Objects.nonNull(productsSubCategoryEntity)) {
      if (productSubCategoryJson.getSubCategoryNameKk() != null) {
        productsSubCategoryEntity.setSubCategoryNameKk(productSubCategoryJson.getSubCategoryNameKk());
      }
      if (productSubCategoryJson.getSubCategoryNameRu() != null) {
        productsSubCategoryEntity.setSubCategoryNameRu(productSubCategoryJson.getSubCategoryNameRu());
      }
      if (productSubCategoryJson.getSubCategoryNameEn() != null) {
        productsSubCategoryEntity.setSubCategoryNameEn(productSubCategoryJson.getSubCategoryNameEn());
      }
      if (productSubCategoryJson.getSubCategoryAddInfo() != null) {
        productsSubCategoryEntity.setSubCategoryAddInfo(productSubCategoryJson.getSubCategoryAddInfo());
      }
      if (productSubCategoryJson.getProductCategoryId() != null){
        productsSubCategoryEntity.setProductCategoryId(productSubCategoryJson.getProductCategoryId());
      }
      this.productSubCategoryRepository.save(productsSubCategoryEntity);
      log.info("Updated  product category " + new Date());
      return "Подкатегория продуктов обновлена";
    } else {
      return "Подкатегория продуктов с таким id не существует";
    }
  }

  @Override
  public String deleteProductSubCategory(Long productCategoryId) {
    ProductsSubCategoryEntity productsSubCategoryEntity =
      this.productSubCategoryRepository.findById(productCategoryId).orElse(null);

    if (Objects.nonNull(productsSubCategoryEntity)) {
      log.info("Deleted " + productsSubCategoryEntity.getSubCategoryNameRu() + " category " + new Date());
      this.productSubCategoryRepository.delete(productsSubCategoryEntity);
      return "Подкатегория продукта удалена";
    } else {
      return "Подкатегория продукта с таким id не существует";
    }
  }

    @Override
    public ProductSubCategory showProductSubCategory(Long productSubCategoryId) {
      ProductsSubCategoryEntity productsSubCategoryEntity =
        this.productSubCategoryRepository.findById(productSubCategoryId).orElse(null);
      return DefaultProductSubCategory.builder()
        .id(productSubCategoryId)
        .subCategoryName(localizedMessageBuilderFactory.builder()
          .en(productsSubCategoryEntity.getSubCategoryNameEn())
          .kk(productsSubCategoryEntity.getSubCategoryNameKk())
          .ru(productsSubCategoryEntity.getSubCategoryNameRu()).build())
        .productCategoryId(productsSubCategoryEntity.getProductCategoryId())
        .subCategoryAddInfo(productsSubCategoryEntity.getSubCategoryAddInfo()).build();
    }


}
