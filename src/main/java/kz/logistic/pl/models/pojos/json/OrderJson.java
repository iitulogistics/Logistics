package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OrderJson implements Serializable {

//  @JsonProperty
//  private Long orderId;

//  @JsonProperty
//  private Integer orderNumber;

  @JsonProperty
  private Long productId;

  @JsonProperty
  private Long sellerCompanyId;

  @JsonProperty
  private Date orderDate;

  @JsonProperty
  private Integer productCount;

  @JsonProperty
  private Integer unitPrice;

  @JsonProperty
  private Integer totalPrice;

  @JsonProperty
  private Long customerId;

  @JsonProperty
  private String deliveringStatus;

  @JsonProperty
  private Integer productAmount;

  @JsonProperty
  private Integer orderAmount;

}
