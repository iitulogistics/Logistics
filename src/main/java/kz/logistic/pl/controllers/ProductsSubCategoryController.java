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
@RequestMapping("/product/category/subcategory")
public class ProductsSubCategoryController {

  private ProductSubCategoryService productSubCategoryService;

  @Qualifier("defaultProductSubCategoryService")
  @Autowired(required = false)
  public void setProductSubCategoryService(ProductSubCategoryService productSubCategoryService) {
    this.productSubCategoryService = productSubCategoryService;
  }

  @ApiOperation(value = "Показывает всю подкатегорию продуктов")
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.productSubCategoryService.showAllProductSubCategory());
  }

  @ApiOperation(value = "Добавляет новую подкатегорию продуктов")
  @RequestMapping(value = "/add", method = RequestMethod.POST)
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
  @RequestMapping(value = "/addJson", method = RequestMethod.POST)
  public ResponseEntity<?> addJson(
    @RequestBody ProductSubCategoryJson productSubCategoryJson
  ) {
    this.productSubCategoryService.addProductSubCategoryJson(productSubCategoryJson);
    return ResponseEntity.ok("Новая подкатегория продуктов добавлена посредством JSON");
  }

}
