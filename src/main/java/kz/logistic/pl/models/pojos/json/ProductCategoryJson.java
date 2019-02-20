package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductCategoryJson implements Serializable {

  @JsonProperty
  private String categoryNameKk;

  @JsonProperty
  private String categoryNameRu;

  @JsonProperty
  private String categoryNameEn;

  @JsonProperty
  private String addInfo;

}
