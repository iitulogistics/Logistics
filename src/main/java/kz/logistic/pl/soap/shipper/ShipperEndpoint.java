package kz.logistic.pl.soap.shipper;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.shipper.*;

import java.text.ParseException;

@Log
@Endpoint
public class ShipperEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/shipper";

  private ShipperRepositorySoap shipperRepositorySoap;

  @Autowired
  public ShipperEndpoint(ShipperRepositorySoap shipperRepositorySoap) {
    this.shipperRepositorySoap = shipperRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getShipperIdRequest")
  @ResponsePayload
  public GetShipperIdResponse getShipper(@RequestPayload GetShipperIdRequest request) {
    GetShipperIdResponse response = new GetShipperIdResponse();
    response.setShipper(shipperRepositorySoap.findShipperId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteShipperIdRequest")
  @ResponsePayload
  public DeleteShipperIdResponse deleteShipper(@RequestPayload DeleteShipperIdRequest request) {
    DeleteShipperIdResponse response = new DeleteShipperIdResponse();

    response.setShipper(this.shipperRepositorySoap.deleteShipper(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addShipperRequest")
  @ResponsePayload
  public AddShipperResponse addShipper(@RequestPayload AddShipperRequest request) throws ParseException {
    AddShipperResponse response = new AddShipperResponse();
    response.setShipper(shipperRepositorySoap.addShipper(request.getShipperNameEn(), request.getShipperNameKk(),
      request.getShipperNameRu(), request.getAddress(), request.getMobilePhone(), request.getPhoneNumber(),
      request.getEmail(), request.getBin()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateShipperRequest")
  @ResponsePayload
  public UpdateShipperResponse updateShipper(@RequestPayload UpdateShipperRequest request) throws ParseException {
    UpdateShipperResponse response = new UpdateShipperResponse();
    response.setShipper(shipperRepositorySoap.updateShipper(request.getShipperId(), request.getShipperNameEn(), request.getShipperNameKk(),
      request.getShipperNameRu(), request.getAddress(), request.getMobilePhone(), request.getPhoneNumber(),
      request.getEmail(), request.getBin()));
    return response;
  }

}
