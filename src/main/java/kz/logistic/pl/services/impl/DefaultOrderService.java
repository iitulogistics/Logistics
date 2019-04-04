package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.OrdersEntity;
import kz.logistic.pl.models.pojos.Order;
import kz.logistic.pl.models.pojos.impl.DefaultOrder;
import kz.logistic.pl.models.pojos.json.OrderJson;
import kz.logistic.pl.repositories.OrdersRepository;
import kz.logistic.pl.services.OrderService;
import kz.logistic.pl.utils.UniqueSeq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultOrderService implements OrderService {

  private OrdersRepository ordersRepository;

  @Autowired
  private void setOrdersRepository(OrdersRepository ordersRepository) {
    this.ordersRepository = ordersRepository;
  }

  @Override
  public List<Order> showAllOrders() {
    List<OrdersEntity> ordersEntities = this.ordersRepository.findAll();

    return ordersEntities.stream().map(ordersEntity -> DefaultOrder.builder()
      .customerId(ordersEntity.getCustomerId())
      .deliveringStatus(ordersEntity.getDeliveringStatus())
      .orderAmount(ordersEntity.getOrderAmount())
      .orderDate(ordersEntity.getOrderDate())
      .orderId(ordersEntity.getOrderId())
      .orderNumber(ordersEntity.getOrderNumber())
      .productAmount(ordersEntity.getProductAmount())
      .productCount(ordersEntity.getProductCount())
      .productId(ordersEntity.getProductId())
      .sellerCompanyId(ordersEntity.getSellerCompanyId())
      .unitPrice(ordersEntity.getUnitPrice()).build()
    ).collect(Collectors.toList());
  }

  @Override
  public DefaultOrder showOrder(Long orderId) {

    OrdersEntity ordersEntity = this.ordersRepository.findById(orderId).orElse(null);

    return DefaultOrder.builder()
      .orderId(ordersEntity.getOrderId())
      .orderNumber(ordersEntity.getOrderNumber())
      .unitPrice(ordersEntity.getUnitPrice())
      .sellerCompanyId(ordersEntity.getSellerCompanyId())
      .productId(ordersEntity.getProductId())
      .productCount(ordersEntity.getProductCount())
      .productAmount(ordersEntity.getProductAmount())
      .orderNumber(ordersEntity.getOrderNumber())
      .orderDate(ordersEntity.getOrderDate())
      .orderAmount(ordersEntity.getOrderAmount())
      .deliveringStatus(ordersEntity.getDeliveringStatus())
      .customerId(ordersEntity.getCustomerId()).build();
  }

  @Override
  public String addOrder(Long productId,
                         Long sellerCompanyId, Date orderDate,
                         Integer productCount, Integer unitPrice,
                         Long customerId, String deliveringStatus,
                         Integer productAmount, Integer orderAmount) {
    Integer totalPrice = unitPrice * productAmount;
    OrdersEntity ordersEntity = new OrdersEntity();
    ordersEntity.setDeliveringStatus(deliveringStatus);
    ordersEntity.setOrderAmount(orderAmount);
    ordersEntity.setOrderDate(orderDate);
    ordersEntity.setCustomerId(customerId);
    ordersEntity.setProductAmount(productAmount);
    ordersEntity.setProductCount(productCount);
    ordersEntity.setProductId(productId);
    ordersEntity.setSellerCompanyId(sellerCompanyId);
    ordersEntity.setTotalPrice(totalPrice);
    ordersEntity.setUnitPrice(unitPrice);
    ordersRepository.save(ordersEntity);
    log.info("New order added " + ordersEntity.getOrderNumber() + " " + new Date());
    return "Заказ добавлен";
  }

  @Override
  public String addOrderJson(OrderJson orderJson) {
    OrdersEntity ordersEntity = new OrdersEntity();

    ordersEntity.setOrderNumber(UniqueSeq.getNext());
    ordersEntity.setProductId(orderJson.getProductId());
    ordersEntity.setSellerCompanyId(orderJson.getSellerCompanyId());
    ordersEntity.setUnitPrice(orderJson.getUnitPrice());
    ordersEntity.setOrderDate(orderJson.getOrderDate());
    ordersEntity.setProductCount(orderJson.getProductCount());
    ordersEntity.setUnitPrice(orderJson.getUnitPrice());
    ordersEntity.setTotalPrice(orderJson.getTotalPrice());
    ordersEntity.setCustomerId(orderJson.getCustomerId());
    ordersEntity.setDeliveringStatus(orderJson.getDeliveringStatus());
    ordersEntity.setProductAmount(orderJson.getProductAmount());
    ordersEntity.setOrderAmount(orderJson.getOrderAmount());

    ordersRepository.save(ordersEntity);
    log.info("New order " + ordersEntity.getOrderNumber() + " added via Json" + " " + new Date());
    return "Заказ добавлен посредством JSON";
  }

  @Override
  public String updateOrder(Long orderId, OrderJson orderJson) {
    OrdersEntity ordersEntity = ordersRepository.findById(orderId).orElse(null);
    if (Objects.nonNull(ordersEntity)) {
      if (orderJson.getCustomerId() != null) {
        ordersEntity.setCustomerId(orderJson.getCustomerId());
      }
      if (orderJson.getUnitPrice() != null) {
        ordersEntity.setUnitPrice(orderJson.getUnitPrice());
      }
      if (orderJson.getSellerCompanyId() != null) {
        ordersEntity.setSellerCompanyId(orderJson.getSellerCompanyId());
      }
      if (orderJson.getProductId() != null) {
        ordersEntity.setProductId(orderJson.getProductId());
      }
      if (orderJson.getProductCount() != null) {
        ordersEntity.setProductCount(orderJson.getProductCount());
      }
      if (orderJson.getProductAmount() != null) {
        ordersEntity.setProductAmount(orderJson.getProductAmount());
      }
      if (orderJson.getOrderDate() != null) {
        ordersEntity.setOrderDate(orderJson.getOrderDate());
      }
      if (orderJson.getOrderAmount() != null) {
        ordersEntity.setOrderAmount(orderJson.getOrderAmount());
      }
      ordersRepository.save(ordersEntity);
      log.info("Updated order " + ordersEntity.getOrderNumber());
      return "Заказ обнавлен";
    } else {
      return "Заказ с таким id не существует";
    }
  }

  @Override
  public String deleteOrder(Long orderId) {
    OrdersEntity ordersEntity = ordersRepository.findById(orderId).orElse(null);
    if (Objects.nonNull(ordersEntity)) {
      log.info("Deleted order " + ordersEntity.getOrderNumber());
      this.ordersRepository.deleteById(ordersEntity.getOrderId());
      return "Заказ удален";
    } else {
      return "Заказ с таким id не существует";
    }
  }
}
