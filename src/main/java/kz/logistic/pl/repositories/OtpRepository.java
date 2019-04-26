package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
  @Query("select o from OtpEntity o where o.mobilePhone = ?1 and o.otp = ?2")
  OtpEntity findByMobilePhoneAndOtp(String mobileNumber, String otp);


  @Query("select o from OtpEntity o where o.mobilePhone = ?1")
  List<OtpEntity> findByMobilePhone(String mobileNumber);
}
