package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.Shipper;
import kz.logistic.pl.models.pojos.impl.DefaultShipper;
import kz.logistic.pl.models.pojos.json.ShipperJson;


public interface ShipperService {

  List<Shipper> showAllShippers();

  DefaultShipper showShipper(Long shipperId);

  void addShipper(String username, String password);

  void addShipperJson(ShipperJson shipperJson);

  String updateShipper(Long shipperId, ShipperJson shipperJson);

  String deleteShipper(Long shipperId);

}