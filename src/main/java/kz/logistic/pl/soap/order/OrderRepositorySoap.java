package kz.logistic.pl.soap.order;

import kz.logistic.pl.models.entities.OrdersEntity;
import kz.logistic.pl.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.order.Order;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderRepositorySoap {
  private static final Map<Long, Order> orderMap = new HashMap<>();

  private OrdersRepository ordersRepository;

  @Autowired(required = false)
  public void setOrdersRepository(OrdersRepository ordersRepository) {
    this.ordersRepository = ordersRepository;
  }

  @PostConstruct
  public void initData() {
    List<OrdersEntity> entities = this.ordersRepository.findAll();
    entities.forEach(ordersEntity -> {
      Order order = convertToOrder(ordersEntity);

      orderMap.put(order.getOrderId(), order);
    });
  }

  public Order findOrderId(Long id) {
    return orderMap.get(id);
  }

  public Order addOrder(Long customerId, String deliveringStatus, int orderAmount,
                        String orderDate, Long orderNumber,
                        int productAmount, Long productId, Long sellerCompanyId,
                        int totalPrice, int unitPrice, int productCount) throws ParseException {
    OrdersEntity ordersEntity = new OrdersEntity();
    ordersEntity.setCustomerId(customerId);
    ordersEntity.setDeliveringStatus(deliveringStatus);
    ordersEntity.setOrderAmount(orderAmount);
    ordersEntity.setOrderDate(new SimpleDateFormat().parse(orderDate));
    ordersEntity.setOrderNumber(orderNumber);
    ordersEntity.setProductAmount(productAmount);
    ordersEntity.setProductId(productId);
    ordersEntity.setSellerCompanyId(sellerCompanyId);
    ordersEntity.setTotalPrice(totalPrice);
    ordersEntity.setUnitPrice(unitPrice);
    ordersEntity.setProductCount(productCount);

    ordersRepository.save(ordersEntity);

    Order order = convertToOrder(ordersEntity);
    orderMap.put(order.getOrderId(), order);

    return order;
  }

  public Order updateOrder(Long id, Long customerId, String deliveringStatus, int orderAmount,
                           String orderDate, Long orderNumber,
                           int productAmount, Long productId, Long sellerCompanyId,
                           int totalPrice, int unitPrice, int productCount) throws ParseException {
    Order order = orderMap.get(id);
    order.setCustomerId(customerId);
    order.setDeliveringStatus(deliveringStatus);
    order.setOrderAmount(orderAmount);
    order.setOrderDate(orderDate);
    order.setOrderNumber(orderNumber);
    order.setProductAmount(productAmount);
    order.setProductId(productId);
    order.setSellerCompanyId(sellerCompanyId);
    order.setTotalPrice(totalPrice);
    order.setUnitPrice(unitPrice);
    order.setProductCount(productCount);

    ordersRepository.updateOrderById(id, customerId, deliveringStatus, orderAmount,
      new SimpleDateFormat().parse(orderDate), orderNumber, productAmount, productId, sellerCompanyId,
      totalPrice, unitPrice, productCount);
    return order;
  }

  public String deleteOrderId(Long id) {
    OrdersEntity ordersEntity = this.ordersRepository.findById(id).orElse(null);

    if (ordersEntity != null) {
      this.ordersRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Order convertToOrder(OrdersEntity entity) {
    Order order = new Order();
    order.setCustomerId(entity.getCustomerId());
    order.setDeliveringStatus(entity.getDeliveringStatus());
    order.setOrderAmount(entity.getOrderAmount());
    order.setOrderDate(entity.getOrderDate().toString());
    order.setOrderId(entity.getOrderId());
    order.setOrderNumber(entity.getOrderNumber());
    order.setProductAmount(entity.getProductAmount());
    order.setProductId(entity.getProductId());
    order.setSellerCompanyId(entity.getSellerCompanyId());
    order.setTotalPrice(entity.getTotalPrice());
    order.setUnitPrice(entity.getUnitPrice());
    order.setProductCount(entity.getProductCount());

    return order;
  }
}
