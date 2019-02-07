package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.ProductSubCategory;
import lombok.Builder;

@Builder
public class DefaultCustomer implements Customer {

    private Long customerId;
    private Long loginId;
    private String username;
    private String password;
    private LocalizedMessage customerName;
    private String mobilePhone;


    @Override
    public long getLoginId() {
        return loginId;
    }

    @Override
    public String getLoginName() {
        return username;
    }

    @Override
    public String getLoginPassword() {
        return password;
    }

    @Override
    public long getCustomerId() {
        return customerId;
    }

    @Override
    public LocalizedMessage getCustomerName() {
        return customerName;
    }

    @Override
    public String getCustomerMobilePhone() {
        return mobilePhone;
    }


}
