package kz.logistic.pl.soap.product;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.product.*;

import java.text.ParseException;

@Log
@Endpoint
public class ProductEndpoint {

  private static final String NAMESPACE_URI = "http://logistic.soap/product";

  private ProductRepositorySoap productRepositorySoap;

  @Autowired
  public ProductEndpoint(ProductRepositorySoap productRepositorySoap) {
    this.productRepositorySoap = productRepositorySoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductIdRequest")
  @ResponsePayload
  public GetProductIdResponse getProduct(@RequestPayload GetProductIdRequest request) {
    GetProductIdResponse response = new GetProductIdResponse();
    response.setProduct(productRepositorySoap.findProductId(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductIdRequest")
  @ResponsePayload
  public DeleteProductIdResponse deleteProduct(@RequestPayload DeleteProductIdRequest request) {
    DeleteProductIdResponse response = new DeleteProductIdResponse();

    response.setProduct(this.productRepositorySoap.deleteProduct(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
  @ResponsePayload
  public AddProductResponse addProduct(@RequestPayload AddProductRequest request) throws ParseException {
    AddProductResponse response = new AddProductResponse();
    response.setProduct(productRepositorySoap.addProduct(request.getManufacturer(), request.getPrice(), request.getProductCategoryId(),
      request.getProductDescription(), request.getProductNameEn(), request.getProductNameKk(), request.getProductNameRu(),
      request.getProductsImg(), request.getProductSubcategoryId(), request.getSellerCompanyId(), request.getSerialNumber(),
      request.getSize(), request.getSpecialCharacteristicId(), request.getUniqueIdNumber(), request.getWeight()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
  @ResponsePayload
  public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) throws ParseException {
    UpdateProductResponse response = new UpdateProductResponse();
    response.setProduct(productRepositorySoap.updateProduct(request.getProductId(), request.getManufacturer(), request.getPrice(), request.getProductCategoryId(),
      request.getProductDescription(), request.getProductNameEn(), request.getProductNameKk(), request.getProductNameRu(),
      request.getProductsImg(), request.getProductSubcategoryId(), request.getSellerCompanyId(), request.getSerialNumber(),
      request.getSize(), request.getSpecialCharacteristicId(), request.getUniqueIdNumber(), request.getWeight()));
    return response;
  }

}
