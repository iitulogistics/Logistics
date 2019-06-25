package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSubCategoryRepository
    extends JpaRepository<ProductsSubCategoryEntity, Long> {
    List<ProductsSubCategoryEntity> findByProductCategoryId(Long productId);
}
