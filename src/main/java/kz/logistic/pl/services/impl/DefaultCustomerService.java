package kz.logistic.pl.services.impl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.logistic.pl.models.entities.CustomerEntity;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.impl.DefaultCustomer;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.repositories.CustomerRepository;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.services.CustomerService;
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


@Slf4j
public class DefaultCustomerService implements CustomerService {

  private CustomerRepository customerRepository;
  private LoginRepository loginRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Value("${sms.api.key}")
  private String smsApiKey;

  @Value("${sms.api.url}")
  private String smsApiUrl;

  @Autowired
  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Autowired
  public void setLocalizedMessageBuilderFactory(
      LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Autowired
  public void setLoginRepository(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  @Override
  public List<Customer> showAllCustomers() {
    List<LoginEntity> entities = this.loginRepository.findByCustomerEntityCustomerIdIsNotNull();

    return entities.stream().map(customerEntity -> DefaultCustomer.builder()
      .loginId(customerEntity.getLoginId())
      .username(customerEntity.getUsername())
      .password(customerEntity.getPassword())
      .customerId(customerEntity.getCustomerEntity().getCustomerId())
      .mobilePhone(customerEntity.getCustomerEntity().getMobilePhone())
      .customerName(localizedMessageBuilderFactory.builder()
        .en(customerEntity.getCustomerEntity().getCustomerNameEn())
        .kk(customerEntity.getCustomerEntity().getCustomerNameKk())
        .ru(customerEntity.getCustomerEntity().getCustomerNameRu()).build())
      .iinOrBin(customerEntity.getCustomerEntity().getIinOrBin())
      .phoneNumber(customerEntity.getCustomerEntity().getPhoneNumber())
      .email(customerEntity.getCustomerEntity().getEmail())
      .addInfo(customerEntity.getCustomerEntity().getAddInfo())
      .addressId(customerEntity.getCustomerEntity().getAddressId())
      .build()).collect(Collectors.toList());
  }

  @Override
  public DefaultCustomer showCustomer(Long customerId) throws Exception {
    CustomerEntity customerEntity = this.customerRepository.findById(customerId).orElse(null);

    if(customerEntity == null){
      throw new Exception("Customer id not found");
    }

    return DefaultCustomer.builder()
      .customerId(customerEntity.getCustomerId())
      .loginId(customerEntity.getLoginEntity().getLoginId())
      .mobilePhone(customerEntity.getMobilePhone())
      .username(customerEntity.getLoginEntity().getUsername())
      .password(customerEntity.getLoginEntity().getPassword())
      .customerName(localizedMessageBuilderFactory.builder()
      .en(customerEntity.getCustomerNameEn())
      .kk(customerEntity.getCustomerNameKk())
      .ru(customerEntity.getCustomerNameRu()).build())
      .iinOrBin(customerEntity.getIinOrBin())
      .phoneNumber(customerEntity.getPhoneNumber())
      .email(customerEntity.getEmail())
      .addInfo(customerEntity.getAddInfo())
      .addressId(customerEntity.getAddressId())
      .build();
  }

  @Override
  public boolean exists(String username) {
    LoginEntity loginEntity = this.loginRepository.findByUsername(username);
    return loginEntity != null;
  }

  public int sendSms(String mobilePhone)  throws IOException {


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    map.add("apiKey", smsApiKey);
    map.add("recipient", mobilePhone);
    Random random = new Random();
    String id = String.format("%04d", random.nextInt(10000));
    map.add("text", id+" Ваш код подтверждения для регистрации");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.postForEntity( smsApiUrl, request , String.class );

    String result = response.getBody().toString();
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> objectMap = objectMapper.readValue(result, Map.class);

    return (int) objectMap.get("code");
  }

  @Override
  public String addCustomer(String username, String password) throws IOException {
    if (exists(username)) {
      return "Данный логин уже занят";
    }
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(username);
    loginEntity.setPassword(password);

    if (sendSms(username) == 0){
      log.info("OTP sent");
    } else {
      log.info("OTP did not send");
    }

    CustomerEntity customerEntity = new CustomerEntity();
    this.customerRepository.save(customerEntity);
    loginEntity.setCustomerEntity(customerEntity);
    this.loginRepository.save(loginEntity);
    log.info("Added new customer, username: " + username + ". " + new Date());
    return "Пользователь добавлен";
  }

  @Override
  public String addCustomerJson(CustomerJson customerJson) {
    if (exists(customerJson.getUsername())) {
      return "Данный логин уже занят";
    }
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(customerJson.getUsername());
    loginEntity.setPassword(customerJson.getPassword());


    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setEmail(customerJson.getEmail());
    customerEntity.setAddInfo(customerJson.getAddInfo());
    customerEntity.setCustomerNameEn(customerJson.getCustomerNameEn());
    customerEntity.setCustomerNameKk(customerJson.getCustomerNameKk());
    customerEntity.setCustomerNameRu(customerJson.getCustomerNameRu());
    customerEntity.setIinOrBin(customerJson.getIinOrBin());
    customerEntity.setMobilePhone(customerJson.getMobilePhone());
    customerEntity.setPhoneNumber(customerJson.getPhoneNumber());

    this.customerRepository.save(customerEntity);
    loginEntity.setCustomerEntity(customerEntity);
    this.loginRepository.save(loginEntity);
    log.info("Added new customer via JSON, username "
      + customerJson.getUsername() + ". " + new Date());
    return "Пользователь добавлен ";
  }

  @Override
  public String updateCustomer(Long customerId, CustomerJson customerJson) {
       CustomerEntity customerEntity = this.customerRepository.findById(customerId).orElse(null);
       LoginEntity loginEntity = this.loginRepository.findById(customerEntity.getLoginEntity().getLoginId()).orElse(null);

    if (Objects.nonNull(customerEntity)) {
      if (customerJson.getMobilePhone() != null) {
        customerEntity.setMobilePhone(customerJson.getMobilePhone());
      }
      if (customerJson.getPassword() != null) {
        loginEntity.setPassword(customerJson.getPassword());
      }
      if (customerJson.getEmail() != null) {
        customerEntity.setEmail(customerJson.getEmail());
      }
      if (customerJson.getCustomerNameKk() != null) {
        customerEntity.setCustomerNameKk(customerJson.getCustomerNameKk());
      }
      if (customerJson.getCustomerNameRu() != null) {
        customerEntity.setCustomerNameRu(customerJson.getCustomerNameRu());
      }
      if (customerJson.getCustomerNameEn() != null) {
        customerEntity.setCustomerNameEn(customerJson.getCustomerNameEn());
      }
      if (customerJson.getIinOrBin() != null) {
        customerEntity.setIinOrBin(customerJson.getIinOrBin());
      }
      if (customerJson.getPhoneNumber() != null) {
        customerEntity.setPhoneNumber(customerJson.getPhoneNumber());
      }
      if (customerJson.getEmail() != null) {
        customerEntity.setEmail(customerJson.getEmail());
      }
      if (customerJson.getAddInfo() != null) {
        customerEntity.setAddInfo(customerJson.getAddInfo());
      }

      this.loginRepository.save(loginEntity);
      this.customerRepository.save(customerEntity);
      log.info("Updated " + customerId + " customer " + new Date());
      return "Клиент обновлен";
    } else {
      return "Клиент с таким id не существует";
    }
  }

  @Override
  public String deleteCustomer(Long customerId) {
    CustomerEntity customerEntity = this.customerRepository.findById(customerId).orElse(null);
    LoginEntity loginEntity = this.loginRepository.findById(customerEntity.getLoginEntity().getLoginId()).orElse(null);
    this.loginRepository.deleteById(loginEntity.getLoginId());
    this.customerRepository.deleteById(customerEntity.getCustomerId());
    return "Данные клиента удалены";
  }
}
