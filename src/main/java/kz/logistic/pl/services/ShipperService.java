package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Shipper;
import kz.logistic.pl.models.pojos.json.ShipperJson;

import java.util.List;

public interface ShipperService {

    List<Shipper> showAllShippers();

    void addShipper(String username, String password);

    void addShipperJson(ShipperJson shipperJson);

}