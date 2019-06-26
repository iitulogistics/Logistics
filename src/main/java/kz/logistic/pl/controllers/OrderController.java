package kz.logistic.pl.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.logistic.pl.models.pojos.json.OrderJson;
import kz.logistic.pl.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "Список заказов", description = "API для списка заказов")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

  private OrderService orderService;

  @Autowired(required = false)
  @Qualifier("defaultOrderService")
  private void setOrderService(OrderService orderService) {
    this.orderService = orderService;
  }

  @ApiOperation(value = "Показывает весь список заказов")
  @GetMapping("/all")
  public ResponseEntity<?> all() {
    return ResponseEntity.ok(this.orderService.showAllOrders());
  }

    @ApiOperation(value = "Получение списка моих заказов для продавца")
    @GetMapping("/{sellerCompanyId}")
    public ResponseEntity<?> showOrdersBySeller(@PathVariable Long sellerCompanyId) {
        return ResponseEntity.ok(this.orderService.showOrdersBySeller(sellerCompanyId));
    }

    @ApiOperation(value = " Получение списка моих заказов для покупателя")
    @GetMapping("/{customerId}")
    public ResponseEntity<?> showOrdersByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(this.orderService.showOrdersByCustomer(customerId));
    }

  @ApiOperation(value = "Показывает заказ по ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getId(@PathVariable(value = "id") Long orderId) {
    return ResponseEntity.ok(this.orderService.showOrder(orderId));
  }

  @ApiOperation(value = "Добавляет заказ")
  @PostMapping("/add")
  public ResponseEntity<?> add(
    @RequestParam Long productId,
    @RequestParam Long sellerCompanyId,
    @RequestParam Date orderDate,
    @RequestParam Integer productCount,
    @RequestParam Integer unitPrice,
    @RequestParam Long customerId,
    @RequestParam String deliveringStatus,
    @RequestParam Integer productAmount,
    @RequestParam Integer orderAmount) {
    return ResponseEntity.ok(this.orderService.addOrder(
      productId, sellerCompanyId, orderDate, productCount, unitPrice,
      customerId, deliveringStatus, productAmount, orderAmount));
  }

  @ApiOperation(value = "Добавляет заказ посредством JSON")
  @PostMapping("/addJson")
  public ResponseEntity<?> addJson(
    @RequestBody List<OrderJson> orderJson
  ) {
    return ResponseEntity.ok(this.orderService.addOrderJson(orderJson));
  }

  @ApiOperation(value = "Обновляет заказ")
  @PatchMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable(value = "id") Long orderId,
    @RequestBody OrderJson orderJson
  ) {
    return ResponseEntity.ok(this.orderService.updateOrder(orderId, orderJson));
  }

  @ApiOperation(value = "Удаляет заказ")
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long orderId) {
    return ResponseEntity.ok(this.orderService.deleteOrder(orderId));
  }

}
