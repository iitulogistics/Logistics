package kz.logistic.pl.soap.credit_card;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.logistic.credit_card.*;

import java.text.ParseException;

@Log
@Endpoint
public class CreditCardEndpoint {

    private static final String NAMESPACE_URI = "http://logistic.soap/credit_card";

    private CreditCardRepositorySoap creditCardRepositorySoap;

    @Autowired
    public CreditCardEndpoint(CreditCardRepositorySoap creditCardRepositorySoap) {
        this.creditCardRepositorySoap = creditCardRepositorySoap;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCreditCardIdRequest")
    @ResponsePayload
    public GetCreditCardIdResponse getCreditCard(@RequestPayload GetCreditCardIdRequest request) {
        GetCreditCardIdResponse response = new GetCreditCardIdResponse();
        response.setCreditCard(creditCardRepositorySoap.findCreditCardId(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCreditCardIdRequest")
    @ResponsePayload
    public DeleteCreditCardIdResponse deleteCreditCard(@RequestPayload DeleteCreditCardIdRequest request) {
        DeleteCreditCardIdResponse response = new DeleteCreditCardIdResponse();

        response.setCreditCard(this.creditCardRepositorySoap.deleteCreditCardId(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCreditCardRequest")
    @ResponsePayload
    public AddCreditCardResponse addCreditCard(@RequestPayload AddCreditCardRequest request) {
        AddCreditCardResponse response = new AddCreditCardResponse();
        response.setCreditCard(creditCardRepositorySoap.addCreditCard(request.getCreditCardNumber(), request.getHolderName(), request.getExpireDate()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCreditCardRequest")
    @ResponsePayload
    public UpdateCreditCardResponse updateCreditCard(@RequestPayload UpdateCreditCardRequest request) throws ParseException {
        UpdateCreditCardResponse response = new UpdateCreditCardResponse();
        response.setCreditCard(creditCardRepositorySoap.updateCreditCard(request.getCcId(), request.getCreditCardNumber(), request.getHolderName(), request.getExpireDate()));
        return response;
    }

}
