package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.RegionJson;
import kz.logistic.pl.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Список областей", description = "API для списка областей")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/region")
public class RegionController {

  private RegionService regionService;

  @Autowired(required = false)
  @Qualifier("defaultRegionService")
  public void setRegionService(RegionService regionService) {
    this.regionService = regionService;
  }

  @ApiOperation(value = "Показывает весь список областей")
  @GetMapping(value = "/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.regionService.showAllRegions());
  }

  @ApiOperation(value = "Добавляет область")
  @PostMapping(value = "/add")
  public ResponseEntity<?> add(
    @RequestParam(required = false) String regionNameKk,
    @RequestParam String regionNameRu,
    @RequestParam(required = false) String regionnameEn,
    @RequestParam Long countryId
  ) {
    return ResponseEntity.ok(
      this.regionService.addRegion(regionnameEn, regionNameKk, regionNameRu, countryId)
    );
  }

  @ApiOperation(value = "Добавляет область посредством JSON")
  @PostMapping(value = "/addJson")
  public ResponseEntity<?> addJson(@RequestBody RegionJson regionJson) {
    return ResponseEntity.ok(this.regionService.addRegionJson(regionJson));
  }

  @ApiOperation(value = "Показывает регион по ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long regionId) {
    return ResponseEntity.ok(this.regionService.showRegion(regionId));
  }

  @ApiOperation(value = "Обновляет регион")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long regionId,
    @RequestBody RegionJson regionJson
  ) {
    return ResponseEntity.ok(this.regionService.updateRegion(regionId, regionJson));
  }

  @ApiOperation(value = "Удаляет регион")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long regionId) {
    return ResponseEntity.ok(this.regionService.deleteRegion(regionId));
  }

}
