package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.CustomerEntity;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.impl.DefaultCustomer;
import kz.logistic.pl.models.pojos.impl.DefaultProductCategory;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.repositories.CustomerRepository;
import kz.logistic.pl.repositories.LoginRepository;
import kz.logistic.pl.repositories.ProductsCategoryRepository;
import kz.logistic.pl.services.CustomerService;
import kz.logistic.pl.services.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DefaultCustomerService implements CustomerService {

    private CustomerRepository customerRepository;
    private LoginRepository loginRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
                .build()).collect(Collectors.toList());
    }


    @Override
    public void addCustomer(String username, String password) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(username);
        loginEntity.setPassword(password);

        CustomerEntity customerEntity = new CustomerEntity();
        this.customerRepository.save(customerEntity);
        loginEntity.setCustomerEntity(customerEntity);
        this.loginRepository.save(loginEntity);
        log.info("Added new customer, username: " + username + ". " + new Date());
    }

    @Override
    public void addCustomerJson(CustomerJson customerJson) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(customerJson.getUsername());
        loginEntity.setPassword(customerJson.getPassword());

        CustomerEntity customerEntity = new CustomerEntity();
        this.customerRepository.save(customerEntity);
        loginEntity.setCustomerEntity(customerEntity);
        this.loginRepository.save(loginEntity);
        log.info("Added new customer via JSON, username " + customerJson.getUsername() + ". " + new Date());
    }
}
