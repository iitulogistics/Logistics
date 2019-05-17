package kz.logistic.pl.soap.city;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.city.*;

@Log
@Endpoint
public class CityEndpoint {

    private static final String NAMESPACE_URI = "http://logistic.soap/city";

    private CityRepositorySoap cityRepositorySoap;

    @Autowired
    public CityEndpoint(CityRepositorySoap cityRepositorySoap) {
        this.cityRepositorySoap = cityRepositorySoap;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCityIdRequest")
    @ResponsePayload
    public GetCityIdResponse getCity(@RequestPayload GetCityIdRequest request) {
        GetCityIdResponse response = new GetCityIdResponse();
        response.setCity(cityRepositorySoap.findCityId(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCityIdRequest")
    @ResponsePayload
    public DeleteCityIdResponse deleteCityIdResponse(@RequestPayload DeleteCityIdRequest request) {
        DeleteCityIdResponse response = new DeleteCityIdResponse();

        response.setCity(this.cityRepositorySoap.deleteCityId(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCityRequest")
    @ResponsePayload
    public AddCityResponse addCity(@RequestPayload AddCityRequest request) {
        AddCityResponse response = new AddCityResponse();
        response.setCity(cityRepositorySoap.addCity(request.getCountryId(), request.getRegionId(), request.getCityNameEn(), request.getCityNameRu(), request.getCityNameKk()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCityRequest")
    @ResponsePayload
    public UpdateCityResponse updateCity(@RequestPayload UpdateCityRequest request) {
        UpdateCityResponse response = new UpdateCityResponse();
        response.setCity(cityRepositorySoap.updateCity(
            request.getId(), request.getCityNameEn(), request.getCityNameRu(), request.getCityNameKk()));
        return response;
    }

}
