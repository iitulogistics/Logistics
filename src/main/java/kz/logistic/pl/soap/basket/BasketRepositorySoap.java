package kz.logistic.pl.soap.basket;

import kz.logistic.pl.models.entities.AddressesEntity;
import kz.logistic.pl.models.entities.BasketEntity;
import kz.logistic.pl.models.entities.CityEntity;
import kz.logistic.pl.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.address.Address;
import soap.logistic.basket.Basket;
import soap.logistic.city.City;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BasketRepositorySoap {
  private static final Map<Long, Basket> basketMap = new HashMap<>();

  private BasketRepository basketRepository;

  @Autowired(required = false)
  public void setBasketRepository(BasketRepository basketRepository) {
    this.basketRepository = basketRepository;
  }

  @PostConstruct
  public void initData() {
    List<BasketEntity> entities = this.basketRepository.findAll();
    entities.forEach(basketEntity -> {
      Basket basket = convertToBasket(basketEntity);

      basketMap.put(basket.getBasketId(), basket);
    });
  }

  public Basket findBasketId(Long id) {
    return basketMap.get(id);
  }

  public Basket addBasket(Long loginId, Long productId) {
    BasketEntity basketEntity = new BasketEntity();
    basketEntity.setLoginId(loginId);
    basketEntity.setProductId(productId);
    basketRepository.save(basketEntity);

    Basket basket = convertToBasket(basketEntity);

    basketMap.put(basket.getBasketId(), basket);
    return basket;
  }

  public Basket updateBasket(Long id, Long loginId, Long productId) {
    Basket basket = basketMap.get(id);
    basket.setLoginId(loginId);
    basket.setProductId(productId);

    basketRepository.updateBasketById(id, loginId, productId);
    return basket;
  }

  public String deleteBasketId(Long id) {
    BasketEntity basketEntity = this.basketRepository.findById(id).orElse(null);

    if (basketEntity != null) {
      this.basketRepository.deleteById(id);
      return "id" + id + "удалён";
    } else {
      return "id" + id + "не удалён";
    }
  }

  private Basket convertToBasket(BasketEntity entity) {
    Basket basket = new Basket();
    basket.setBasketId(entity.getBasketId());
    basket.setProductId(entity.getProductId());
    basket.setLoginId(entity.getLoginId());
    return basket;
  }
}
