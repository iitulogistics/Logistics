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

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> showAllCustomers() {
        List<CustomerEntity> entities = this.customerRepository.findAll();
        return entities.stream().map(customerEntity -> DefaultCustomer.builder()
                .customerId(customerEntity.getCustomerId())
                .mobilePhone(customerEntity.getMobilePhone())
                .build()).collect(Collectors.toList());
    }


    @Override
    public void addCustomer(String mobilePhone, String password) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setMobilePhone(mobilePhone);

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(mobilePhone);
        loginEntity.setPassword(password);
        loginEntity.setCustomerEntity(customerEntity);

        customerEntity.setLoginEntity(loginEntity);
        this.customerRepository.save(customerEntity);
        log.info("Added new customer, mobile phone: " + mobilePhone + ". " + new Date());
    }
}
