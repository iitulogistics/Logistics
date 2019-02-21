package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.services.GosZakupBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(tags = {"Поиск по БИН или ИИН через goszakup.egov.kz"}, description = "API по информации по БИН или ИИН")
@RestController
@RequestMapping(value = "/goszakup")
public class BinSearchByGosZakupController {

  private GosZakupBinService gosZakupBinService;

  @Qualifier("defaultGosZakupService")
  @Autowired(required = false)
  public void setGosZakupBinService(GosZakupBinService gosZakupBinService) {
    this.gosZakupBinService = gosZakupBinService;
  }

  @ApiOperation(value = "Поиск по БИН или ИИН")
  @GetMapping("{bin}")
  public ResponseEntity<?> showInformation(@PathVariable(value = "bin") String bin) throws IOException {
    return ResponseEntity.ok(this.gosZakupBinService.showCompanyInformation(bin));
  }
}
