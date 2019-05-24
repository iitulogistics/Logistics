package kz.logistic.pl.models.pojos.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InfoBinJson implements Serializable {
    private String pid;
    private String bin;
    private String name_ru;
    private String name_kz;
    private String email;
    private String phone;
    private List address;
    private String regdate;
    private String crdate;
    private String number_reg;
    private String website;
    private String country_code;
    private String last_update_date;
    private String qvazi;
    private String customer;
    private String organizer;
    private String mark_national_company;
    private String ref_kopf_code;
    private String mark_assoc_with_disab;
    private String year;

}
