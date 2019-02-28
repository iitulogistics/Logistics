package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellerCategoryJson implements Serializable {
  @JsonProperty
  private String sellerCategoryNameKk;
  @JsonProperty
  private String sellerCategoryNameRu;
  @JsonProperty
  private String sellerCategoryNameEn;
  @JsonProperty
  private String addInfo;
}
