package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.json.CustomerJson;

import java.util.List;

public interface CustomerService {

    //Показать  всех клиентов
    List<Customer> showAllCustomers();

    //Добавить нового клиента
    void addCustomer(String username, String password);

    //Добавить нового клиента посредством JSON
    void addCustomerJson(CustomerJson customerJson);
}
