package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

}
