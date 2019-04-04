package kz.logistic.pl.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.logistic.pl.models.entities.OtpEntity;
import kz.logistic.pl.repositories.OtpRepository;
import kz.logistic.pl.services.OtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Slf4j
public class DefaultOtpService implements OtpService {

    @Value("${sms.api.key}")
    private String smsApiKey;

    @Value("${sms.api.url}")
    private String smsApiUrl;

    @Value("${sms.code}")
    private int smsCode;

    @Value("${sms.attempt}")
    private int smsAttempt;

    private OtpRepository otpRepository;

    @Autowired(required = false)
    public void setOtpRepository(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public boolean generateOtp(String mobilePhone) throws IOException {

        List<OtpEntity> otpEntityList = this.otpRepository.findByMobilePhone(mobilePhone);

        if (otpEntityList.size() <= smsAttempt) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("apiKey", smsApiKey);
            map.add("recipient", mobilePhone);
            Random random = new Random();
            String id = String.format("%04d", random.nextInt(10000));
            map.add("text", id + " Ваш код подтверждения для регистрации");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(smsApiUrl, request, String.class);

            String result = response.getBody().toString();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> objectMap = objectMapper.readValue(result, Map.class);

            if (objectMap.get("code").equals(smsCode)) {

                OtpEntity otpEntity = new OtpEntity();
                otpEntity.setMobilePhone(mobilePhone);
                otpEntity.setOtp(id);

                otpRepository.save(otpEntity);

                log.info("Generated OTP " + id + " for mobile phone " + mobilePhone);

                return true;
            } else {
                log.info("SMS Gateway cannot send SMS. Please check mobile number");
                return false;
            }
        } else {
            log.info("Try after 1 day");
            return false;
        }

    }

    @Override
    public boolean validateOtp(String mobilePhone, String otp) {
        OtpEntity otpEntity = this.otpRepository.findByMobilePhoneAndOtp(mobilePhone, otp);
        if (Objects.nonNull(otpEntity)) {
            if (otpEntity.getMobilePhone().equals(mobilePhone) && otpEntity.getOtp().equals(otp)) {
                log.info("call method customerService.addCustomer");
                return true;
            } else {
                return false;
            }
        } else {
            log.info("Entered customer OTP is wrong");
            return false;
        }
    }

}
