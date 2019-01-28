package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Customer;

import java.util.List;

public interface CustomerService {

    //Показать  всех клиентов
    List<Customer> showAllCustomers();

    //Добавить нового клиента
    void addCustomer(String mobilePhone, String password);
}
