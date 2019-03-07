package kz.logistic.pl.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.logistic.pl.services.OtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class DefaultOtpService implements OtpService {

  @Value("${sms.api.key}")
  private String smsApiKey;

  @Value("${sms.api.url}")
  private String smsApiUrl;

  @Value("${sms.code")
  private String smsCode;

  @Override
  public boolean generateOtp(String mobilePhone) throws IOException {

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
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean validateOtp(String mobilePhone, String otp) {
    return false;
  }
}
