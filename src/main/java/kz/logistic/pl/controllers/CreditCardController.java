package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.CreditCardJson;
import kz.logistic.pl.services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Api(tags = {"Список кредитных карт клиентов не использовать"}, description = "API для списка кредитных карт")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/credit/card")
public class CreditCardController {
  private CreditCardService creditCardService;

  @Qualifier("defaultCreditCardService")
  @Autowired(required = false)
  public void setCreditCardService(CreditCardService creditCardService) {
    this.creditCardService = creditCardService;
  }

  @ApiOperation(value = "Показвает весь список кредитных карт")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.creditCardService.showAllCreditCard());
  }

  @ApiOperation(value = "Показвает кредитную карту по ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long creditCardId) {
    return ResponseEntity.ok(this.creditCardService.showCreditCard(creditCardId));
  }

  @ApiOperation(value = "Добавляет кредитную карту")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam Integer creditCardNumber,
    @RequestParam String holderName,
    @RequestParam Date expireDate) {
    return ResponseEntity.ok(this.creditCardService.addCreditCard(creditCardNumber, holderName, expireDate));
  }

  @ApiOperation(value = "Добавляет кредитную карту посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(@RequestBody CreditCardJson creditCardJson) {
    return ResponseEntity.ok(this.creditCardService.addCreditCardJson(creditCardJson));
  }

  @ApiOperation(value = "Обновляет кредитную карту")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(@PathVariable(value = "id") Long creditCardId, @RequestBody CreditCardJson creditCardJson) {
    return ResponseEntity.ok(this.creditCardService.updateCreditCard(creditCardId, creditCardJson));
  }

  @ApiOperation(value = "Удаляет кредитную карту")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long creditCardId) {
    return ResponseEntity.ok(this.creditCardService.deleteCreditCard(creditCardId));
  }
}
