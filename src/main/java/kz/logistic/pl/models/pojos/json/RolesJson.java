package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RolesJson implements Serializable {
  @JsonProperty
  private String roleName;
  @JsonProperty
  private String roleDescription;
}
