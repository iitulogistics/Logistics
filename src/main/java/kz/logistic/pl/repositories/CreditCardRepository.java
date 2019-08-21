package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CreditCardEntity;
import kz.logistic.pl.models.pojos.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

  @Query("select c from CreditCardEntity c where c.creditCardNumber = ?1 and c.holderName = ?2")
  List<CreditCardEntity> findCreditCardByNumberAndHolderName(Integer creditCardNumber, String holderName);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update CreditCardEntity c set c.creditCardNumber = ?2, c.expireDate = ?3, c.holderName = ?4 where c.ccId = ?1")
  void updateCreditCardById(Long id, int creditCardNumber, Date expireDate, String holderName);
}
