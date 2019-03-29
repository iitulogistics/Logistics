package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DistrictJson implements Serializable {

  @JsonProperty
  private String districtNameKk;

  @JsonProperty
  private String districtNameRu;

  @JsonProperty
  private String districtNameEn;

  @JsonProperty
  private Long regionId;

  @JsonProperty
  private Long cityId;

}
