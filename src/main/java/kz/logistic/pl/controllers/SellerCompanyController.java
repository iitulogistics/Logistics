package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.services.SellerCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Компании продавцы"}, description = "API для компании продавцов")
@RestController
@RequestMapping("/seller/company")
public class SellerCompanyController {
  private SellerCompanyService sellerCompanyService;


  @Qualifier("defaultSellerCompanyService")
  @Autowired(required = false)
  public void setSellerCompanyService(SellerCompanyService sellerCompanyService) {
    this.sellerCompanyService = sellerCompanyService;
  }

  @ApiOperation(value = "Показывает всю компанию продавцов")
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.sellerCompanyService.showAllSellerCompanies());
  }

  @ApiOperation(value = "Добавляет новую компанию продавца")
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity<?> add(
          @RequestParam String sellerCompanyNameRu,
          @RequestParam(required = false) String sellerCompanyNameKk,
          @RequestParam(required = false) String sellerCompanyNameEn,
          @RequestParam String sellerCompanyPhone,
          @RequestParam String sellerCompanyMobilePhone,
          @RequestParam String sellerCompanyBin,
          @RequestParam String sellerCompanyEmail,
          @RequestParam String username,
          @RequestParam String password) {
    this.sellerCompanyService.addSellerCompany(
            sellerCompanyNameKk,
            sellerCompanyNameRu,
            sellerCompanyNameEn,
            sellerCompanyPhone,
            sellerCompanyMobilePhone,
            sellerCompanyBin,
            sellerCompanyEmail,
            username,
            password);
    return ResponseEntity.ok(true);
  }


}
