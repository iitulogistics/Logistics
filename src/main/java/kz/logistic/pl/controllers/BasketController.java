package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.BasketJson;
import kz.logistic.pl.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Список корзин"}, description = "API для списка корзин")
@RestController
@RequestMapping(value = "/basket")
public class BasketController {

    private BasketService basketService;

    @Qualifier("defaultBasketService")
    @Autowired(required = false)
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    @ApiOperation(value = "Показывает весь список корзин")
    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(this.basketService.showAllBasket());
    }

    @ApiOperation(value = "Получить корзину пользователя по login_id")
    @GetMapping("/login/{loginId}")
    public ResponseEntity<?> showBasketByLoginId(@PathVariable(value = "loginId") Long loginId) {
        return ResponseEntity.ok(this.basketService.showBasketByLoginId(loginId));
    }

    @ApiOperation(value = "Показывает корзину по ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable(value = "id") Long basketId) {
        return ResponseEntity.ok(this.basketService.showBasket(basketId));
    }

    @ApiOperation(value = "Добавляет корзину")
    @PostMapping("/add")
    public ResponseEntity<?> add(
        @RequestParam Long loginId,
        @RequestParam Long productId

    ) {
        return ResponseEntity.ok(this.basketService.addBasket(loginId,productId));
    }

    @ApiOperation(value = "Добавляет корзину посредством JSON")
    @PostMapping("/addJson")
    public ResponseEntity<?> addJson(
        @RequestBody BasketJson basketJson
    ) {
        return ResponseEntity.ok(this.basketService.addBasketJson(basketJson));
    }

    @ApiOperation(value = "Обновляет корзину")
    @PatchMapping("{id}")
    public ResponseEntity<?> update(
        @PathVariable(value = "id") Long basketId,
        @RequestBody BasketJson basketJson
    ) {
        return ResponseEntity.ok(this.basketService.updateBasket(basketId, basketJson));
    }

    @ApiOperation(value = "Удаляет корзину")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
        @PathVariable(value = "id") Long basketId
    ) {
        return ResponseEntity.ok(this.basketService.deleteBasket(basketId));
    }
}
