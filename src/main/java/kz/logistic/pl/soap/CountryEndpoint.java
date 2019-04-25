package kz.logistic.pl.soap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;
import soap.logistic.logistics.DeleteCountryIdRequest;
import soap.logistic.logistics.DeleteCountryIdResponse;
import soap.logistic.logistics.GetCountryIdRequest;
import soap.logistic.logistics.GetCountryIdResponse;

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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountyIdRequest")
    @Namespace(prefix = "delete",uri = NAMESPACE_URI)
    @ResponsePayload
    public DeleteCountryIdResponse deleteCountry(@RequestPayload DeleteCountryIdRequest request){
      DeleteCountryIdResponse response = new DeleteCountryIdResponse();
    //  response.setResult(countryRepositorySoap.deleteCountryId(request.getId()));
      return response;
    }
}
