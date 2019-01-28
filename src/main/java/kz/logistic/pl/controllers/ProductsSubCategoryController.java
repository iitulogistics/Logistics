package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.services.ProductSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam Integer productCategoryId,
            @RequestParam String subCategoryAddInfo) {
        this.productSubCategoryService.addProductSubCategory(
                subCategoryNameKk, subCategoryNameRu, subCategoryNameEn,
                productCategoryId, subCategoryAddInfo
        );
        return ResponseEntity.ok("Новая подкатегория продуктов добавлена");
    }
}
