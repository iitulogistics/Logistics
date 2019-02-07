package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
