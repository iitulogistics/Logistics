package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialCharacteristicJson {

  @JsonProperty
  private String characteristicNameKk;

  @JsonProperty
  private String characteristicNameRu;

  @JsonProperty
  private String characteristicNameEn;

  @JsonProperty
  private String addInfo;

}
