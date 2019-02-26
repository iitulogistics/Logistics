package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.CreditCard;
import lombok.Builder;

import java.util.Date;

@Builder
public class DefaultCreditCard implements CreditCard {
  private Long creditCardId;
  private Integer creditCardNumber;
  private String holderName;
  private Date expireDate;

  @Override
  public Long getCreditCardId() {
    return creditCardId;
  }

  @Override
  public Integer getCreditCardNumber() {
    return creditCardNumber;
  }

  @Override
  public String getHolderName() {
    return holderName;
  }

  @Override
  public Date getExpireDate() {
    return expireDate;
  }
}
