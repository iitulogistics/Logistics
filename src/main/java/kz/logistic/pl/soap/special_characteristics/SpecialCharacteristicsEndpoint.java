package kz.logistic.pl.soap.special_characteristics;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.special_characteristics.*;


import java.text.ParseException;

@Log
@Endpoint
public class SpecialCharacteristicsEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/special_characteristics";

  private SpecialCharacteristicsRepositorySoap SpecialCharacteristicRepositorySoap;

  @Autowired
  public SpecialCharacteristicsEndpoint(SpecialCharacteristicsRepositorySoap SpecialCharacteristicRepositorySoap) {
    this.SpecialCharacteristicRepositorySoap = SpecialCharacteristicRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSpecialCharacteristicsIdRequest")
  @ResponsePayload
  public GetSpecialCharacteristicsIdResponse getSpecialCharacteristics(@RequestPayload GetSpecialCharacteristicsIdRequest request) {
    GetSpecialCharacteristicsIdResponse response = new GetSpecialCharacteristicsIdResponse();
    response.setSpecialCharacteristics(SpecialCharacteristicRepositorySoap.findSpecialCharacteristicsId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteSpecialCharacteristicsIdRequest")
  @ResponsePayload
  public DeleteSpecialCharacteristicsIdResponse deleteSpecialCharacteristics(@RequestPayload DeleteSpecialCharacteristicsIdRequest request) {
    DeleteSpecialCharacteristicsIdResponse response = new DeleteSpecialCharacteristicsIdResponse();

    response.setSpecialCharacteristics(this.SpecialCharacteristicRepositorySoap.deleteSpecialCharacteristics(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSpecialCharacteristicsRequest")
  @ResponsePayload
  public AddSpecialCharacteristicsResponse addSpecialCharacteristics(@RequestPayload AddSpecialCharacteristicsRequest request) throws ParseException {
    AddSpecialCharacteristicsResponse response = new AddSpecialCharacteristicsResponse();
    response.setSpecialCharacteristics(SpecialCharacteristicRepositorySoap.addSpecialCharacteristics(request.getCharacteristicsNameEn(),
      request.getCharacteristicsNameRu(), request.getCharacteristicsNameKk(), request.getAddInfo()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSpecialCharacteristicsRequest")
  @ResponsePayload
  public UpdateSpecialCharacteristicsResponse updateSpecialCharacteristics(@RequestPayload UpdateSpecialCharacteristicsRequest request) throws ParseException {
    UpdateSpecialCharacteristicsResponse response = new UpdateSpecialCharacteristicsResponse();
    response.setSpecialCharacteristics(SpecialCharacteristicRepositorySoap.updateSpecialCharacteristics(request.getSpecialCharacteristicId(),
      request.getCharacteristicsNameEn(), request.getCharacteristicsNameRu(),
      request.getCharacteristicsNameKk(), request.getAddInfo()));
    return response;
  }

}
