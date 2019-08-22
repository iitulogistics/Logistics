package kz.logistic.pl.soap.payment;

import kz.logistic.pl.models.entities.PaymentEntity;
import kz.logistic.pl.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.payment.Payment;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentRepositorySoap {
  private static final Map<Long, Payment> paymentMap = new HashMap<>();

  private PaymentRepository paymentRepository;

  @Autowired(required = false)
  public void setPaymentRepository(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @PostConstruct
  public void initData() {
    List<PaymentEntity> entities = this.paymentRepository.findAll();
    entities.forEach(paymentEntity -> {
      Payment payment = convertToPayment(paymentEntity);

      paymentMap.put(payment.getPaymentId(), payment);
    });
  }

  public Payment findPaymentId(Long id) {
    return paymentMap.get(id);
  }

  public Payment addPayment(Long ccId, Long orderId, int paymentAmount,
                            int paymentState, int paymentStatus, String timestamp) throws ParseException {
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setCcId(ccId);
    paymentEntity.setOrderId(orderId);
    paymentEntity.setPaymentAmount(paymentAmount);
    paymentEntity.setPaymentState(paymentState);
    paymentEntity.setPaymentStatus(paymentStatus);
    paymentEntity.setTimestamp(new SimpleDateFormat("yyyy-MM-dd").parse(timestamp));

    paymentRepository.save(paymentEntity);

    Payment payment = convertToPayment(paymentEntity);
    paymentMap.put(payment.getPaymentId(), payment);

    return payment;
  }

  public Payment updatePayment(Long id, Long ccId, Long orderId, int paymentAmount,
                               int paymentState, int paymentStatus, String timestamp) throws ParseException {
    Payment payment = paymentMap.get(id);
    payment.setCcId(ccId);
    payment.setOrderId(orderId);
    payment.setPaymentAmount(paymentAmount);
    payment.setPaymentState(paymentState);
    payment.setPaymentStatus(paymentStatus);
    payment.setTimestamp(timestamp);

    paymentRepository.updatePaymentById(id, ccId, orderId, paymentAmount, paymentState, paymentStatus,
      new SimpleDateFormat("yyyy-MM-dd").parse(timestamp));
    return payment;
  }

  public String deletePaymentId(Long id) {
    PaymentEntity paymentEntity = this.paymentRepository.findById(id).orElse(null);

    if (paymentEntity != null) {
      this.paymentRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Payment convertToPayment(PaymentEntity entity) {
    Payment payment = new Payment();
    payment.setCcId(entity.getCcId());
    payment.setOrderId(entity.getOrderId());
    payment.setPaymentAmount(entity.getPaymentAmount());
    payment.setPaymentId(entity.getPaymentId());
    payment.setPaymentState(entity.getPaymentState());
    payment.setPaymentStatus(entity.getPaymentStatus());
    payment.setTimestamp(entity.getTimestamp().toString());
    return payment;
  }
}
