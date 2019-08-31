package kz.logistic.pl.services;

import java.io.IOException;
import java.util.List;

import kz.logistic.pl.MobilePhone;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.impl.DefaultCustomer;
import kz.logistic.pl.models.pojos.json.CustomerJson;


public interface CustomerService {

    //Показать  всех клиентов
    List<Customer> showAllCustomers();

    DefaultCustomer showCustomer(Long customerId) throws Exception;

    //Добавить нового клиента
    String addCustomer(String username, String password) throws IOException;

    String addCustomerAllParams(String username, String password,
                       String customerNameKk, String customerNameRu,
                       String customerNameEn, String mobilePhone,
                       String email, String phoneNumber ,
                       String addInfo, String iinOrBin
                       ) throws IOException;

    //Добавить нового клиента посредством JSON
    String addCustomerJson(CustomerJson customerJson);

    boolean exists(MobilePhone username);

    boolean exists(String username);

    String updateCustomer(Long customerId, CustomerJson customerJson);

    String deleteCustomer(Long customerId);

}
