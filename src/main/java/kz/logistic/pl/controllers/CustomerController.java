package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Покупатели (Клиенты)"}, description = "API для покупателей(клиенты)")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    //@Qualifier("defaultCustomerService")
    @Autowired(required = false)
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Показывает всех покупателей")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.customerService.customers());
    }
}
