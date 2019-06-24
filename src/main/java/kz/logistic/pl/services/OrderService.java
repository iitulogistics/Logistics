package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Order;
import kz.logistic.pl.models.pojos.impl.DefaultOrder;
import kz.logistic.pl.models.pojos.json.OrderJson;

import java.util.Date;
import java.util.List;

public interface OrderService {

  List<Order> showAllOrders();

  List<Order> showOrdersBySeller(Long sellerCompanyId);

  List<Order> showOrdersByCustomer(Long customerId);

  DefaultOrder showOrder(Long orderId);

  String addOrder(
    Long productId,
    Long sellerCompanyId,
    Date orderDate,
    Integer productCount,
    Integer unitPrice,
    Long customerId,
    String deliveringStatus,
    Integer productAmount,
    Integer orderAmount);

  String addOrderJson(OrderJson orderJson);

  String updateOrder(Long orderId, OrderJson orderJson);

  String deleteOrder(Long orderId);

}
