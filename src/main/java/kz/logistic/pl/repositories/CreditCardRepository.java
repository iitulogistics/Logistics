package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CreditCardEntity;
import kz.logistic.pl.models.pojos.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

  @Query("select c from CreditCardEntity c where c.creditCardNumber = ?1 and c.holderName = ?2")
  List<CreditCardEntity> findCreditCardByNumberAndHolderName(Integer creditCardNumber, String holderName);
}
