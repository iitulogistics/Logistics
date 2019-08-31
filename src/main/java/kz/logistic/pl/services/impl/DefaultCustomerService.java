package kz.logistic.pl.services.impl;

import kz.logistic.pl.MobilePhone;
import kz.logistic.pl.utils.ReturnMessage;
import kz.logistic.pl.models.entities.CustomerEntity;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.impl.DefaultCustomer;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.repositories.CustomerRepository;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.services.CustomerService;
import kz.logistic.pl.services.OtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
public class DefaultCustomerService implements CustomerService {

    private CustomerRepository customerRepository;
    private LoginRepository loginRepository;
    private OtpService otpService;
    private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;
    private ReturnMessage returnMessage;

    @Autowired(required = false)
    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setOtpService(OtpService otpService) {
        this.otpService = otpService;
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

        if (customerEntity == null) {
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
    public boolean exists(MobilePhone username) {
        LoginEntity loginEntity = this.loginRepository
          .findByUsername(username.getMobilePhone())
          .orElse(null);
        return loginEntity != null;
    }

  @Override
  public boolean exists(String username) {
    LoginEntity loginEntity = this.loginRepository.findByUsername(username).orElse(null);
    return loginEntity != null;
  }

  @Override
    public String addCustomer(String username, String password) throws IOException {
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
        return java.text.MessageFormat.format(returnMessage.getCustomerAddSuccess(), username);
    }

  @Override
  public String addCustomerAllParams(String username, String password,
                                     String customerNameKk, String customerNameRu,
                                     String customerNameEn, String mobilePhone, String email,
                                     String phoneNumber, String addInfo, String iinOrBin) throws IOException {
    if (exists(username)) {
      return "Данный логин уже занят";
    }
    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setUsername(username);
    loginEntity.setPassword(password);

    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setEmail(email);
    customerEntity.setAddInfo(addInfo);
    customerEntity.setCustomerNameEn(customerNameEn);
    customerEntity.setCustomerNameKk(customerNameKk);
    customerEntity.setCustomerNameRu(customerNameRu);
    customerEntity.setIinOrBin(iinOrBin);
    customerEntity.setMobilePhone(mobilePhone);
    customerEntity.setPhoneNumber(phoneNumber);

    this.customerRepository.save(customerEntity);
    loginEntity.setCustomerEntity(customerEntity);
    this.loginRepository.save(loginEntity);
    log.info("Added new customer via all params, username "
      + username + ". " + new Date());
    return java.text.MessageFormat.format(returnMessage.getCustomerAddSuccess(), username);
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
        return java.text.MessageFormat.format(returnMessage.getCustomerAddSuccess(), customerJson.getUsername());
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
            return java.text.MessageFormat.format(returnMessage.getCustomerUpdateSuccess(), customerJson.getUsername());
        } else {
            return java.text.MessageFormat.format(returnMessage.getCustomerUpdateError(), customerJson.getUsername());

        }
    }

    @Override
    public String deleteCustomer(Long customerId) {
        CustomerEntity customerEntity = this.customerRepository.findById(customerId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(customerEntity.getLoginEntity().getLoginId()).orElse(null);
        this.loginRepository.deleteById(loginEntity.getLoginId());
        this.customerRepository.deleteById(customerEntity.getCustomerId());
        return java.text.MessageFormat.format(returnMessage.getCustomerDeleteSuccess(), customerEntity.getLoginEntity().getUsername());
    }
}
