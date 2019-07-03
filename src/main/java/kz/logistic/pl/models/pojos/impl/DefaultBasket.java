package kz.logistic.pl.models.pojos.impl;

import kz.logistic.pl.models.pojos.Basket;
import lombok.Builder;

@Builder
public class DefaultBasket implements Basket {

    private Long basketId;
    private Long loginId;
    private Long productId;

    @Override
    public Long getBasketId() {
        return basketId;
    }

    @Override
    public Long getLoginId() {
        return loginId;
    }

    @Override
    public Long getProductId() {
        return productId;
    }
}
