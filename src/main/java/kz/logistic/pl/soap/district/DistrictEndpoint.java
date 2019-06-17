package kz.logistic.pl.soap.district;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.district.*;

@Log
@Endpoint
public class DistrictEndpoint {
    private static final String NAMESPACE_URI = "http://logistic.soap/district";

    private DistrictRepositorySoap districtRepositorySoap;

    @Autowired
    public DistrictEndpoint(DistrictRepositorySoap districtRepositorySoap){this.districtRepositorySoap=districtRepositorySoap;}

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDistrictIdRequest")
    @ResponsePayload
    public GetDistrictIdResponse getDistrict(@RequestPayload GetDistrictIdRequest request){
        GetDistrictIdResponse response = new GetDistrictIdResponse();
        response.setDistrict(districtRepositorySoap.findDistrictId(request.getId()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteDistrictIdRequest")
    @ResponsePayload
    public DeleteDistrictIdResponse deleteDistrict(@RequestPayload DeleteDistrictIdRequest request){
        DeleteDistrictIdResponse response = new DeleteDistrictIdResponse();
        response.setDistrict(districtRepositorySoap.deleteDistrictId(request.getId()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addDistrictRequest")
    @ResponsePayload
    public AddDistrictResponse addDistrict(@RequestPayload AddDistrictRequest request){
        AddDistrictResponse response = new AddDistrictResponse();
        response.setDistrict(districtRepositorySoap.addDistrict(request.getRegionId(), request.getCityId(), request.getDistrictNameKk(), request.getDistrictNameRu(), request.getDistrictNameEn()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateDistrictRequest")
    @ResponsePayload
    public UpdateDistrictResponse updateDistrict(@RequestPayload UpdateDistrictRequest request){
        UpdateDistrictResponse response = new UpdateDistrictResponse();
        response.setDistrict(districtRepositorySoap.updateDistrict(request.getId(), request.getDistrictNameKk(), request.getDistrictNameRu(), request.getDistrictNameEn()));
        return response;
    }

}
