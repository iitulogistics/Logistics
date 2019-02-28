package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.DistrictJson;
import kz.logistic.pl.services.DistrictService;
import org.hibernate.criterion.Disjunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Список районов", description = "API для списка районов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/district")
public class DistrictController {

  private DistrictService districtService;

  @Autowired(required = false)
  @Qualifier("defaultDistrictService")
  public void setDistrictService(DistrictService districtService) {
    this.districtService = districtService;
  }

  @ApiOperation(value = "Показывает весь список районов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.districtService.showAllDistricts());
  }

  @ApiOperation(value = "Показывает район по ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long districtId) {
    return ResponseEntity.ok(this.districtService.showDistrict(districtId));
  }

  @ApiOperation(value = "Добавляет район")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam(required = false) String districtNameKk,
    @RequestParam String cityNameRu,
    @RequestParam(required = false) String districtNameEn,
    @RequestParam Long regionId,
    @RequestParam Long cityId) {
    return ResponseEntity.ok(this.districtService.addDistrict(
      districtNameKk, districtNameEn, cityNameRu, regionId, cityId));
  }

  @ApiOperation(value = "Добавляет район посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody DistrictJson districtJson
  ) {
    return ResponseEntity.ok(this.districtService.addDistrictJson(districtJson));
  }

  @ApiOperation(value = "Обновляет район")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long districtId,
    @RequestBody DistrictJson districtJson
  ) {
    return ResponseEntity.ok(this.districtService.updateDistrict(districtId, districtJson));
  }

  @ApiOperation(value = "Удаляет район")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long districtId) {
    return ResponseEntity.ok(this.districtService.deleteDistrict(districtId));
  }

}
