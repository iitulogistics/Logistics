package kz.logistic.pl.models.pojos;

public interface Customer {

  long getCustomerId();

  long getLoginId();

  String getLoginName();

  String getLoginPassword();

  LocalizedMessage getCustomerName();

  String getIinOrBin();

  String getPhoneNumber();

  String getCustomerMobilePhone();

  String getEmail();

  String getAddInfo();

  long getAddressId();
}
