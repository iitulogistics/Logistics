package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import lombok.Getter;

@Getter
public class DefaultLocalizedMessage implements LocalizedMessage {

    private String ru;
    private String kk;
    private String en;

    public DefaultLocalizedMessage(String ru, String kk, String en) {
        this.ru = ru;
        this.kk = kk;
        this.en = en;
    }


}
