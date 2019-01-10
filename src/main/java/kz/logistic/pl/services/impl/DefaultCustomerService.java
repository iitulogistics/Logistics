package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.CustomerEntity;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.impl.DefaultCustomer;
import kz.logistic.pl.repositories.CustomerRepository;
import kz.logistic.pl.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultCustomerService implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired(required = false)
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> customers() {
        List<CustomerEntity> entities = this.customerRepository.findAll();

        return entities.stream().map(companyEntity -> DefaultCustomer
                .builder()
                .id(companyEntity.getId())
                .companyName(
                        localizedMessageBuilderFactory.builder()
                                .kk(companyEntity.getNameKk())
                                .ru(companyEntity.getNameRu())
                                .en(companyEntity.getNameEn()).build())
                .companyPhoneNumber(companyEntity.getPhone())
                .mobilePhoneNumber(companyEntity.getMobilePhone())
                .email(companyEntity.getEmail())
                .bin(companyEntity.getBin())
                .build()).collect(Collectors.toList());
    }


    @Override
    public void addCustomer(String mobilePhone, String password) {

    }
}
