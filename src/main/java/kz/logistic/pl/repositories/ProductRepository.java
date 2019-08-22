package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long> {

  @Query("select prod from ProductsEntity prod where prod.productNameEn like %?1% or " +
    "prod.productNameRu like %?1% or " +
    "prod.productNameKk like %?1%")
  List<ProductsEntity> getProductsByName(String name);

  @Query("select prod from ProductsEntity prod where prod.productCategoryId = ?1")
  List<ProductsEntity> getProductsByCategoryId(Long id);

  @Query("select prod from ProductsEntity prod where prod.productId = ?1")
  ProductsEntity getOne(Long l);

  List<ProductsEntity> findBySellerCompanyId(Long sellerCompanyId);

  List<ProductsEntity> findByProductCategoryId(Long productCategoryId);

  List<ProductsEntity> findByProductSubcategoryId(Long productSubCategoryId);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update ProductsEntity p set p.manufacturer = ?2, p.price = ?3, p.productCategoryId = ?4, p.productDescription = ?5," +
    "p.productNameEn = ?6, p.productNameKk = ?7, p.productNameRu = ?8, p.productsImg = ?9, p.productSubcategoryId = ?10," +
    "p.sellerCompanyId = ?11, p.serialNumber = ?12, p.size = ?13, p.specialCharacteristicId = ?14, p.uniqueIdNumber = ?15," +
    "p.weight = ?16 where p.productId = ?1")
  void updateProductById(long id, String manufacturer, int price, long productCategoryId, String productDescription,
                         String productNameEn, String productNameKk, String productNameRu,
                         String productsImg, long productSubcategoryId, long sellerCompanyId,
                         String serialNumber, String size, long specialCharacteristicId,
                         String uniqueIdNumber, int weight);
}
