package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.RegionJson;
import kz.logistic.pl.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Список областей", description = "API для списка областей")
@RestController
@RequestMapping(value = "/region")
public class RegionController {

  private RegionService regionService;

  @Autowired(required = false)
  @Qualifier("defaultRegionService")
  public void setRegionService(RegionService regionService) {
    this.regionService = regionService;
  }

  @ApiOperation(value = "Показывает весь список областей")
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.regionService.showAllRegions());
  }

  @ApiOperation(value = "Добавляет область")
  @RequestMapping(value = "/add", method = RequestMethod.POST)
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
  @RequestMapping(value = "/addJson", method = RequestMethod.POST)
  public ResponseEntity<?> addJson(@RequestParam RegionJson regionJson) {
    return ResponseEntity.ok(this.regionService.addRegionJson(regionJson));
  }

}
