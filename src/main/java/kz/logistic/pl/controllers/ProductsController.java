package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.ProductJson;
import kz.logistic.pl.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Продукты"}, description = "API для продуктов")
@RestController
@RequestMapping("/product")
public class ProductsController {

    private ProductService productService;

    @Qualifier("defaultProductService")
    @Autowired(required = false)
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Добавляет продукт")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(
            @RequestParam String productNameKk,
            @RequestParam String productNameRu,
            @RequestParam String productNameEn,
            @RequestParam String description,
            @RequestParam Long sellerCompanyId) {
        this.productService.addProduct(productNameKk, productNameRu, productNameEn, description, sellerCompanyId);
        return ResponseEntity.ok("Новый продукт добавлен");
    }

    @ApiOperation(value = "Добавляет продукты посредством JSON")
    @RequestMapping(value = "/addJson", method = RequestMethod.POST)
    public ResponseEntity<?> addJson(
            @RequestBody ProductJson productJson) {
        this.productService.addProductJson(productJson);
        return ResponseEntity.ok("Новой продукт добавлен посредством JSON");
    }

    @ApiOperation(value = "Показывает список продуктов")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.productService.showAllProducts());
    }

}