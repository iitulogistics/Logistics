package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.ProductSubCategoryJson;
import kz.logistic.pl.services.ProductSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Подкатегория продуктов"}, description = "API для подкатегории  продуктов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product/category/subcategory")
public class ProductsSubCategoryController {

  private ProductSubCategoryService productSubCategoryService;

  @Qualifier("defaultProductSubCategoryService")
  @Autowired(required = false)
  public void setProductSubCategoryService(ProductSubCategoryService productSubCategoryService) {
    this.productSubCategoryService = productSubCategoryService;
  }

  @ApiOperation(value = "Показывает всю подкатегорию продуктов")
  @GetMapping(value = "/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.productSubCategoryService.showAllProductSubCategory());
  }

  @ApiOperation(value = "Добавляет новую подкатегорию продуктов")
  @PostMapping(value = "/add")
  public ResponseEntity<?> add(
    @RequestParam(required = false) String subCategoryNameKk,
    @RequestParam String subCategoryNameRu,
    @RequestParam(required = false) String subCategoryNameEn,
    @RequestParam Long productCategoryId,
    @RequestParam String subCategoryAddInfo) {
    this.productSubCategoryService.addProductSubCategory(
      subCategoryNameKk, subCategoryNameRu, subCategoryNameEn,
      productCategoryId, subCategoryAddInfo
    );
    return ResponseEntity.ok("Новая подкатегория продуктов добавлена");
  }

  @ApiOperation(value = "Добавляет подкатегорию продуктов посредством JSON")
  @PostMapping(value = "/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody ProductSubCategoryJson productSubCategoryJson
  ) {
    this.productSubCategoryService.addProductSubCategoryJson(productSubCategoryJson);
    return ResponseEntity.ok("Новая подкатегория продуктов добавлена посредством JSON");
  }

  @ApiOperation(value = "Обновляет подкатегорию продуктов")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(@PathVariable(value = "id") Long productSubCategoryId,
                                  @RequestBody ProductSubCategoryJson productSubCategoryJson) {
    return ResponseEntity.ok(
      this.productSubCategoryService.updateProductSubCategory(productSubCategoryId, productSubCategoryJson));
  }

  @ApiOperation(value = "Удаляет подкатегогию продуктов")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long productSubCategoryId) {
    return ResponseEntity.ok(this.productSubCategoryService.deleteProductSubCategory(productSubCategoryId));
  }

  @ApiOperation(value = "Показывает подкатегорию продуктов ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long productSubCategoryId) {
    return  ResponseEntity.ok(this.productSubCategoryService.showProductSubCategory(productSubCategoryId));
  }

}
