package kz.logistic.pl.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


@Slf4j
public class DefaultCustomerService implements CustomerService {

  private CustomerRepository customerRepository;
  private LoginRepository loginRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

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
//      .customerName(localizedMessageBuilderFactory.builder()
//        .en(customerEntity.getCustomerEntity().getCustomerNameEn())
//        .kk(customerEntity.getCustomerEntity().getCustomerNameKk())
//        .ru(customerEntity.getCustomerEntity().getCustomerNameRu()).build())
//      .iinOrBin(customerEntity.getCustomerEntity().getIinOrBin())
//      .phoneNumber(customerEntity.getCustomerEntity().getPhoneNumber())
//      .email(customerEntity.getCustomerEntity().getEmail())
//      .addInfo(customerEntity.getCustomerEntity().getAddInfo())
//      .addressId(customerEntity.getCustomerEntity().getCustomerId())
      .build()).collect(Collectors.toList());
  }

  @Override
  public DefaultCustomer showCustomer(Long customerId) {
    CustomerEntity customerEntity = this.customerRepository.findById(customerId).orElse(null);
    return DefaultCustomer.builder()
      .customerId(customerEntity.getCustomerId())
      .loginId(customerEntity.getLoginEntity().getLoginId())
      .mobilePhone(customerEntity.getMobilePhone())
      .username(customerEntity.getLoginEntity().getUsername())
      .customerName(localizedMessageBuilderFactory.builder()
      .en(customerEntity.getCustomerNameEn())
      .kk(customerEntity.getCustomerNameKk())
      .ru(customerEntity.getCustomerNameRu()).build())
      .iinOrBin(customerEntity.getIinOrBin())
      .phoneNumber(customerEntity.getPhoneNumber())
      .email(customerEntity.getEmail())
      .addInfo(customerEntity.getAddInfo())
      .addressId(customerEntity.getCustomerId())
      .build();
  }

  @Override
  public boolean exists(String username) {
    ArrayList<LoginEntity> loginEntities = this.loginRepository.findByUsername(username);
    return loginEntities.size() > 0;
  }

  @Override
  public String addCustomer(String username, String password) {
    if (exists(username)) {
      return "Данный логин уже занят";
    }
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(username);
    loginEntity.setPassword(password);

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
