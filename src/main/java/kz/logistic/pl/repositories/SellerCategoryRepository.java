package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.SellerCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SellerCategoryRepository extends JpaRepository<SellerCategoryEntity, Long> {

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update SellerCategoryEntity p set p.addInfo = ?2, p.categoryNameEn = ?3, " +
    "p.categoryNameRu = ?4, p.categoryNameKk = ?5 where p.sellerCategoryId = ?1")
  void updateSellerCategoryById(Long id, String addInfo, String categoryNameEn, String categoryNameRu,
                                 String categoryNameKk);
}
