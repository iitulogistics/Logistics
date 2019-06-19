package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long> {
    @Query("select product from ProductsEntity product where product.productCategoryId = ?1")
    ArrayList<ProductsEntity> findByProductCategoryId(Long categoryId);
}
