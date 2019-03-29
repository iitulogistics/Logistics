package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.SpecialCharacteristicJson;
import kz.logistic.pl.services.SpecialCharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Список характеристик"}, description = "API для списка характеристик")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/specialCharacteristic")
public class SpecialCharacteristicController {

  private SpecialCharacteristicService specialCharacteristicService;

  @Autowired(required = false)
  @Qualifier("defaultSpecialCharacteristicService")
  public void setSpecialCharacteristicService(SpecialCharacteristicService specialCharacteristicService) {
    this.specialCharacteristicService = specialCharacteristicService;
  }

  @ApiOperation(value = "Показывает весь список характеристик")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.specialCharacteristicService.showAllCharacteristic());
  }

  @ApiOperation(value = "Показывает характеристику ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long characteristicId) {
    return ResponseEntity.ok(this.specialCharacteristicService.getCharacteristicById(characteristicId));
  }

  @ApiOperation(value = "Добавляет характеристику")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam String characteristicNameKk,
    @RequestParam String characteristicNameRu,
    @RequestParam String characteristicNameEn,
    @RequestParam String addInfo) {
    this.specialCharacteristicService.addSpecialCharacteristic(
      characteristicNameKk,
      characteristicNameRu,
      characteristicNameEn,
      addInfo
    );
    return ResponseEntity.ok("Новая характеристика добавлена");
  }

  @ApiOperation(value = "Добавляет характеристику посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody SpecialCharacteristicJson specialCharacteristicJson
  ) {
    this.specialCharacteristicService.addSpecialCharacteristicJson(specialCharacteristicJson);
    return ResponseEntity.ok("Новая характеристика добавлена посредством JSON");
  }

  @ApiOperation(value = "Обновляет характеристику")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long characteristicId,
    @RequestBody SpecialCharacteristicJson specialCharacteristicJson) {
    return ResponseEntity.ok(this.specialCharacteristicService.
      updateCharacteristic(characteristicId, specialCharacteristicJson));
  }


  @ApiOperation(value = "Удаляет характеристику")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long characteristicId) {
    return ResponseEntity.ok(this.specialCharacteristicService.deleteCharacteristic(characteristicId));
  }

}
