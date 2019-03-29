package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CreditCardEntity;
import kz.logistic.pl.models.pojos.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
  List<CreditCardEntity> findByCreditCardNumberAndHolderName(Integer creditCardNumber, String holderName);
}
