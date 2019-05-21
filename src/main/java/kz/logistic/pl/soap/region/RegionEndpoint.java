package kz.logistic.pl.soap.region;

import lombok.extern.java.Log;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.region.*;

@Log
@Endpoint
public class RegionEndpoint {



        private static final String NAMESPACE_URI = "http://logistic.soap/region";

        private RegionRepositorySoap regionRepositorySoap;



    public RegionEndpoint(RegionRepositorySoap regionRepositorySoap) {
        this.regionRepositorySoap = regionRepositorySoap;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegionIdRequest")
        @ResponsePayload
        public GetRegionIdResponse getRegion(@RequestPayload GetRegionIdRequest request) {
            GetRegionIdResponse response = new GetRegionIdResponse();
            response.setRegion(regionRepositorySoap.findRegionId(request.getId()));
            return response;
        }

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteRegionIdRequest")
        @ResponsePayload
        public DeleteRegionIdResponse deleteRegionIdResponse(@RequestPayload DeleteRegionIdRequest request) {
            DeleteRegionIdResponse response = new DeleteRegionIdResponse();

            response.setRegion(this.regionRepositorySoap.deleteRegion(request.getId()));
            return response;
        }

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRegionRequest")
        @ResponsePayload
        public AddRegionResponse addRegion(@RequestPayload AddRegionRequest request) {
            AddRegionResponse response = new AddRegionResponse();
            response.setRegion(regionRepositorySoap.addRegion(request.getRegionNameKk(), request.getRegionNameRu(), request.getRegionNameEn(), request.getCountryId()));

            return response;
        }

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateRegionRequest")
        @ResponsePayload
        public UpdateRegionResponse updateRegion(@RequestPayload UpdateRegionRequest request) {
            UpdateRegionResponse response = new UpdateRegionResponse();
            response.setRegion(regionRepositorySoap.updateRegion(
                request.getId(), request.getRegionNameKk(), request.getRegionNameRu(), request.getRegionNameEn(), request.getCountryId()));
            return response;
        }



}
