package kz.logistic.pl.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.logistic.pl.services.GosZakupBinService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class DefaultGosZakupService implements GosZakupBinService {

  @Override
  public Map<String, Object> showCompanyInformation(String bin) throws IOException {

    final String uri = "https://ows.goszakup.gov.kz/subject/biin/" + bin + "";
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer 49a1104332fc91e3d6c48c3521b281b7");
    HttpEntity<String> request = new HttpEntity<String>(headers);
    ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

    String result = responseEntity.getBody().toString();
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> map = objectMapper.readValue(result, Map.class);
    return map;
  }
}
