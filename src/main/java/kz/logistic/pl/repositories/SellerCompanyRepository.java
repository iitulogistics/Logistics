package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.SellerCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface SellerCompanyRepository extends JpaRepository<SellerCompanyEntity, Long> {
    String findByMobilePhone(String mobilePhone);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update SellerCompanyEntity s set s.companyNameEn = ?2, s.companyNameRu = ?3, s.companyNameKk = ?4," +
      "s.bin = ?5, s.email = ?6, s.mobilePhone = ?7, s.phone = ?8, s.sellerCategoryId = ?9 where s.sellerCompanyId = ?1")
    void updateSellerCompanyById(Long id, String companyNameEn, String companyNameRu,
                                 String companyNameKk, String bin, String email, String mobilePhone,
                                 String phone, Long sellerCategoryId);
}
