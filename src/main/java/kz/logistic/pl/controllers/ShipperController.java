package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.models.pojos.json.ShipperJson;
import kz.logistic.pl.services.CustomerService;
import kz.logistic.pl.services.ProductCategoryService;
import kz.logistic.pl.services.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Список доставщиков"}, description = "API для списка доставщиков")
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    private ShipperService shipperService;

    @Qualifier("defaultShipperService")
    @Autowired(required = false)
    public void setShipperService(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @ApiOperation(value = "Показывает весь список доставщиков")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.shipperService.showAllShippers());
    }

    @ApiOperation(value = "Добавляет доставщика")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(
            @RequestParam String username,
            @RequestParam String password) {
        this.shipperService.addShipper(username, password);
        return ResponseEntity.ok("Новый доставщик добавлен");
    }

    @ApiOperation(value = "Добавляет доставщика посредством JSON")
    @RequestMapping(value = "/addJson", method = RequestMethod.POST)
    public ResponseEntity<?> addJson(
            @RequestBody ShipperJson shipperJson
    ) {
        this.shipperService.addShipperJson(shipperJson);
        return ResponseEntity.ok("Новый доставщик добавлен посредством JSON");
    }

}