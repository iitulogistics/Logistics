package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Order;
import lombok.Builder;

import java.util.Date;

@Builder
public class DefaultOrder implements Order {

  private Long orderId;
  private Long orderNumber;
  private Long productId;
  private Long sellerCompanyId;
  private Date orderDate;
  private Integer productCount;
  private Integer unitPrice;
  private Integer totalPrice;
  private Long customerId;
  private String deliveringStatus;
  private Integer productAmount;
  private Integer orderAmount;

  @Override
  public Long getOrderId() {
    return orderId;
  }

  @Override
  public Long getOrderNumber() {
    return orderNumber;
  }

  @Override
  public Long getProductId() {
    return productId;
  }

  @Override
  public Long getSellerCompanyId() {
    return sellerCompanyId;
  }

  @Override
  public Date getOrderDate() {
    return orderDate;
  }

  @Override
  public Integer getProductCount() {
    return productCount;
  }

  @Override
  public Integer getUnitPrice() {
    return unitPrice;
  }

  @Override
  public Integer getTotalPrice() {
    return totalPrice;
  }

  @Override
  public Long getCustomerId() {
    return customerId;
  }

  @Override
  public String getDeliveringStatus() {
    return deliveringStatus;
  }

  @Override
  public Integer getProductAmount() {
    return productAmount;
  }

  @Override
  public Integer getOrderAmount() {
    return orderAmount;
  }
}
