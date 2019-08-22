package kz.logistic.pl.soap.seller_category;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.seller_category.*;

@Log
@Endpoint
public class SellerCategoryEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/seller_category";

  private SellerCategoryRepositorySoap sellerCategoryRepository;

  @Autowired
  public SellerCategoryEndpoint(SellerCategoryRepositorySoap sellerCategoryRepository) {
    this.sellerCategoryRepository = sellerCategoryRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSellerCategoryIdRequest")
  @ResponsePayload
  public GetSellerCategoryIdResponse getSeller(@RequestPayload GetSellerCategoryIdRequest request) {
    GetSellerCategoryIdResponse response = new GetSellerCategoryIdResponse();
    response.setSellerCategory(sellerCategoryRepository.findSellerCategoryId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteSellerCategoryIdRequest")
  @ResponsePayload
  public DeleteSellerCategoryIdResponse deleteSeller(@RequestPayload DeleteSellerCategoryIdRequest request) {
    DeleteSellerCategoryIdResponse response = new DeleteSellerCategoryIdResponse();

    response.setSellerCategory(this.sellerCategoryRepository.deleteSellerCategory(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addSellerCategoryRequest")
  @ResponsePayload
  public AddSellerCategoryResponse addSeller(@RequestPayload AddSellerCategoryRequest request) {
    AddSellerCategoryResponse response = new AddSellerCategoryResponse();
    response.setSellerCategory(sellerCategoryRepository.addSellerCategory(request.getAddInfo(), request.getCategoryNameEn(),
      request.getCategoryNameRu(), request.getCategoryNameKk()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSellerCategoryRequest")
  @ResponsePayload
  public UpdateSellerCategoryResponse updateSeller(@RequestPayload UpdateSellerCategoryRequest request) {
    UpdateSellerCategoryResponse response = new UpdateSellerCategoryResponse();
    response.setSellerCategory(sellerCategoryRepository.updateSellerCategory(request.getSellerCategoryId(),
      request.getAddInfo(), request.getCategoryNameEn(), request.getCategoryNameRu(), request.getCategoryNameKk()));
    return response;
  }

}
