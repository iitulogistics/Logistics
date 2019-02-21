package kz.logistic.pl.models.pojos;

public interface Customer {

  Long getCustomerId();

  Long getLoginId();

  String getLoginName();

  String getLoginPassword();

  LocalizedMessage getCustomerName();

  String getIinOrBin();

  String getPhoneNumber();

  String getCustomerMobilePhone();

  String getEmail();

  String getAddInfo();

  Long getAddressId();
}
