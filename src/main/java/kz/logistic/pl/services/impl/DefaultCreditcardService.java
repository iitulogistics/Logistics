package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.CreditCardEntity;
import kz.logistic.pl.models.pojos.CreditCard;
import kz.logistic.pl.models.pojos.impl.DefaultCreditCard;
import kz.logistic.pl.models.pojos.json.CreditCardJson;
import kz.logistic.pl.repositories.CreditCardRepository;
import kz.logistic.pl.services.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultCreditcardService implements CreditCardService {

  private CreditCardRepository creditCardRepository;

  @Autowired(required = false)
  public void setCreditCardRepository(CreditCardRepository creditCardRepository) {
    this.creditCardRepository = creditCardRepository;
  }

  @Override
  public List<CreditCard> showAllCreditCard() {
    List<CreditCardEntity> creditCardEntities = this.creditCardRepository.findAll();

    return creditCardEntities.stream().map(creditCardEntity ->
      DefaultCreditCard.builder()
        .creditCardId(creditCardEntity.getCcId())
        .creditCardNumber(creditCardEntity.getCreditCardNumber())
        .holderName(creditCardEntity.getHolderName()).build()).collect(Collectors.toList());
  }

  public boolean exists(Integer creditCardNumber, String holderName) {
    List<CreditCardEntity> creditCardEntities = this.creditCardRepository.findByCreditCardNumberAndHolderName(
      creditCardNumber, holderName);
    return creditCardEntities.size() > 0;
  }

  @Override
  public DefaultCreditCard showCreditCard(Long creditCardId) {
    CreditCardEntity creditCardEntity = this.creditCardRepository.findById(creditCardId).orElse(null);

    return DefaultCreditCard.builder()
      .creditCardId(creditCardEntity.getCcId())
      .creditCardNumber(creditCardEntity.getCreditCardNumber())
      .holderName(creditCardEntity.getHolderName()).build();
  }

  @Override
  public String addCreditCard(Integer creditCardNumber, String holderName, Date expireDate) {
    if (exists(creditCardNumber, holderName)) {
      return "Эта кредитная карта уже зарегистрирована";
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Timestamp expDate;
    CreditCardEntity creditCardEntity = new CreditCardEntity();
    creditCardEntity.setCreditCardNumber(creditCardNumber);
    creditCardEntity.setHolderName(holderName);
    creditCardEntity.setExpireDate(expireDate);
    this.creditCardRepository.save(creditCardEntity);
    log.info("Added new credit card " + creditCardNumber);
    return "Новый кредитная карта добавлена";
  }


  @Override
  public String addCreditCardJson(CreditCardJson creditCardJson) {
    if (exists(creditCardJson.getCreditCardNumber(), creditCardJson.getHolderName())) {
      return "Эта кредитная карта уже зарегистрирована";
    }
    CreditCardEntity creditCardEntity = new CreditCardEntity();
    creditCardEntity.setCreditCardNumber(creditCardJson.getCreditCardNumber());
    creditCardEntity.setHolderName(creditCardJson.getHolderName());
    creditCardEntity.setExpireDate(creditCardJson.getExpireDate());
    this.creditCardRepository.save(creditCardEntity);
    log.info("Added new credit card " + creditCardJson.getCreditCardNumber());
    return "Новый кредитная карта добавлена добавлен посредством JSON";
  }

  @Override
  public String updateCreditCard(Long creditCardId, CreditCardJson creditCardJson) {
    CreditCardEntity creditCardEntity = this.creditCardRepository.findById(creditCardId).orElse(null);
    if (Objects.nonNull(creditCardEntity)) {
      if (creditCardJson.getCreditCardNumber() != null) {
        creditCardEntity.setCreditCardNumber(creditCardJson.getCreditCardNumber());
      }
      if (creditCardJson.getHolderName() != null) {
        creditCardEntity.setHolderName(creditCardJson.getHolderName());
      }
      if (creditCardJson.getExpireDate() != null) {
        creditCardEntity.setExpireDate(creditCardJson.getExpireDate());
      }
      this.creditCardRepository.save(creditCardEntity);
      log.info("Updated " + creditCardJson.getCreditCardNumber() + " credit card");
      return "Кредитная карта успешно обнавлен";
    } else {
      return "Адрес с таким id не существует";
    }
  }

  @Override
  public String deleteCreditCard(Long creditCardId) {
    CreditCardEntity creditCardEntity = this.creditCardRepository.findById(creditCardId).orElse(null);
    if (Objects.nonNull(creditCardEntity)) {
      this.creditCardRepository.deleteById(creditCardEntity.getCcId());
      log.info("Deleted " + creditCardEntity.getCreditCardNumber() + " credit card");
      return "Кредитная карта успешно удалена";
    } else {
      return "Кредитная карт с таким id не существует";
    }
  }
}
