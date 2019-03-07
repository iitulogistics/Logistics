package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
  OtpEntity findByMobilePhoneAndOtp(String mobileNumber, String otp);

  List<OtpEntity> findByMobilePhone(String mobileNumber);
}
