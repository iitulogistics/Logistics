package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.Shipper;
import lombok.Builder;

@Builder
public class DefaultShipper implements Shipper {

    private Long shipperId;
    private String username;
    private String password;
    private LocalizedMessage shipperName;

    @Override
    public Long getShipperId() {
        return shipperId;
    }

    @Override
    public String getShipperUsername() {
        return username;
    }

    @Override
    public String getShipperPassword() {
        return password;
    }

    @Override
    public LocalizedMessage getShipperName() {
        return shipperName;
    }

}