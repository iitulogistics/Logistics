package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.Shipper;
import lombok.Builder;

@Builder
public class DefaultShipper implements Shipper {

  private Long shipperId;
  private Long loginId;
  private String username;
  private String password;
  private LocalizedMessage shipperName;
  private String phoneNumber;
  private String mobilePhone;
  private String email;
  private String address;
  private String bin;

  @Override
  public Long getShipperId() {
    return shipperId;
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
  public LocalizedMessage getShipperName() {
    return shipperName;
  }

  @Override
  public Long getLoginId() {
    return loginId;
  }

  @Override
  public String getBin() {
    return bin;
  }

  @Override
  public String getMobilePhone() {
    return mobilePhone;
  }

  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getAddress() {
    return address;
  }

}