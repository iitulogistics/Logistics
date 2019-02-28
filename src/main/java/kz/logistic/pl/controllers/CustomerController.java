package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.Customer;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.services.CustomerService;
import kz.logistic.pl.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Список клиентов"}, description = "API для списка клиентов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customer")
public class CustomerController {

  private CustomerService customerService;

  @Qualifier("defaultCustomerService")
  @Autowired(required = false)
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }


  @ApiOperation(value = "Показывает весь список клиентов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.customerService.showAllCustomers());
  }

  @ApiOperation(value = "Показывает клиента по ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long customerId) throws Exception {
    return ResponseEntity.ok(this.customerService.showCustomer(customerId));
  }

  @ApiOperation(value = "Добавляет клиента")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam String username,
    @RequestParam String password) {
    return ResponseEntity.ok(this.customerService.addCustomer(username, password));
  }

  @ApiOperation(value = "Добавляет клиента посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody CustomerJson customerJson
  ) {
    return ResponseEntity.ok(this.customerService.addCustomerJson(customerJson));
  }

  @ApiOperation(value = "Обновляет клиента")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long customerId,
    @RequestBody CustomerJson customerJson
  ) {
    return ResponseEntity.ok(this.customerService.updateCustomer(customerId, customerJson));
  }

  @ApiOperation(value = "Удаляет клиента")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long customerId) {
    return ResponseEntity.ok(this.customerService.deleteCustomer(customerId));
  }

}
