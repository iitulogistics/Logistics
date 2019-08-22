package kz.logistic.pl.soap.seller_company;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.seller_company.*;

@Log
@Endpoint
public class SellerCompanyEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/seller_company";

  private SellerCompanyRepositorySoap sellerCompanyRepository;

  @Autowired
  public SellerCompanyEndpoint(SellerCompanyRepositorySoap sellerCompanyRepository) {
    this.sellerCompanyRepository = sellerCompanyRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSellerCompanyIdRequest")
  @ResponsePayload
  public GetSellerCompanyIdResponse getSeller(@RequestPayload GetSellerCompanyIdRequest request) {
    GetSellerCompanyIdResponse response = new GetSellerCompanyIdResponse();
    response.setSellerCompany(sellerCompanyRepository.findSellerCompanyId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteSellerCompanyIdRequest")
  @ResponsePayload
  public DeleteSellerCompanyIdResponse deleteSeller(@RequestPayload DeleteSellerCompanyIdRequest request) {
    DeleteSellerCompanyIdResponse response = new DeleteSellerCompanyIdResponse();

    response.setSellerCompany(this.sellerCompanyRepository.deleteSellerCompany(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSellerCompanyRequest")
  @ResponsePayload
  public AddSellerCompanyResponse addSeller(@RequestPayload AddSellerCompanyRequest request) {
    AddSellerCompanyResponse response = new AddSellerCompanyResponse();
    response.setSellerCompany(sellerCompanyRepository.addSellerCompany(request.getCompanyNameEn(),
      request.getCompanyNameRu(), request.getCompanyNameKk(), request.getBin(), request.getEmail(),
      request.getMobilePhone(), request.getPhone(), request.getSellerCategoryId()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSellerCompanyRequest")
  @ResponsePayload
  public UpdateSellerCompanyResponse updateSeller(@RequestPayload UpdateSellerCompanyRequest request) {
    UpdateSellerCompanyResponse response = new UpdateSellerCompanyResponse();
    response.setSellerCompany(sellerCompanyRepository.updateSellerCompany(request.getSellerCompanyId(),
      request.getCompanyNameEn(), request.getCompanyNameRu(), request.getCompanyNameKk(), request.getBin(),
      request.getEmail(), request.getMobilePhone(), request.getPhone(), request.getSellerCategoryId()));
    return response;
  }

}
