package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
