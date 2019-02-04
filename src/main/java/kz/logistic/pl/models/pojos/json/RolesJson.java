package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RolesJson implements Serializable {
    @JsonProperty
    private String roleName;
    @JsonProperty
    private String roleDescription;
}
