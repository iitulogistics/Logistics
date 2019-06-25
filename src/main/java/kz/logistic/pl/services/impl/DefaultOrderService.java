package kz.logistic.pl.services.impl;

import kz.logistic.pl.utils.ReturnMessage;
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
  private ReturnMessage returnMessage;

    @Autowired(required = false)

    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }
  @Autowired
  private void setOrdersRepository(OrdersRepository ordersRepository) {
    this.ordersRepository = ordersRepository;
  }

  @Override
  public List<Order> showAllOrders() {
    List<OrdersEntity> ordersEntities = this.ordersRepository.findAll();

    return ordersEntities.stream().map(ordersEntity ->
        getOrderBuilder(ordersEntity)
    ).collect(Collectors.toList());
  }

    @Override
    public List<Order> showOrdersBySeller(Long sellerCompanyId) {
        List<OrdersEntity> ordersEntities = this.ordersRepository.findBySellerCompanyId(sellerCompanyId);

        return ordersEntities.stream().map(ordersEntity ->
            getOrderBuilder(ordersEntity)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Order> showOrdersByCustomer(Long customerId) {
        List<OrdersEntity> ordersEntities = this.ordersRepository.findByCustomerId(customerId);

        return ordersEntities.stream().map(ordersEntity ->
            getOrderBuilder(ordersEntity)
        ).collect(Collectors.toList());
    }

    private DefaultOrder getOrderBuilder(OrdersEntity ordersEntity) {
        return DefaultOrder.builder()
      .customerId(ordersEntity.getCustomerId())
      .deliveringStatus(ordersEntity.getDeliveringStatus())
      .orderAmount(ordersEntity.getOrderAmount())
      .orderDate(ordersEntity.getOrderDate())
      .orderId(ordersEntity.getOrderId())
      .orderNumber(ordersEntity.getOrderNumber())
      .productAmount(ordersEntity.getProductAmount())
      .productCount(ordersEntity.getProductCount())
      .productId(ordersEntity.getProductId())
      .totalPrice(ordersEntity.getTotalPrice())
      .sellerCompanyId(ordersEntity.getSellerCompanyId())
      .unitPrice(ordersEntity.getUnitPrice()).build();
    }

    @Override
  public DefaultOrder showOrder(Long orderId) {

    OrdersEntity ordersEntity = this.ordersRepository.findById(orderId).orElse(null);

    return  getOrderBuilder(ordersEntity);
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
      return java.text.MessageFormat.format(returnMessage.getOrderAddSuccess(), productId);
    }

  @Override
  public String addOrderJson(List<OrderJson> orderJson) {


      for (OrderJson order :
           orderJson) {
          OrdersEntity ordersEntity = new OrdersEntity();
          ordersEntity.setOrderNumber(UniqueSeq.getNext());
          ordersEntity.setProductId(order.getProductId());
          ordersEntity.setSellerCompanyId(order.getSellerCompanyId());
          ordersEntity.setUnitPrice(order.getUnitPrice());
          ordersEntity.setOrderDate(order.getOrderDate());
          ordersEntity.setProductCount(order.getProductCount());
          ordersEntity.setUnitPrice(order.getUnitPrice());
          ordersEntity.setTotalPrice(order.getTotalPrice());
          ordersEntity.setCustomerId(order.getCustomerId());
          ordersEntity.setDeliveringStatus(order.getDeliveringStatus());
          ordersEntity.setProductAmount(order.getProductAmount());
          ordersEntity.setOrderAmount(order.getOrderAmount());

          ordersRepository.save(ordersEntity);
      }

 return  "good";
    //log.info("New order " + ordersEntity.getOrderNumber() + " added via Json" + " " + new Date());
      //return java.text.MessageFormat.format(returnMessage.getOrderAddSuccess(), ordersEntity.getOrderNumber());
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
        return java.text.MessageFormat.format(returnMessage.getOrderUpdateSuccess(), ordersEntity.getOrderNumber());
    } else {
        return java.text.MessageFormat.format(returnMessage.getOrderUpdateError(), ordersEntity.getOrderNumber());

    }
  }

  @Override
  public String deleteOrder(Long orderId) {
    OrdersEntity ordersEntity = ordersRepository.findById(orderId).orElse(null);
    if (Objects.nonNull(ordersEntity)) {
      log.info("Deleted order " + ordersEntity.getOrderNumber());
      this.ordersRepository.deleteById(ordersEntity.getOrderId());
        return java.text.MessageFormat.format(returnMessage.getOrderDeleteSuccess(), ordersEntity.getOrderNumber());

    } else {
        return java.text.MessageFormat.format(returnMessage.getOrderDeleteError(), ordersEntity.getOrderNumber());
    }
  }
}
