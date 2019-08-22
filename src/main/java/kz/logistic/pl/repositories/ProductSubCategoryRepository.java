package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductSubCategoryRepository
    extends JpaRepository<ProductsSubCategoryEntity, Long> {
    List<ProductsSubCategoryEntity> findByProductCategoryId(Long productId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update ProductsSubCategoryEntity p set p.subCategoryNameEn = ?2, p.subCategoryNameKk = ?3," +
      "p.subCategoryNameRu = ?4, p.productCategoryId = ?5, p.subCategoryAddInfo = ?6 where p.productSubcategoryId = ?1")
    void updateSubCategoryById(Long id, String subCategoryNameEn, String subCategoryNameKk,
                               String subCategoryNameRu, long productCategoryId, String subCategoryAddInfo);
}
