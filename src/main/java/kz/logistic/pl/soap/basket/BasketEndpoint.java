package kz.logistic.pl.soap.basket;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.basket.*;
import soap.logistic.city.*;

@Log
@Endpoint
public class BasketEndpoint {

    private static final String NAMESPACE_URI = "http://logistic.soap/basket";

    private BasketRepositorySoap basketRepositorySoap;

    @Autowired
    public BasketEndpoint(BasketRepositorySoap basketRepositorySoap) {
        this.basketRepositorySoap = basketRepositorySoap;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBasketIdRequest")
    @ResponsePayload
    public GetBasketIdResponse getBasket(@RequestPayload GetBasketIdRequest request) {
        GetBasketIdResponse response = new GetBasketIdResponse();
        response.setBasket(basketRepositorySoap.findBasketId(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBasketIdRequest")
    @ResponsePayload
    public DeleteBasketIdResponse deleteBasket(@RequestPayload DeleteBasketIdRequest request) {
        DeleteBasketIdResponse response = new DeleteBasketIdResponse();

        response.setBasket(this.basketRepositorySoap.deleteBasketId(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBasketRequest")
    @ResponsePayload
    public AddBasketResponse addBasket(@RequestPayload AddBasketRequest request) {
        AddBasketResponse response = new AddBasketResponse();
        response.setBasket(basketRepositorySoap.addBasket(request.getLoginId(), request.getProductId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBasketRequest")
    @ResponsePayload
    public UpdateBasketResponse updateBasket(@RequestPayload UpdateBasketRequest request) {
        UpdateBasketResponse response = new UpdateBasketResponse();
        response.setBasket(basketRepositorySoap.updateBasket(request.getBasketId(), request.getLoginId(), request.getProductId()));
        return response;
    }

}
