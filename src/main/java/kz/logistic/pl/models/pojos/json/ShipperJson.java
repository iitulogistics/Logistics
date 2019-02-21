package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShipperJson implements Serializable {

  @JsonProperty
  private String username;

  @JsonProperty
  private String password;

  @JsonProperty
  private String shipperNameKk;

  @JsonProperty
  private String shipperNameRu;

  @JsonProperty
  private String shipperNameEn;

  @JsonProperty
  private String mobilePhone;

  @JsonProperty
  private String phoneNumber;

  @JsonProperty
  private String bin;

  @JsonProperty
  private String email;

  @JsonProperty
  private String address;

}