package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CityJson implements Serializable {

    @JsonProperty
    private String cityNameKk;

    @JsonProperty
    private String cityNameRu;

    @JsonProperty
    private String cityNameEn;

    @JsonProperty
    private Long regionId;

    @JsonProperty
    private Long countryId;

}
