package kz.logistic.pl.services;

import java.util.List;
import kz.logistic.pl.models.pojos.Shipper;
import kz.logistic.pl.models.pojos.impl.DefaultShipper;
import kz.logistic.pl.models.pojos.json.ShipperJson;


public interface ShipperService {

  List<Shipper> showAllShippers();

  DefaultShipper showShipper(Long shipperId);

  String addShipper(String username,
                    String password,
                    String shipperNameKk,
                    String shipperNameRu,
                    String shipperNameEn,
                    String phoneNumber,
                    String bin,
                    String email,
                    String address);

  String addShipperJson(ShipperJson shipperJson);

  boolean exists(String username);

  String updateShipper(Long shipperId, ShipperJson shipperJson);

  String deleteShipper(Long shipperId);

}