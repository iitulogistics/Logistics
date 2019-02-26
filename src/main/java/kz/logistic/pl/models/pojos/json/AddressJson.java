package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AddressJson implements Serializable {

  @JsonProperty
  private String streetNameKk;

  @JsonProperty
  private String streetNameRu;

  @JsonProperty
  private String streetNameEn;

  @JsonProperty
  private Long inhLocalityId;

  @JsonProperty
  private Long districtId;

  @JsonProperty
  private String buildingNumber;

  @JsonProperty
  private String flatNumber;

  @JsonProperty
  private String zipCode;

  @JsonProperty
  private Integer addressAssign;

}
