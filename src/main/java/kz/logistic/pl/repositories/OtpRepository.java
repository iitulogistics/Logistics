package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
  @Query("select o from OtpEntity o where o.mobilePhone = ?1 and o.otp = ?2")
  OtpEntity findByMobilePhoneAndOtp(String mobileNumber, String otp);


  @Query("select o from OtpEntity o where o.mobilePhone = ?1")
  List<OtpEntity> findByMobilePhone(String mobileNumber);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update OtpEntity o set o.mobilePhone = ?2, o.otp = ?3 where o.id = ?1")
  void updateOtpById(Long id, String mobilePhone, String otp);
}
