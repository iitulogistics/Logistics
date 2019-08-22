package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductsCategoryRepository extends JpaRepository<ProductsCategoryEntity, Long> {
  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update ProductsCategoryEntity p set p.addInfo = ?2, p.categoryNameEn = ?3, " +
    "p.categoryNameRu = ?4, p.categoryNameKk = ?5 where p.productCategoryId = ?1")
  void updateProductCategoryById(Long id, String addInfo, String categoryNameEn, String categoryNameRu,
                                 String categoryNameKk);
}
