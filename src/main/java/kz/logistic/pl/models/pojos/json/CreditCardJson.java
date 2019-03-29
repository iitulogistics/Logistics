package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class CreditCardJson implements Serializable {
  @JsonProperty
  private Integer creditCardNumber;

  @JsonProperty
  private String holderName;

  @JsonProperty
  private Date expireDate;
}
