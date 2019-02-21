package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CityJson implements Serializable {

  @JsonProperty
  private String cityNameKk;

  @JsonProperty
  private String cityNameRu;

  @JsonProperty
  private String cityNameEn;

  @JsonProperty
  private Long regionId;

  @JsonProperty
  private Long countryId;

}
