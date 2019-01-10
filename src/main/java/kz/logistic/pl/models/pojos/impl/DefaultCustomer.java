package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Customer;
import lombok.Builder;

@Builder
public class DefaultCustomer implements Customer {
    private long id;
    private String name;
    private String email;
    private String address;
    private String mobilePhone;
    private String userName;

    @Override
    public long getCustomerId() {
        return id;
    }

    @Override
    public String getCustomerName() {
        return name;
    }

    @Override
    public String getCustomerEmail() {
        return email;
    }

    @Override
    public String getCustomerAddress() {
        return address;
    }

    @Override
    public String getCustomerMobilePhone() {
        return mobilePhone;
    }

    @Override
    public String getCustomerUserName() {
        return userName;
    }
}
