package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.impl.DefaultShipper;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.models.pojos.json.ShipperJson;
import kz.logistic.pl.services.CustomerService;
import kz.logistic.pl.services.OtpService;
import kz.logistic.pl.services.ProductCategoryService;
import kz.logistic.pl.services.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = {"Список доставщиков"}, description = "API для списка доставщиков")
@RestController
@RequestMapping("/shipper")
public class ShipperController {

  private ShipperService shipperService;
  private OtpService otpService;

  @Qualifier("defaultShipperService")
  @Autowired(required = false)
  public void setShipperService(ShipperService shipperService) {
    this.shipperService = shipperService;
  }

  @Qualifier("defaultTokenService")
  @Autowired(required = false)
  public void setOtpService(OtpService otpService) {
    this.otpService = otpService;
  }

  @ApiOperation(value = "Показывает весь список доставщиков")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.shipperService.showAllShippers());
  }

  @ApiOperation(value = "Показывает доставщика ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long shipperId) {
    return ResponseEntity.ok(this.shipperService.showShipper(shipperId));
  }

  @ApiOperation(value = "Проверка существующего логина (мобильный номер)")
  @PostMapping("/exists")
  public ResponseEntity<?> exists(@RequestParam String mobilePhone) {
    return ResponseEntity.ok(this.shipperService.exists(mobilePhone));
  }

  @ApiOperation(value = "Генерация OTP для мобильного номера")
  @PostMapping("/generateOtp")
  public ResponseEntity<?> generateOtp(@RequestParam String mobilePhone) throws IOException {
    return ResponseEntity.ok(this.otpService.generateOtp(mobilePhone));
  }

  @ApiOperation(value = "Валидация OTP и номер мобильного телефона")
  @PostMapping("/validateOtp")
  public ResponseEntity<?> validateOtp(@RequestParam String mobilePhone, @RequestParam String otp) {
    return ResponseEntity.ok(this.otpService.validateOtp(mobilePhone, otp));
  }

  @ApiOperation(value = "Добавляет доставщика")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam String username,
    @RequestParam String password,
    @RequestParam String shipperNameKk,
    @RequestParam String shipperNameRu,
    @RequestParam String shipperNameEn,
    @RequestParam String phoneNumber,
    @RequestParam String bin,
    @RequestParam String email,
    @RequestParam String address
  ) {
    return ResponseEntity.ok(this.shipperService.addShipper(username, password, shipperNameKk,
      shipperNameRu, shipperNameEn, phoneNumber, bin, email, address));
  }

  @ApiOperation(value = "Добавляет доставщика посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody ShipperJson shipperJson
  ) {
    return ResponseEntity.ok(this.shipperService.addShipperJson(shipperJson));
  }

  @ApiOperation(value = "Обновляет доставщика")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long shipperId,
    @RequestBody ShipperJson shipperJson) {
    return ResponseEntity.ok(this.shipperService.updateShipper(shipperId, shipperJson));
  }

  @ApiOperation(value = "Удаляет доставщика")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long shipperId) {
    return ResponseEntity.ok(this.shipperService.deleteShipper(shipperId));
  }

}