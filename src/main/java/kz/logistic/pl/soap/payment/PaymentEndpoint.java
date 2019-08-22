package kz.logistic.pl.soap.payment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.payment.*;

import java.text.ParseException;

@Log
@Endpoint
public class PaymentEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/payment";

  private PaymentRepositorySoap paymentRepositorySoap;

  @Autowired
  public PaymentEndpoint(PaymentRepositorySoap paymentRepositorySoap) {
    this.paymentRepositorySoap = paymentRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentIdRequest")
  @ResponsePayload
  public GetPaymentIdResponse getPayment(@RequestPayload GetPaymentIdRequest request) {
    GetPaymentIdResponse response = new GetPaymentIdResponse();
    response.setPayment(paymentRepositorySoap.findPaymentId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePaymentIdRequest")
  @ResponsePayload
  public DeletePaymentIdResponse deletePayment(@RequestPayload DeletePaymentIdRequest request) {
    DeletePaymentIdResponse response = new DeletePaymentIdResponse();

    response.setPayment(this.paymentRepositorySoap.deletePaymentId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaymentRequest")
  @ResponsePayload
  public AddPaymentResponse addPayment(@RequestPayload AddPaymentRequest request) throws ParseException {
    AddPaymentResponse response = new AddPaymentResponse();
    response.setPayment(paymentRepositorySoap.addPayment(request.getCcId(), request.getOrderId(), request.getPaymentAmount(),
      request.getPaymentState(), request.getPaymentStatus(), request.getTimestamp()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePaymentRequest")
  @ResponsePayload
  public UpdatePaymentResponse updatePayment(@RequestPayload UpdatePaymentRequest request) throws ParseException {
    UpdatePaymentResponse response = new UpdatePaymentResponse();
    response.setPayment(paymentRepositorySoap.updatePayment(request.getPaymentId(), request.getCcId(), request.getOrderId(), request.getPaymentAmount(),
      request.getPaymentState(), request.getPaymentStatus(), request.getTimestamp()));
    return response;
  }

}
