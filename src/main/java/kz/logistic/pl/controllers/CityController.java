package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.CityJson;
import kz.logistic.pl.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"Список городов"}, description = "API для списка городов")
@RestController
@RequestMapping(value = "/city")
public class CityController {

  private CityService cityService;

  @Qualifier("defaultCityService")
  @Autowired(required = false)
  private void setCityService(CityService cityService) {
    this.cityService = cityService;
  }

  @ApiOperation(value = "Показывает весь список городов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.cityService.showAllCities());
  }

  @ApiOperation(value = "Показывает город по ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long cityId) {
    return ResponseEntity.ok(this.cityService.showCity(cityId));
  }

  @ApiOperation(value = "Добавляет город")
  @PostMapping("/add")
  public ResponseEntity<?> add(
      @RequestParam(required = false) String cityNamekk,
      @RequestParam String cityNameRu,
      @RequestParam(required = false) String cityNameEn,
      @RequestParam Long regionId,
      @RequestParam Long countryId) {
    return ResponseEntity.ok(
      this.cityService.addCity(cityNamekk, cityNameRu, cityNameEn, regionId, countryId)
    );
  }

  @ApiOperation(value = "Добавляет город посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
      @RequestBody CityJson cityJson
  ) {
    return ResponseEntity.ok(this.cityService.addCityJson(cityJson));
  }

  @ApiOperation(value = "Обновляет город")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
      @PathVariable(value = "id") Long cityId,
      @RequestBody CityJson cityJson
  ) {
    return ResponseEntity.ok(this.cityService.updateCity(cityId, cityJson));
  }

  @ApiOperation(value = "Удаляет город")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long cityId) {
    return ResponseEntity.ok(this.cityService.deleteCity(cityId));
  }

}
