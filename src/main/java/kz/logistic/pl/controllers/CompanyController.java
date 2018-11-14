package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Компании"}, description = "API для компании")
@RestController
@RequestMapping("/company")
public class CompanyController {
  private CompanyService companyService;

  @Qualifier("defaultCompanyService")
  @Autowired(required = false)
  public void setCompanyService(CompanyService companyService) {
    this.companyService = companyService;
  }

  @ApiOperation(value = "Показывает всю компанию")
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.companyService.companies());
  }

  @ApiOperation(value = "Добавляет новую компанию")
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity<?> add(
          @RequestParam String companyNameRu,
          @RequestParam String companyNameKk,
          @RequestParam String companyNameEn,
          @RequestParam String companyPhoneNumber) {
    this.companyService.addCompany(companyNameRu, companyNameKk, companyNameEn, companyPhoneNumber);
    return ResponseEntity.ok(true);
  }
}
