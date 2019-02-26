package kz.logistic.pl.models.pojos;

import java.util.Date;

public interface CreditCard {
  Long getCreditCardId();

  Integer getCreditCardNumber();

  String getHolderName();

  Date getExpireDate();

}
