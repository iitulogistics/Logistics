package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.SellerCompanyJson;
import kz.logistic.pl.services.SellerCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Компании продавцы"}, description = "API для компании продавцов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seller/company")
public class SellerCompanyController {
  private SellerCompanyService sellerCompanyService;


  @Qualifier("defaultSellerCompanyService")
  @Autowired(required = false)
  public void setSellerCompanyService(SellerCompanyService sellerCompanyService) {
    this.sellerCompanyService = sellerCompanyService;
  }

  @ApiOperation(value = "Показывает всю компанию продавцов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.sellerCompanyService.showAllSellerCompanies());
  }

  @ApiOperation(value = "Показывает компанию продавцов ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long sellerCompanyId) {
    return ResponseEntity.ok(this.sellerCompanyService.showSellerCompany(sellerCompanyId));
  }

  @ApiOperation(value = "Добавляет новую компанию продавца")
  @PostMapping("/add")
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
    return ResponseEntity.ok("Новая компания продовца добавлена");
  }


  @ApiOperation(value = "Добавляет новую компанию продавца посредством JSON")
  @RequestMapping(value = "/addJson", method = RequestMethod.POST)
  public ResponseEntity<?> addJson(
    @RequestBody SellerCompanyJson sellerCompanyJson
  ) {
    this.sellerCompanyService.addSellerCompanyJson(sellerCompanyJson);
    return ResponseEntity.ok("Новая компания продовца добавлена посредством JSON");
  }

  @ApiOperation(value = "Обновляет компанию продавца")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long sellerCompanyid,
    @RequestBody SellerCompanyJson sellerCompanyJson) {
    return ResponseEntity.ok(this.sellerCompanyService.updateSellerCompany(sellerCompanyid, sellerCompanyJson));
  }


  @ApiOperation(value = "Удаляет компанию продавца")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long sellerCompanyId) {
    return ResponseEntity.ok(this.sellerCompanyService.deleteSellerCompany(sellerCompanyId));
  }

}
