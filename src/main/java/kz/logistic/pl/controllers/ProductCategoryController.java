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
@RequestMapping("/product/category/category")
public class ProductCategoryController {

    private ProductCategoryService productCategoryService;

    @Qualifier("defaultProductCategoryService")
    @Autowired(required = false)
    public void setProductCategoryService(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @ApiOperation(value = "Показывает всю категорию продуктов")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.productCategoryService.showAllProduct());
    }

    @ApiOperation(value = "Добавляет категорию продуктов")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(
            @RequestParam(required = false) String categoryNameKk,
            @RequestParam String categoryNameRu,
            @RequestParam(required = false) String categoryNameEn,
            @RequestParam(required = false) String addInfo) {
        this.productCategoryService.addCategory(categoryNameKk, categoryNameRu, categoryNameEn, addInfo);
        return ResponseEntity.ok("Новая категория продуктов добавлена");
    }

    @ApiOperation(value = "Добавляет категорию продуктов посредством JSON")
    @RequestMapping(value = "/addJson", method = RequestMethod.POST)
    public ResponseEntity<?> addJson(
            @RequestBody ProductCategoryJson productCategoryJson
    ) {
        this.productCategoryService.addCategoryJson(productCategoryJson);
        return ResponseEntity.ok("Новая категория продуктов добавлена посредством JSON");
    }

}
