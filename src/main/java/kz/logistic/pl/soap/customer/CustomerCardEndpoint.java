package kz.logistic.pl.soap.customer;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.customer.*;

import java.text.ParseException;

@Log
@Endpoint
public class CustomerCardEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/customer";

  private CustomerRepositorySoap customerRepositorySoap;

  @Autowired
  public CustomerCardEndpoint(CustomerRepositorySoap customerRepositorySoap) {
    this.customerRepositorySoap = customerRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerIdRequest")
  @ResponsePayload
  public GetCustomerIdResponse getCustomer(@RequestPayload GetCustomerIdRequest request) {
    GetCustomerIdResponse response = new GetCustomerIdResponse();
    response.setCustomer(customerRepositorySoap.findCustomerId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCustomerIdRequest")
  @ResponsePayload
  public DeleteCustomerIdResponse deleteCustomer(@RequestPayload DeleteCustomerIdRequest request) {
    DeleteCustomerIdResponse response = new DeleteCustomerIdResponse();

    response.setCustomer(this.customerRepositorySoap.deleteCustomerId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCustomerRequest")
  @ResponsePayload
  public AddCustomerResponse addCustomer(@RequestPayload AddCustomerRequest request) {
    AddCustomerResponse response = new AddCustomerResponse();
    response.setCustomer(customerRepositorySoap.addCustomer(request.getCustomerNameEn(), request.getCustomerNameRu(), request.getCustomerNameKk(),
      request.getAddInfo(), request.getEmail(),
      request.getIinOrBin(), request.getMobilePhone(), request.getPhoneNumber(),
      request.getAddressId()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCustomerRequest")
  @ResponsePayload
  public UpdateCustomerResponse updateCustomer(@RequestPayload UpdateCustomerRequest request) throws ParseException {
    UpdateCustomerResponse response = new UpdateCustomerResponse();
    response.setCustomer(customerRepositorySoap.updateCustomerCard(request.getCustomerId(), request.getCustomerNameEn(), request.getCustomerNameRu(),
      request.getCustomerNameKk(), request.getAddInfo(), request.getEmail(),
      request.getIinOrBin(), request.getMobilePhone(), request.getPhoneNumber(),
      request.getAddressId()));
    return response;
  }

}
