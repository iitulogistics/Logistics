package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductSubCategoryJson implements Serializable {

    @JsonProperty
    private String subCategoryNameKk;

    @JsonProperty
    private String subCategoryNameRu;

    @JsonProperty
    private String subCategoryNameEn;

    @JsonProperty
    private Long productCategoryId;

    @JsonProperty
    private String subCategoryAddInfo;

}
