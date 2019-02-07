package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellerCompanyJson implements Serializable {

    @JsonProperty
    private String password;
    @JsonProperty
    private String sellerCompanyBin;
    @JsonProperty
    private String sellerCompanyEmail;
    @JsonProperty
    private String sellerCompanyMobilePhone;
    @JsonProperty
    private String sellerCompanyNameEn;
    @JsonProperty
    private String sellerCompanyNameKk;
    @JsonProperty
    private String sellerCompanyNameRu;
    @JsonProperty
    private String sellerCompanyPhone;
    @JsonProperty
    private String username;
}
