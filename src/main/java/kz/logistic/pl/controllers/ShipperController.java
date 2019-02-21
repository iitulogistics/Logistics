package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.impl.DefaultShipper;
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

  @ApiOperation(value = "Показывает доставщика ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long shipperId){
    return ResponseEntity.ok(this.shipperService.showShipper(shipperId));
  }

  @ApiOperation(value = "Добавляет доставщика")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam String username,
    @RequestParam String password) {
    this.shipperService.addShipper(username, password);
    return ResponseEntity.ok("Новый доставщик добавлен");
  }

  @ApiOperation(value = "Добавляет доставщика посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody ShipperJson shipperJson
  ) {
    this.shipperService.addShipperJson(shipperJson);
    return ResponseEntity.ok("Новый доставщик добавлен посредством JSON");
  }

  @ApiOperation(value = "Обновляет доставщика")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id")Long shipperId,
    @RequestBody ShipperJson shipperJson){
    return ResponseEntity.ok(this.shipperService.updateShipper(shipperId, shipperJson));
  }


  @ApiOperation(value = "Удаляет доставщика")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id")Long shipperId){
    return ResponseEntity.ok(this.shipperService.deleteShipper(shipperId));
  }

}