package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.CityJson;
import kz.logistic.pl.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Список городов"}, description = "API для городов")
@RestController
@RequestMapping(value = "/city")
public class CityController {

    private CityService cityService;

    @Qualifier("defaultCityService")
    @Autowired(required = false)
    private void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation(value = "Показывает все города")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.cityService.showAllCities());
    }

    @ApiOperation(value = "Добавляет город")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(
            @RequestParam(required = false) String cityNamekk,
            @RequestParam String cityNameRu,
            @RequestParam(required = false) String cityNameEn,
            @RequestParam Long regionId,
            @RequestParam Long countryId) {
        this.cityService.addCity(cityNamekk, cityNameRu, cityNameEn, regionId, countryId);
        return ResponseEntity.ok("Новый город добавлен");
    }

    @ApiOperation(value = "Добавляет город")
    @RequestMapping(value = "/addJson", method = RequestMethod.POST)
    public ResponseEntity<?> addJson(
            @RequestBody CityJson cityJson
    ) {
        this.cityService.addCityJson(cityJson);
        return ResponseEntity.ok("Новый город добавлен посредством JSON");
    }
}
