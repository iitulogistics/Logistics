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

  @JsonProperty
  private String customerNameKk;

  @JsonProperty
  private String customerNameRu;

  @JsonProperty
  private String customerNameEn;

  @JsonProperty
  private String mobilePhone;

  @JsonProperty
  private String email;

  @JsonProperty
  private String phoneNumber;

  @JsonProperty
  private String addInfo;

  @JsonProperty
  private String iinOrBin;

}
