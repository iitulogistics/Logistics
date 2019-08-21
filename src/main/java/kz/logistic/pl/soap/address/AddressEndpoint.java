package kz.logistic.pl.soap.address;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.address.*;

@Log
@Endpoint
public class AddressEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/address";

  private AddressRepositorySoap addressRepositorySoap;

  @Autowired
  public AddressEndpoint(AddressRepositorySoap addressRepositorySoap) {
    this.addressRepositorySoap = addressRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAddressIdRequest")
  @ResponsePayload
  public GetAddressIdResponse getAddress(@RequestPayload GetAddressIdRequest request) {
    GetAddressIdResponse response = new GetAddressIdResponse();
    response.setAddress(addressRepositorySoap.findAddressId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAddressIdRequest")
  @ResponsePayload
  public DeleteAddressIdResponse deleteAddress(@RequestPayload DeleteAddressIdRequest request) {
    DeleteAddressIdResponse response = new DeleteAddressIdResponse();

    response.setAddress(this.addressRepositorySoap.deleteAddressId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAddressRequest")
  @ResponsePayload
  public AddAddressResponse addAddress(@RequestPayload AddAddressRequest request) {
    AddAddressResponse response = new AddAddressResponse();
    response.setAddress(addressRepositorySoap.addAddress(request.getDistrictId(),
    request.getIhnLocalityId(),
    request.getStreetNameKk(),
    request.getStreetNameRu(),
    request.getStreetNameEn(),
    request.getBuildingNumber(),
    request.getFlatNumber(),
    request.getZipCode(),
    request.getAddressAssign()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAddressRequest")
  @ResponsePayload
  public UpdateAddressResponse updateAddress(@RequestPayload UpdateAddressRequest request) {
    UpdateAddressResponse response = new UpdateAddressResponse();
    response.setAddress(addressRepositorySoap.updateAddress(request.getId(),
      request.getDistrictId(),
      request.getIhnLocalityId(),
      request.getStreetNameKk(),
      request.getStreetNameRu(),
      request.getStreetNameEn(),
      request.getBuildingNumber(),
      request.getFlatNumber(),
      request.getZipCode(),
      request.getAddressAssign()));
    return response;
  }

}
