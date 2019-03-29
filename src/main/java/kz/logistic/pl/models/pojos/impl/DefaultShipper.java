package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.Shipper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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

}