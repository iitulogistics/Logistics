package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> customers();

    void addCustomer(String mobilePhone, String password);

}
