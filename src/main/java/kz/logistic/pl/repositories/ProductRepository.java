package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
