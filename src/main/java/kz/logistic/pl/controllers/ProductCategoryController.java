package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.CustomerJson;
import kz.logistic.pl.models.pojos.json.ProductCategoryJson;
import kz.logistic.pl.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Категория продуктов"}, description = "API для категории продуктов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product/category")
public class ProductCategoryController {

  private ProductCategoryService productCategoryService;

  @Qualifier("defaultProductCategoryService")
  @Autowired(required = false)
  public void setProductCategoryService(ProductCategoryService productCategoryService) {
    this.productCategoryService = productCategoryService;
  }

  @ApiOperation(value = "Показывает всю категорию продуктов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.productCategoryService.showAllProduct());
  }

  @ApiOperation(value = "Показывает категорию продуктов ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long productCategoryId) {
    return  ResponseEntity.ok(this.productCategoryService.showProductCategory(productCategoryId));
  }

  @ApiOperation(value = "Добавляет категорию продуктов")
  @PostMapping("/add")
  public ResponseEntity<?> add(
      @RequestParam(required = false) String categoryNameKk,
      @RequestParam String categoryNameRu,
      @RequestParam(required = false) String categoryNameEn,
      @RequestParam(required = false) String addInfo) {

    return ResponseEntity.ok(
      this.productCategoryService.addCategory(
        categoryNameKk, categoryNameRu, categoryNameEn, addInfo));
  }

  @ApiOperation(value = "Добавляет категорию продуктов посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
      @RequestBody ProductCategoryJson productCategoryJson
  ) {
    return ResponseEntity.ok(this.productCategoryService.addCategoryJson(productCategoryJson));
  }

  @ApiOperation(value = "Обновляет категорию продуктов")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(@PathVariable(value = "id") Long productCategoryId,
                                  @RequestBody ProductCategoryJson productCategoryJson) {
    return ResponseEntity.ok(
      this.productCategoryService.updateProductCategory(productCategoryId,productCategoryJson));
  }

  @ApiOperation(value = "Удаляет категогию продуктов")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long productCategoryId) {
    return ResponseEntity.ok(this.productCategoryService.deleteProductCategory(productCategoryId));
  }

}
