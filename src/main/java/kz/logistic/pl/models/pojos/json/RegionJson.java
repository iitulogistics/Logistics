package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegionJson implements Serializable {

  @JsonProperty
  private String regionNameKk;

  @JsonProperty
  private String regionNameRu;

  @JsonProperty
  private String regionNameEn;

  @JsonProperty
  private Long countryId;

}
