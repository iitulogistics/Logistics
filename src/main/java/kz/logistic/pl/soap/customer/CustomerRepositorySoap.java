package kz.logistic.pl.soap.customer;

import kz.logistic.pl.models.entities.CustomerEntity;
import kz.logistic.pl.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.customer.Customer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerRepositorySoap {
  private static final Map<Long, Customer> customerMap = new HashMap<>();

  private CustomerRepository customerRepository;

  @Autowired(required = false)
  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @PostConstruct
  public void initData() {
    List<CustomerEntity> entities = this.customerRepository.findAll();
    entities.forEach(customerEntity -> {
      Customer customer = convertToCustomer(customerEntity);

      customerMap.put(customer.getCustomerId(), customer);
    });
  }

  public Customer findCustomerId(Long id) {
    return customerMap.get(id);
  }

  public Customer addCustomer(String customerNameEn, String customerNameRu, String customerNameKk,
                              String addInfo, String email,
                              String iinOrBin, String mobilePhone, String phoneNumber,
                              Long addressId) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setCustomerNameEn(customerNameEn);
    customerEntity.setCustomerNameRu(customerNameRu);
    customerEntity.setCustomerNameKk(customerNameKk);
    customerEntity.setAddInfo(addInfo);
    customerEntity.setEmail(email);
    customerEntity.setIinOrBin(iinOrBin);
    customerEntity.setMobilePhone(mobilePhone);
    customerEntity.setPhoneNumber(phoneNumber);
    customerEntity.setAddressId(addressId);

    customerRepository.save(customerEntity);

    Customer customer = convertToCustomer(customerEntity);
    customerMap.put(customer.getCustomerId(), customer);

    return customer;
}

  public Customer updateCustomer(Long id,
                                     String customerNameEn, String customerNameRu, String customerNameKk,
                                     String addInfo, String email,
                                     String iinOrBin, String mobilePhone, String phoneNumber,
                                     Long addressId) {
    Customer customer = customerMap.get(id);
    customer.setCustomerNameEn(customerNameEn);
    customer.setCustomerNameRu(customerNameRu);
    customer.setCustomerNameKk(customerNameKk);
    customer.setAddInfo(addInfo);
    customer.setEmail(email);
    customer.setIinOrBin(iinOrBin);
    customer.setMobilePhone(mobilePhone);
    customer.setPhoneNumber(phoneNumber);
    customer.setAddressId(addressId);

    customerRepository.updateCustomerById(id,
      customerNameEn, customerNameRu, customerNameKk, addInfo, email,
      iinOrBin, mobilePhone, phoneNumber, addressId);
    return customer;
  }

  public String deleteCustomerId(Long id) {
    CustomerEntity customerEntity = this.customerRepository.findById(id).orElse(null);

    if (customerEntity != null) {
      this.customerRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Customer convertToCustomer(CustomerEntity entity) {
    Customer customer = new Customer();
    customer.setCustomerNameEn(entity.getCustomerNameEn());
    customer.setCustomerNameRu(entity.getCustomerNameRu());
    customer.setCustomerNameKk(entity.getCustomerNameKk());
    customer.setCustomerId(entity.getCustomerId());
    customer.setAddInfo(entity.getAddInfo());
    customer.setEmail(entity.getEmail());
    customer.setIinOrBin(entity.getIinOrBin());
    customer.setMobilePhone(entity.getMobilePhone());
    customer.setPhoneNumber(entity.getPhoneNumber());
//    customer.setAddressId(entity.getAddressId());

    return customer;
  }
}
