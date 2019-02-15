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
@RequestMapping(value = "/country")
public class CountryController {

    private CountryService countryService;

    @Autowired(required = false)
    @Qualifier("defaultCountryService")
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Показывает весь список стран")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.countryService.showAllCountries());
    }

    @ApiOperation(value = "Добавляет страну")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(
            @RequestParam(required = false) String countryNameKk,
            @RequestParam String countryNameRu,
            @RequestParam(required = false) String countryNameEn
    ) {
        return ResponseEntity.ok(this.countryService.addCountry(countryNameKk, countryNameRu, countryNameEn));
    }

    @ApiOperation(value = "Добавляет страну посредством JSON")
    @RequestMapping(value = "/addJson", method = RequestMethod.POST)
    public ResponseEntity<?> addJson(
            @RequestParam CountryJson countryJson
    ) {
        return ResponseEntity.ok(this.countryService.addCountryJson(countryJson));
    }
}
