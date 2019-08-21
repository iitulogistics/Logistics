package kz.logistic.pl.soap.credit_card;

import kz.logistic.pl.models.entities.CreditCardEntity;
import kz.logistic.pl.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.credit_card.CreditCard;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreditCardRepositorySoap {
  private static final Map<Long, CreditCard> creditCardMap = new HashMap<>();

  private CreditCardRepository creditCardRepository;

  @Autowired(required = false)
  public void setCreditCardRepository(CreditCardRepository creditCardRepository) {
    this.creditCardRepository = creditCardRepository;
  }

  @PostConstruct
  public void initData() {
    List<CreditCardEntity> entities = this.creditCardRepository.findAll();
    entities.forEach(creditCardEntity -> {
      CreditCard creditCard = convertToCreditCard(creditCardEntity);

      creditCardMap.put(creditCard.getCcId(), creditCard);
    });
  }

  public CreditCard findCreditCardId(Long id) {
    return creditCardMap.get(id);
  }

  public CreditCard addCreditCard(int creditCardNumber,
                              String holderName,
                              String expireDate) {
    CreditCardEntity creditCardEntity = new CreditCardEntity();
    creditCardEntity.setCreditCardNumber(creditCardNumber);
    creditCardEntity.setHolderName(holderName);
    try {
      creditCardEntity.setExpireDate(new SimpleDateFormat("yyyy-MM-dd").parse(expireDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    creditCardRepository.save(creditCardEntity);

    CreditCard creditCard = convertToCreditCard(creditCardEntity);
    creditCardMap.put(creditCard.getCcId(), creditCard);

    return creditCard;
  }

  public CreditCard updateCreditCard(Long id,
                                 int creditCardNumber,
                                 String holderName,
                                 String expireDate) throws ParseException {
    CreditCard creditCard = creditCardMap.get(id);
    creditCard.setHolderName(holderName);
    creditCard.setCreditCardNumber(creditCardNumber);
    creditCard.setExpireDate(expireDate);

    creditCardRepository.updateCreditCardById(id, creditCardNumber, new SimpleDateFormat("yyyy-MM-dd").parse(expireDate), holderName);
    return creditCard;
  }

  public String deleteCreditCardId(Long id) {
    CreditCardEntity creditCardEntity = this.creditCardRepository.findById(id).orElse(null);

    if (creditCardEntity != null) {
      this.creditCardRepository.deleteById(id);
      return "id" + id + "удалён";
    } else {
      return "id" + id + "не удалён";
    }
  }

  private CreditCard convertToCreditCard(CreditCardEntity entity) {
    CreditCard creditCard = new CreditCard();
    creditCard.setCcId(entity.getCcId());
    creditCard.setCreditCardNumber(entity.getCreditCardNumber());
    creditCard.setExpireDate(entity.getExpireDate().toString());
    creditCard.setHolderName(entity.getHolderName());

    return creditCard;
  }
}
