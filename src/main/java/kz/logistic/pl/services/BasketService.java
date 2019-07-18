package kz.logistic.pl.services;

import kz.logistic.pl.models.pojos.Basket;
import kz.logistic.pl.models.pojos.impl.DefaultBasket;
import kz.logistic.pl.models.pojos.json.BasketJson;

import java.util.List;

public interface BasketService {

    List<Basket> showAllBasket();

    List<Basket> showBasketByLoginId(Long loginId);

    DefaultBasket showBasket(Long basketId);

    String addBasket(Long loginId, Long productId);

    String addBasketJson(BasketJson basketJson);

    String updateBasket(Long basketId, BasketJson basketJson);

    String deleteBasket(Long basketId);

    String deleteByLoginId(Long loginId);

}
