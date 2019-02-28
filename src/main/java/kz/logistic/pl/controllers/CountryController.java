package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.CountryJson;
import kz.logistic.pl.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Список стран", description = "API для списка стран")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/country")
public class CountryController {

  private CountryService countryService;

  @Autowired(required = false)
  @Qualifier("defaultCountryService")
  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  @ApiOperation(value = "Показывает весь список стран")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.countryService.showAllCountries());
  }

  @ApiOperation(value = "Показывает страну по ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long countryId){
    return ResponseEntity.ok(this.countryService.showCountry(countryId));
  }

  @ApiOperation(value = "Добавляет страну")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam(required = false) String countryNameKk,
    @RequestParam String countryNameRu,
    @RequestParam(required = false) String countryNameEn
  ) {
    return ResponseEntity.ok(
      this.countryService.addCountry(countryNameKk, countryNameRu, countryNameEn)
    );
  }

  @ApiOperation(value = "Добавляет страну посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody CountryJson countryJson
  ) {
    return ResponseEntity.ok(this.countryService.addCountryJson(countryJson));
  }

  @ApiOperation(value = "Обновляет страну")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long countryId,
    @RequestBody CountryJson countryJson
  ) {
    return ResponseEntity.ok(this.countryService.updateCountry(countryId, countryJson));
  }

  @ApiOperation(value = "Удаляет страну")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long countryId) {
    return ResponseEntity.ok(this.countryService.deleteCountry(countryId));
  }
}
