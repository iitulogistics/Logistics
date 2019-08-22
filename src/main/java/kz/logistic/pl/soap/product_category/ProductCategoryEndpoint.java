package kz.logistic.pl.soap.product_category;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.product_category.*;

import java.text.ParseException;

@Log
@Endpoint
public class ProductCategoryEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/product_category";

  private ProductCategoryRepositorySoap productCategoryRepository;

  @Autowired
  public ProductCategoryEndpoint(ProductCategoryRepositorySoap productCategoryRepository) {
    this.productCategoryRepository = productCategoryRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductCategoryIdRequest")
  @ResponsePayload
  public GetProductCategoryIdResponse getProduct(@RequestPayload GetProductCategoryIdRequest request) {
    GetProductCategoryIdResponse response = new GetProductCategoryIdResponse();
    response.setProductCategory(productCategoryRepository.findProductCategoryId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductCategoryIdRequest")
  @ResponsePayload
  public DeleteProductCategoryIdResponse deleteProduct(@RequestPayload DeleteProductCategoryIdRequest request) {
    DeleteProductCategoryIdResponse response = new DeleteProductCategoryIdResponse();

    response.setProductCategory(this.productCategoryRepository.deleteProductCategory(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductCategoryRequest")
  @ResponsePayload
  public AddProductCategoryResponse addProduct(@RequestPayload AddProductCategoryRequest request)  {
    AddProductCategoryResponse response = new AddProductCategoryResponse();
    response.setProductCategory(productCategoryRepository.addProductCategory(request.getAddInfo(), request.getCategoryNameEn(),
      request.getCategoryNameRu(), request.getCategoryNameKk()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductCategoryRequest")
  @ResponsePayload
  public UpdateProductCategoryResponse updateProduct(@RequestPayload UpdateProductCategoryRequest request) {
    UpdateProductCategoryResponse response = new UpdateProductCategoryResponse();
    response.setProductCategory(productCategoryRepository.updateProductCategory(request.getProductCategoryId(),
      request.getAddInfo(), request.getCategoryNameEn(), request.getCategoryNameRu(), request.getCategoryNameKk()));
    return response;
  }

}
