package kz.logistic.pl.models.pojos;

public interface Customer {

    long getCustomerId();

    LocalizedMessage getCustomerName();

    String getCustomerMobilePhone();

    String getCustomerPassword();
}
