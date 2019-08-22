package kz.logistic.pl.soap.product_subcategory;

import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import kz.logistic.pl.models.entities.ProductsSubCategoryEntity;
import kz.logistic.pl.models.pojos.ProductSubCategory;
import kz.logistic.pl.repositories.ProductSubCategoryRepository;
import kz.logistic.pl.repositories.ProductsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.product_category.ProductCategory;
import soap.logistic.product_subcategory.ProductSubcategory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductSubcategoryRepositorySoap {
  private static final Map<Long, ProductSubcategory> productMap = new HashMap<>();

  private ProductSubCategoryRepository productSubCategoryRepository;

  @Autowired(required = false)
  public void setProductSubcategoryRepository(ProductSubCategoryRepository productSubCategoryRepository) {
    this.productSubCategoryRepository = productSubCategoryRepository;
  }

  @PostConstruct
  public void initData() {
    List<ProductsSubCategoryEntity> entities = this.productSubCategoryRepository.findAll();
    entities.forEach(productSubCategoryEntity -> {
      ProductSubcategory productSubCategory = convertToProductCategory(productSubCategoryEntity);

      productMap.put(productSubCategory.getProductSubcategoryId(), productSubCategory);
    });
  }

  public ProductSubcategory findProductSubCategoryId(Long id) {
    return productMap.get(id);
  }

  public ProductSubcategory addProductSubCategory(String subCategoryNameEn, String subCategoryNameKk, String subCategoryNameRu,
                                                  long productCategoryId, String subCategoryAddInfo) {
    ProductsSubCategoryEntity productsEntity = new ProductsSubCategoryEntity();
    productsEntity.setSubCategoryNameEn(subCategoryNameEn);
    productsEntity.setSubCategoryNameKk(subCategoryNameKk);
    productsEntity.setSubCategoryNameRu(subCategoryNameRu);
    productsEntity.setProductCategoryId(productCategoryId);
    productsEntity.setSubCategoryAddInfo(subCategoryAddInfo);

    productSubCategoryRepository.save(productsEntity);

    ProductSubcategory product = convertToProductCategory(productsEntity);
    productMap.put(product.getProductCategoryId(), product);

    return product;
  }

  public ProductSubcategory updateProductSubCategory(Long id, String subCategoryNameEn, String subCategoryNameKk,
                                                     String subCategoryNameRu, long productCategoryId, String subCategoryAddInfo) {
    ProductSubcategory product = productMap.get(id);
    product.setCategoryNameEn(subCategoryNameEn);
    product.setCategoryNameKk(subCategoryNameKk);
    product.setCategoryNameRu(subCategoryNameRu);
    product.setProductCategoryId(productCategoryId);
    product.setSubCategoryAddInfo(subCategoryAddInfo);

    productSubCategoryRepository.updateSubCategoryById(id, subCategoryNameEn, subCategoryNameKk, subCategoryNameRu, productCategoryId, subCategoryAddInfo);
    return product;
  }

  public String deleteProductSubCategory(Long id) {
    ProductsSubCategoryEntity subProductsEntity = this.productSubCategoryRepository.findById(id).orElse(null);

    if (subProductsEntity != null) {
      this.productSubCategoryRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private ProductSubcategory convertToProductCategory(ProductsSubCategoryEntity entity) {
    ProductSubcategory productSubcategory = new ProductSubcategory();
    productSubcategory.setCategoryNameEn(entity.getSubCategoryNameEn());
    productSubcategory.setCategoryNameKk(entity.getSubCategoryNameKk());
    productSubcategory.setCategoryNameRu(entity.getSubCategoryNameRu());
    productSubcategory.setProductCategoryId(entity.getProductCategoryId());
    productSubcategory.setProductSubcategoryId(entity.getProductSubcategoryId());
    productSubcategory.setSubCategoryAddInfo(entity.getSubCategoryAddInfo());

    return productSubcategory;
  }
}
