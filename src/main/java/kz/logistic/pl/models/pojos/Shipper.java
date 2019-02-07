package kz.logistic.pl.models.pojos;

public interface Shipper {

    Long getShipperId();

    String getShipperUsername();

    String getShipperPassword();

    LocalizedMessage getShipperName();

}