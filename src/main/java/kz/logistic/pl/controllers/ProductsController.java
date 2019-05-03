package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import kz.logistic.pl.services.ProductService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    @PostMapping("/add")
    public ResponseEntity<?> add(
        @RequestParam String productNameKk,
        @RequestParam String productNameRu,
        @RequestParam String productNameEn,
        @RequestParam Long productCategoryId,
        @RequestParam Long productSubcategoryId,
        @RequestParam String uniqueIdNumber,
        @RequestParam String serialNumber,
        @RequestParam String manufacturer,
        @RequestParam String size,
        @RequestParam Integer weight,
        @RequestParam Integer price,
        @RequestParam String productDescription,
        @RequestParam Long sellerCompanyId,
        @RequestParam Long specialCharacteristicsId,
        @RequestParam(value = "jwt_token", required = false) String jwtToken) {
        this.productService.addProduct(
            productNameKk, productNameRu, productNameEn, productCategoryId, productSubcategoryId,
            uniqueIdNumber, serialNumber, manufacturer, size, weight, price, productDescription,
            sellerCompanyId, specialCharacteristicsId);
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

    @ApiOperation(value = "Обновляет продукт")
    @PatchMapping("{id}")
    public ResponseEntity<?> update(
        @PathVariable(value = "id") Long productId,
        @RequestBody ProductJson productJson
    ) {
        return ResponseEntity.ok(this.productService.updateProduct(productId, productJson));
    }

    @ApiOperation(value = "Удаляет продукт")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long productId) {
        return ResponseEntity.ok(this.productService.deleteProduct(productId));
    }

    @ApiOperation("Добавляет фото")
    @PostMapping("/addPhoto")
    public ResponseEntity<?> addPhoto(Long id, MultipartFile file) {
        return ResponseEntity.ok(this.productService.addPhoto(id, file));
    }

    @ApiOperation("Возвращает фотографию по названию на сервере")
    @GetMapping("/uploads/{name}")
    public ResponseEntity<?> getPhoto(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(this.productService.getPhoto(name));
        } catch (IOException e) {
            return ResponseEntity.ok("Данной фотографий не существует");
        }
    }

}