package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductJson implements Serializable {

  @JsonProperty
  private Long productId;

  @JsonProperty
  private String productNameKk;

  @JsonProperty
  private String productNameRu;

  @JsonProperty
  private String productNameEn;

  @JsonProperty
  private String description;

  @JsonProperty
  private Long sellerCompanyId;

  @JsonProperty
  private Long sellerCategoryId;
}