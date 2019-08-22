package kz.logistic.pl.soap.otp;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.otp.*;

@Log
@Endpoint
public class OtpEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/otp";

  private OtpRepositorySoap otpRepositorySoap;

  @Autowired
  public OtpEndpoint(OtpRepositorySoap otpRepositorySoap) {
    this.otpRepositorySoap = otpRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOptIdRequest")
  @ResponsePayload
  public GetOtpIdResponse getOtp(@RequestPayload GetOptIdRequest request) {
    GetOtpIdResponse response = new GetOtpIdResponse();
    response.setOtp(otpRepositorySoap.findOtpId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteOtpIdRequest")
  @ResponsePayload
  public DeleteOtpIdResponse deleteOtp(@RequestPayload DeleteOtpIdRequest request) {
    DeleteOtpIdResponse response = new DeleteOtpIdResponse();

    response.setOtp(this.otpRepositorySoap.deleteOtpId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addOtpRequest")
  @ResponsePayload
  public AddOtpResponse addOtp(@RequestPayload AddOtpRequest request) {
    AddOtpResponse response = new AddOtpResponse();
    response.setOtp(otpRepositorySoap.addOpt(request.getMobilePhone(), request.getOtp()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOtpRequest")
  @ResponsePayload
  public UpdateOtpResponse updateOtp(@RequestPayload UpdateOtpRequest request) {
    UpdateOtpResponse response = new UpdateOtpResponse();
    response.setOtp(otpRepositorySoap.updateOtp(request.getId(),request.getMobilePhone(), request.getOtp()));
    return response;
  }

}
