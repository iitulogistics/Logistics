package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.SellerCategoryJson;
import kz.logistic.pl.services.SellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Категория компаний продавцов"}, description = "API для категории продавцов")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seller/company/category")
public class SellerCategoryController {

  private SellerCategoryService sellerCategorySevice;

  @Qualifier("defaultSelleCategoryService")
  @Autowired(required = false)
  public void setSellerCategorySevice(SellerCategoryService sellerCategorySevice) {
    this.sellerCategorySevice = sellerCategorySevice;
  }

  @ApiOperation(value = "Показвать весь категорию продавцов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.sellerCategorySevice.showAllSellerCategories());
  }

  @ApiOperation(value = "Показывает категорию продавцов ID")
  @GetMapping("{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long sellerCategoryId) {
    return ResponseEntity.ok(this.sellerCategorySevice.showSellerCategory(sellerCategoryId));
  }

  @ApiOperation(value = "Добавляет новую категорию продавца")
  @PostMapping("add")
  public ResponseEntity<?> add(
    @RequestParam String sellerCategoryNameKk,
    @RequestParam String sellerCategoryNameRu,
    @RequestParam String sellerCategoryNameEn,
    @RequestParam String addInfo
  ) {
    return ResponseEntity.ok(this.sellerCategorySevice.addSellerCategory(
      sellerCategoryNameKk, sellerCategoryNameRu, sellerCategoryNameEn, addInfo
    ));
  }

  @ApiOperation(value = "Добавляет новую категорию продавца посредством JSON")
  @PostMapping("addJson")
  public ResponseEntity<?> addJson(
    @RequestBody SellerCategoryJson sellerCategoryJson
  ) {
    return ResponseEntity.ok(this.sellerCategorySevice.addSellerCategoryJson(sellerCategoryJson));
  }

  @ApiOperation(value = "Обновляет категорию продавца")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(@PathVariable(value = "id") Long sellerCategoryId,
                                  @RequestBody SellerCategoryJson sellerCategoryJson) {
    return ResponseEntity.ok(this.sellerCategorySevice.updateSellerCategory(sellerCategoryId, sellerCategoryJson));
  }

  @ApiOperation(value = "Удаляет категорию продавца")
  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable(value = "id") Long sellerCategoryId) {
    return ResponseEntity.ok(this.sellerCategorySevice.deleteSellerCategory(sellerCategoryId));
  }

}
