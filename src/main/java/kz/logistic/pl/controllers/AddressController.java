package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.AddressJson;
import kz.logistic.pl.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Список адресов"}, description = "API для списка адресов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/address")
public class AddressController {

  private AddressService addressService;

  @Qualifier("defaultAddressService")
  @Autowired(required = false)
  public void setAddressService(AddressService addressService) {
    this.addressService = addressService;
  }

  @ApiOperation(value = "Показывает весь список адресов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.addressService.showAllAddresses());
  }

  @ApiOperation(value = "Показывает адрес по ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long addressId) {
    return ResponseEntity.ok(this.addressService.showAddress(addressId));
  }

  @ApiOperation(value = "Добавляет адрес")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam(required = false) String streetNameKk,
    @RequestParam(required = false) String streetNameRu,
    @RequestParam String streetNameEn,
    @RequestParam Long districtId,
    @RequestParam Long ihnLocalityId,
    @RequestParam String buildingNumber,
    @RequestParam String flatNumber,
    @RequestParam String zipCode,
    @RequestParam Integer addressAssign
  ) {
    return ResponseEntity.ok(this.addressService.addAddress(streetNameKk, streetNameRu,
      streetNameEn, ihnLocalityId, districtId, buildingNumber, flatNumber, zipCode, addressAssign));
  }

  @ApiOperation(value = "Добавляет адрес посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody AddressJson addressJson
  ) {
    return ResponseEntity.ok(this.addressService.addAddressJson(addressJson));
  }

  @ApiOperation(value = "Обновляет адрес")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long addressId,
    @RequestBody AddressJson addressJson
  ) {
    return ResponseEntity.ok(this.addressService.updateAddress(addressId, addressJson));
  }

  @ApiOperation(value = "Удаляет адрес")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(
    @PathVariable(value = "id") Long addressId
  ) {
    return ResponseEntity.ok(this.addressService.deleteAddress(addressId));
  }

}