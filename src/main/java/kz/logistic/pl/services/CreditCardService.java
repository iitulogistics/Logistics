package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.CreditCard;
import kz.logistic.pl.models.pojos.impl.DefaultCreditCard;
import kz.logistic.pl.models.pojos.json.CreditCardJson;

import java.util.Date;

import java.util.List;

public interface CreditCardService {

  List<CreditCard> showAllCreditCard();

  DefaultCreditCard showCreditCard(Long creditcardId);

  String addCreditCard(Integer creditCardNumber, String holderName,
                       Date expireDate);

  String addCreditCardJson(CreditCardJson creditCardJson);

  String updateCreditCard(Long creditCardId, CreditCardJson creditCardJson);

  String deleteCreditCard(Long creditCardId);
}
