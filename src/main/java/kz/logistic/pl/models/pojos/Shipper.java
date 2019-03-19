package kz.logistic.pl.models.pojos;

public interface Shipper {

  Long getShipperId();

  Long getLoginId();

  String getUsername();

  String getPassword();

  LocalizedMessage getShipperName();

  String getBin();

  String getMobilePhone();

  String getPhoneNumber();

  String getEmail();

  String getAddress();

}