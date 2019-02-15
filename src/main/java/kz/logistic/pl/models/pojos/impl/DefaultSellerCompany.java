package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.SellerCompany;
import lombok.Builder;

import java.util.Objects;

@Builder
public class DefaultSellerCompany implements SellerCompany {

    private Long id;
    private LocalizedMessage sellerCompanyName;
    private String sellerCompanyPhone;
    private String sellerCompanyMobilePhone;
    private String sellerCompanyBin;
    private String sellerCompanyEmail;
    private Long sellerCategoryId;

    @Override
    public long getSellerCompanyId() {
        return id;
    }

    @Override
    public LocalizedMessage getSellerCompanyName() {
        return sellerCompanyName;
    }

    @Override
    public String getSellerCompanyPhone() {
        return sellerCompanyPhone;
    }

    @Override
    public String getSellerCompanyMobilePhone() {
        return sellerCompanyMobilePhone;
    }

    @Override
    public String getSellerCompanyBin() {
        return sellerCompanyBin;
    }

    @Override
    public String getSellerCompanyEmail() {
        return sellerCompanyEmail;
    }

    @Override
    public long getSellerCategoryId() {
        if (Objects.isNull(sellerCategoryId))
            return 0;
        else {
            return sellerCategoryId;
        }
    }
}
