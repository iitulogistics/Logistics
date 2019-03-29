package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductJson implements Serializable {

  @JsonProperty
  private String productNameKk;

  @JsonProperty
  private String productNameRu;

  @JsonProperty
  private String productNameEn;

  @JsonProperty
  private Long productSubcategoryId;

  @JsonProperty
  private Long productCategoryId;

  @JsonProperty
  private String uniqueIdNumber;

  @JsonProperty
  private String serialNumber;

  @JsonProperty
  private String manufacturer;

  @JsonProperty
  private String size;

  @JsonProperty
  private Integer weight;

  @JsonProperty
  private Integer price;

  @JsonProperty
  private String productDescription;

  @JsonProperty
  private Long sellerCompanyId;

  @JsonProperty
  private Long specialCharacteristicId;

}