package kz.logistic.pl.soap.order;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.customer.*;
import soap.logistic.order.*;

import java.text.ParseException;

@Log
@Endpoint
public class OrderEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/order";

  private OrderRepositorySoap orderRepositorySoap;

  @Autowired
  public OrderEndpoint(OrderRepositorySoap orderRepositorySoap) {
    this.orderRepositorySoap = orderRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderIdRequest")
  @ResponsePayload
  public GetOrderIdResponse getOrder(@RequestPayload GetOrderIdRequest request) {
    GetOrderIdResponse response = new GetOrderIdResponse();
    response.setOrder(orderRepositorySoap.findOrderId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteOrderIdRequest")
  @ResponsePayload
  public DeleteOrderIdResponse deleteOrder(@RequestPayload DeleteOrderIdRequest request) {
    DeleteOrderIdResponse response = new DeleteOrderIdResponse();

    response.setOrder(this.orderRepositorySoap.deleteOrderId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addOrderRequest")
  @ResponsePayload
  public AddOrderResponse addOrder(@RequestPayload AddOrderRequest request) throws ParseException {
    AddOrderResponse response = new AddOrderResponse();
    response.setOrder(orderRepositorySoap.addOrder(request.getCustomerId(), request.getDeliveringStatus(),
      request.getOrderAmount(), request.getOrderDate(), request.getOrderNumber(),
      request.getProductAmount(), request.getProductId(), request.getSellerCompanyId(),
      request.getTotalPrice(), request.getUnitPrice(), request.getProductCount()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOrderRequest")
  @ResponsePayload
  public UpdateOrderResponse updateOrder(@RequestPayload UpdateOrderRequest request) throws ParseException {
    UpdateOrderResponse response = new UpdateOrderResponse();
    response.setOrder(orderRepositorySoap.updateOrder(request.getOrderId(), request.getCustomerId(), request.getDeliveringStatus(),
      request.getOrderAmount(), request.getOrderDate(), request.getOrderNumber(),
      request.getProductAmount(), request.getProductId(), request.getSellerCompanyId(),
      request.getTotalPrice(), request.getUnitPrice(), request.getProductCount()));
    return response;
  }

}
