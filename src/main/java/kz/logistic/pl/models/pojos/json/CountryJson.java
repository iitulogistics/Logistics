package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CountryJson implements Serializable {

  @JsonProperty
  private String countryNameKk;

  @JsonProperty
  private String countryNameRu;

  @JsonProperty
  private String countryNameEn;

}
