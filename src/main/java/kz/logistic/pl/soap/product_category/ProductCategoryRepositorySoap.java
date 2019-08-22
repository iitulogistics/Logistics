package kz.logistic.pl.soap.product_category;

import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import kz.logistic.pl.models.entities.ProductsEntity;
import kz.logistic.pl.repositories.ProductsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.product.Product;
import soap.logistic.product_category.ProductCategory;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductCategoryRepositorySoap {
  private static final Map<Long, ProductCategory> productMap = new HashMap<>();

  private ProductsCategoryRepository productsCategoryRepository;

  @Autowired(required = false)
  public void setProductCategoryRepository(ProductsCategoryRepository productsCategoryRepository) {
    this.productsCategoryRepository = productsCategoryRepository;
  }

  @PostConstruct
  public void initData() {
    List<ProductsCategoryEntity> entities = this.productsCategoryRepository.findAll();
    entities.forEach(productCategoryEntity -> {
      ProductCategory productCategory = convertToProductCategory(productCategoryEntity);

      productMap.put(productCategory.getProductCategoryId(), productCategory);
    });
  }

  public ProductCategory findProductCategoryId(Long id) {
    return productMap.get(id);
  }

  public ProductCategory addProductCategory(String addInfo, String categoryNameEn, String categoryNameRu, String categoryNameKk) {
    ProductsCategoryEntity productsEntity = new ProductsCategoryEntity();
    productsEntity.setAddInfo(addInfo);
    productsEntity.setCategoryNameEn(categoryNameEn);
    productsEntity.setCategoryNameRu(categoryNameRu);
    productsEntity.setCategoryNameKk(categoryNameKk);

    productsCategoryRepository.save(productsEntity);

    ProductCategory product = convertToProductCategory(productsEntity);
    productMap.put(product.getProductCategoryId(), product);

    return product;
  }

  public ProductCategory updateProductCategory(Long id, String addInfo, String categoryNameEn, String categoryNameRu, String categoryNameKk) {
    ProductCategory product = productMap.get(id);
    product.setAddInfo(addInfo);
    product.setCategoryNameEn(categoryNameEn);
    product.setCategoryNameRu(categoryNameRu);
    product.setCategoryNameKk(categoryNameKk);

    productsCategoryRepository.updateProductCategoryById(id, addInfo, categoryNameEn, categoryNameRu, categoryNameKk);
    return product;
  }

  public String deleteProductCategory(Long id) {
    ProductsCategoryEntity productsEntity = this.productsCategoryRepository.findById(id).orElse(null);

    if (productsEntity != null) {
      this.productsCategoryRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private ProductCategory convertToProductCategory(ProductsCategoryEntity entity) {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setAddInfo(entity.getAddInfo());
    productCategory.setCategoryNameEn(entity.getCategoryNameEn());
    productCategory.setCategoryNameRu(entity.getCategoryNameRu());
    productCategory.setCategoryNameKk(entity.getCategoryNameKk());
    productCategory.setProductCategoryId(entity.getProductCategoryId());

    return productCategory;
  }
}
