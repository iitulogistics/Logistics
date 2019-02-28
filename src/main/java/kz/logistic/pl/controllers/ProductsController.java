package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import kz.logistic.pl.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Продукты"}, description = "API для продуктов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product")
public class ProductsController {

  private ProductService productService;

  @Qualifier("defaultProductService")
  @Autowired(required = false)
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @ApiOperation(value = "Добавляет продукт")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam String productNameKk,
    @RequestParam String productNameRu,
    @RequestParam String productNameEn,
    @RequestParam String description,
    @RequestParam Long sellerCompanyId,
    @RequestParam Long productSubCategoryId,
    @RequestParam Long specialCharacteristicsId,
    @RequestParam Long productCategoryId) {
    this.productService.addProduct(
      productNameKk, productNameRu, productNameEn, description,
      sellerCompanyId, productSubCategoryId, specialCharacteristicsId, productCategoryId);
    return ResponseEntity.ok("Новый продукт добавлен");
  }

  @ApiOperation(value = "Добавляет продукты посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody ProductJson productJson) {
    this.productService.addProductJson(productJson);
    return ResponseEntity.ok("Новой продукт добавлен посредством JSON");
  }

  @ApiOperation(value = "Показывает список продуктов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.productService.showAllProducts());
  }

  @ApiOperation(value = "Показывает продукт")
  @GetMapping("{id}")
  public ResponseEntity<?> show(@PathVariable(value = "id") Long productId) {
    return ResponseEntity.ok(this.productService.showProduct(productId));
  }

  @ApiOperation(value = "Обновляет город")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long productId,
    @RequestBody ProductJson productJson
  ) {
    return ResponseEntity.ok(this.productService.updateProduct(productId, productJson));
  }

  @ApiOperation(value = "Удаляет город")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long productId) {
    return ResponseEntity.ok(this.productService.deleteProduct(productId));
  }

}