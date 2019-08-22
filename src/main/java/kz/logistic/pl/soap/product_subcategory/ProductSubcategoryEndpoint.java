package kz.logistic.pl.soap.product_subcategory;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.product_subcategory.*;

@Log
@Endpoint
public class ProductSubcategoryEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/product_subcategory";

  private ProductSubcategoryRepositorySoap productSubCategoryRepository;

  @Autowired
  public ProductSubcategoryEndpoint(ProductSubcategoryRepositorySoap productSubCategoryRepository) {
    this.productSubCategoryRepository = productSubCategoryRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductSubcategoryIdRequest")
  @ResponsePayload
  public GetProductSubcategoryIdResponse getSubProduct(@RequestPayload GetProductSubcategoryIdRequest request) {
    GetProductSubcategoryIdResponse response = new GetProductSubcategoryIdResponse();
    response.setProductSubcategory(productSubCategoryRepository.findProductSubCategoryId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductSubcategoryIdRequest")
  @ResponsePayload
  public DeleteProductSubcategoryIdResponse deleteSubProduct(@RequestPayload DeleteProductSubcategoryIdRequest request) {
    DeleteProductSubcategoryIdResponse response = new DeleteProductSubcategoryIdResponse();

    response.setProductSubcategory(this.productSubCategoryRepository.deleteProductSubCategory(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductSubcategoryRequest")
  @ResponsePayload
  public AddProductSubcategoryResponse addSubProduct(@RequestPayload AddProductSubcategoryRequest request) {
    AddProductSubcategoryResponse response = new AddProductSubcategoryResponse();
    response.setProductSubcategory(productSubCategoryRepository.addProductSubCategory(request.getCategoryNameEn(),
      request.getCategoryNameKk(), request.getCategoryNameRu(), request.getProductCategoryId(), request.getSubCategoryAddInfo()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductSubcategoryRequest")
  @ResponsePayload
  public UpdateProductSubcategoryResponse updateSubProduct(@RequestPayload UpdateProductSubcategoryRequest request) {
    UpdateProductSubcategoryResponse response = new UpdateProductSubcategoryResponse();
    response.setProductSubcategory(productSubCategoryRepository.updateProductSubCategory(request.getProductSubcategoryId(),
      request.getCategoryNameEn(), request.getCategoryNameKk(), request.getCategoryNameRu(),
      request.getProductCategoryId(), request.getSubCategoryAddInfo()));
    return response;
  }

}
