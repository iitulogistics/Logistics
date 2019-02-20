package kz.logistic.pl.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.impl.DefaultProductCategory;
import kz.logistic.pl.models.pojos.json.ProductCategoryJson;
import kz.logistic.pl.repositories.ProductsCategoryRepository;
import kz.logistic.pl.services.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;



@Slf4j
public class DefaultProductCategoryService implements ProductCategoryService {

  private ProductsCategoryRepository productsCategoryRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setProductsCategoryRepository(ProductsCategoryRepository productsCategoryRepository) {
    this.productsCategoryRepository = productsCategoryRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(
      LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<ProductCategory> showAllProduct() {
    List<ProductsCategoryEntity> entities = this.productsCategoryRepository.findAll();

    return entities.stream().map(productsCategoryEntity -> DefaultProductCategory.builder()
      .id(productsCategoryEntity.getProductCategoryId())
      .categoryName(localizedMessageBuilderFactory.builder()
        .kk(productsCategoryEntity.getCategoryNameKk())
        .ru(productsCategoryEntity.getCategoryNameRu())
        .en(productsCategoryEntity.getCategoryNameEn()).build())
      .addInfo(productsCategoryEntity.getAddInfo())
      .build()).collect(Collectors.toList());
  }


  @Override
  public DefaultProductCategory showProductCategory(Long productCategoryId) {
    ProductsCategoryEntity productsCategoryEntity =
        this.productsCategoryRepository.findById(productCategoryId).orElse(null);
      return DefaultProductCategory.builder()
        .id(productsCategoryEntity.getProductCategoryId())
        .categoryName(localizedMessageBuilderFactory.builder()
          .en(productsCategoryEntity.getCategoryNameEn())
          .kk(productsCategoryEntity.getCategoryNameKk())
          .ru(productsCategoryEntity.getCategoryNameRu()).build())
        .addInfo(productsCategoryEntity.getAddInfo()).build();
  }

  @Override
  public String addCategory(
      String categoryNameKk, String categoryNameRu, String categoryNameEn, String addInfo) {
    ProductsCategoryEntity categoryEntity = new ProductsCategoryEntity();
    categoryEntity.setCategoryNameKk(categoryNameKk);
    categoryEntity.setCategoryNameRu(categoryNameRu);
    categoryEntity.setCategoryNameEn(categoryNameEn);
    categoryEntity.setAddInfo(addInfo);

    this.productsCategoryRepository.save(categoryEntity);
    log.info("Added new ProductCategory " + categoryNameRu + " " + new Date());
    return "Новая категория продуктов добавлена";
  }

  @Override
  public String addCategoryJson(ProductCategoryJson productCategoryJson) {
    ProductsCategoryEntity categoryEntity = new ProductsCategoryEntity();
    categoryEntity.setCategoryNameKk(productCategoryJson.getCategoryNameKk());
    categoryEntity.setCategoryNameRu(productCategoryJson.getCategoryNameRu());
    categoryEntity.setCategoryNameEn(productCategoryJson.getCategoryNameEn());
    categoryEntity.setAddInfo(productCategoryJson.getAddInfo());

    this.productsCategoryRepository.save(categoryEntity);
    log.info("Added new ProductCategory "
        + productCategoryJson.getCategoryNameRu() + " via JSON " + new Date());
    return "Новая категория продуктов добавлена посредством JSON";
  }


  @Override
  public String updateProductCategory(
      Long productCategoryId, ProductCategoryJson productCategoryJson) {
    ProductsCategoryEntity productsCategoryEntity =
        this.productsCategoryRepository.findById(productCategoryId).orElse(null);

    if (Objects.nonNull(productsCategoryEntity)) {
      if (productCategoryJson.getCategoryNameKk() != null) {
        productsCategoryEntity.setCategoryNameKk(productCategoryJson.getCategoryNameKk());
      }
      if (productCategoryJson.getCategoryNameRu() != null) {
        productsCategoryEntity.setCategoryNameRu(productCategoryJson.getCategoryNameRu());
      }
      if (productCategoryJson.getCategoryNameEn() != null) {
        productsCategoryEntity.setCategoryNameEn(productCategoryJson.getCategoryNameEn());
      }
      if (productCategoryJson.getAddInfo() != null) {
        productsCategoryEntity.setAddInfo(productCategoryJson.getAddInfo());
      }
      this.productsCategoryRepository.save(productsCategoryEntity);
      log.info("Updated  product category " + new Date());
      return "Категория продуктов обновлена";
    } else {
      return "Категория продуктов с таким id не существует";
    }
  }

  @Override
  public String deleteProductCategory(Long productCategoryId) {
    ProductsCategoryEntity productsCategoryEntity =
        this.productsCategoryRepository.findById(productCategoryId).orElse(null);

    if (Objects.nonNull(productsCategoryEntity)) {
      log.info("Deleted " + productsCategoryEntity.getCategoryNameRu() + " category " + new Date());
      this.productsCategoryRepository.delete(productsCategoryEntity);
      return "Категория продукта удалена";
    } else {
      return "Категория продукта с таким id не существует";
    }

  }
}
