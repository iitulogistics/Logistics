package kz.logistic.pl.soap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.logistics.*;

@Endpoint
public class CountryEndpoint {
  private static final String NAMESPACE_URI = "http://logistic.soap/logistics";

  private CountryRepositorySoap countryRepositorySoap;

  @Autowired
  public CountryEndpoint(CountryRepositorySoap countryRepositorySoap) {
    this.countryRepositorySoap = countryRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryIdRequest")
  @ResponsePayload
  public GetCountryIdResponse getCountry(@RequestPayload GetCountryIdRequest request) {
    GetCountryIdResponse response = new GetCountryIdResponse();
    response.setCountry(countryRepositorySoap.findCountryId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountryIdRequest")
  @ResponsePayload
  public DeleteCountryIdResponse deleteCountryIdResponse(@RequestPayload DeleteCountryIdRequest request) {
    DeleteCountryIdResponse response = new DeleteCountryIdResponse();

    response.setCountry(this.countryRepositorySoap.deleteCountryId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
  @ResponsePayload
  public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
    AddCountryResponse response = new AddCountryResponse();
    response.setCountry(countryRepositorySoap.add(request.getCountryNameKk(), request.getCountryNameRu(), request.getCountryNameEn()));

    return response;
  }
}
