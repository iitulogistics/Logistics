package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerJson implements Serializable {

  @JsonProperty
  private String username;

  @JsonProperty
  private String password;

}