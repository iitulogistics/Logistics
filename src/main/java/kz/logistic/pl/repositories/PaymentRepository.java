package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update PaymentEntity p set p.ccId = ?2, p.orderId = ?3, p.paymentAmount = ?4, " +
    "p.paymentState = ?5, p.paymentStatus = ?6, p.timestamp = ?7 where p.paymentId = ?1")
  void updatePaymentById(Long id, Long ccId, Long orderId, int paymentAmount,
                         int paymentState, int paymentStatus, Date timestamp);
}
