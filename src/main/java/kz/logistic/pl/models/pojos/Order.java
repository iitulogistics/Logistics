package kz.logistic.pl.models.pojos;

import java.util.Date;

public interface Order {

  Long getOrderId();

  Long getOrderNumber();

  Long getProductId();

  Long getSellerCompanyId();

  Date getOrderDate();

  Integer getProductCount();

  Integer getUnitPrice();

  Integer getTotalPrice();

  Long getCustomerId();

  String getDeliveringStatus();

  Integer getProductAmount();

  Integer getOrderAmount();

}
