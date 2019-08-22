package kz.logistic.pl.soap.otp;

import kz.logistic.pl.models.entities.OtpEntity;
import kz.logistic.pl.repositories.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.otp.Otp;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OtpRepositorySoap {
  private static final Map<Long, Otp> otpMap = new HashMap<>();

  private OtpRepository otpRepository;

  @Autowired(required = false)
  public void setOtpRepository(OtpRepository otpRepository) {
    this.otpRepository = otpRepository;
  }

  @PostConstruct
  public void initData() {
    List<OtpEntity> entities = this.otpRepository.findAll();
    entities.forEach(otpEntity -> {
      Otp otp = convertToOtp(otpEntity);

      otpMap.put(otp.getId(), otp);
    });
  }

  public Otp findOtpId(Long id) {
    return otpMap.get(id);
  }

  public Otp addOpt(String mobilePhone, String otpString) {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setMobilePhone(mobilePhone);
    otpEntity.setOtp(otpString);

    otpRepository.save(otpEntity);

    Otp otp = convertToOtp(otpEntity);
    otpMap.put(otp.getId(), otp);

    return otp;
  }

  public Otp updateOtp(Long id, String mobilePhone, String otpString) {
    Otp otp = otpMap.get(id);
    otp.setOtp(otpString);
    otp.setMobilePhone(mobilePhone);

    otpRepository.updateOtpById(id, mobilePhone, otpString);
    return otp;
  }

  public String deleteOtpId(Long id) {
    OtpEntity otpEntity = this.otpRepository.findById(id).orElse(null);

    if (otpEntity != null) {
      this.otpRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Otp convertToOtp(OtpEntity entity) {
    Otp otp = new Otp();
    otp.setId(entity.getId());
    otp.setMobilePhone(entity.getMobilePhone());
    otp.setOtp(entity.getOtp());

    return otp;
  }
}
