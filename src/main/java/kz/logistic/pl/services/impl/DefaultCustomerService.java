package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.CustomerEntity;
import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.impl.DefaultCustomer;
import kz.logistic.pl.models.pojos.impl.DefaultProductCategory;
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
    public void setLoginRepository(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }


    @Override
    public List<Customer> showAllCustomers() {
        List<CustomerEntity> entities = this.customerRepository.findAll();
        return entities.stream().map(customerEntity -> DefaultCustomer.builder()
                .customerId(customerEntity.getCustomerId())
                .username(loginRepository.getOne(customerEntity.getLoginEntityId()).getUsername())
                .password(loginRepository.getOne(customerEntity.getLoginEntityId()).getPassword())
                .build()).collect(Collectors.toList());
    }


    @Override
    public void addCustomer(String username, String password) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(username);
        loginEntity.setPassword(password);
        this.loginRepository.save(loginEntity);
        log.info("Added new login instance: username(" + username + "), password(" + password + ").");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setLoginEntityId(loginEntity.getLoginId());
        this.customerRepository.save(customerEntity);
        log.info("Added new customer: username(" + username + ")");
    }
}
