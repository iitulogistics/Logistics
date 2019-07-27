package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.BasketEntity;
import kz.logistic.pl.models.pojos.Basket;
import kz.logistic.pl.models.pojos.impl.DefaultBasket;
import kz.logistic.pl.models.pojos.json.BasketJson;
import kz.logistic.pl.repositories.BasketRepository;
import kz.logistic.pl.services.BasketService;
import kz.logistic.pl.utils.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultBasketService implements BasketService {

    private BasketRepository basketRepository;
    private ReturnMessage returnMessage;

    @Autowired(required = false)
    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

    @Autowired(required = false)
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public List<Basket> showAllBasket() {
        List<BasketEntity> basketEntities = this.basketRepository.findAll();
        return basketEntities.stream().map(basketEntity ->
            DefaultBasket.builder()
                .basketId(basketEntity.getBasketId())
                .loginId(basketEntity.getLoginId())
                .productId(basketEntity.getProductId()).build())
            .collect(Collectors.toList());
    }

    @Override
    public List<Basket> showBasketByLoginId(Long loginId) {
        List<BasketEntity> basketEntities = this.basketRepository.findByLoginId(loginId);
        return basketEntities.stream().map(basketEntity ->
            DefaultBasket.builder()
                .basketId(basketEntity.getBasketId())
                .loginId(basketEntity.getLoginId())
                .productId(basketEntity.getProductId()).build())
            .collect(Collectors.toList());
    }

    public boolean exists(Long loginId, Long productId){
        List<BasketEntity> basketEntities = this.basketRepository.findByLoginIdAndProductId(loginId,productId);
        return basketEntities.size() > 0;
    }

    @Override
    public DefaultBasket showBasket(Long basketId) {
        BasketEntity basketEntity = this.basketRepository.findById(basketId).orElse(null);
        return DefaultBasket.builder()
            .basketId(basketEntity.getBasketId())
            .loginId(basketEntity.getLoginId())
            .productId(basketEntity.getProductId()).build();
    }

    @Override
    public String addBasket(Long loginId, Long productId) {
        if (exists(loginId,productId)){
            return MessageFormat.format(returnMessage.getBasketAddError(),productId);
        }
        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setLoginId(loginId);
        basketEntity.setProductId(productId);
        this.basketRepository.save(basketEntity);
        log.info("Added new login " + loginId + " " + new Date());
        return MessageFormat.format(returnMessage.getBasketAddSuccess(), loginId);
    }

    @Override
    public String addBasketJson(BasketJson basketJson) {
        if (exists(basketJson.getLoginId(),basketJson.getProductId())){
            return MessageFormat.format(returnMessage.getBasketAddError(), basketJson.getLoginId());
        }
        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setLoginId(basketJson.getLoginId());
        basketEntity.setProductId(basketJson.getProductId());
        this.basketRepository.save(basketEntity);
        log.info("Added new login " + basketJson.getLoginId() + " " + new Date());
        return java.text.MessageFormat.format(returnMessage.getBasketAddSuccess(), basketJson.getLoginId());
    }

    @Override
    public String updateBasket(Long basketId, BasketJson basketJson) {
        BasketEntity basketEntity = this.basketRepository.findById(basketId).orElse(null);
        if (Objects.nonNull(basketEntity)){
            if (basketJson.getLoginId() != null) {
                basketEntity.setLoginId(basketJson.getLoginId());
            }
            if (basketJson.getProductId() != null) {
                basketEntity.setProductId(basketJson.getProductId());
            }
            this.basketRepository.save(basketEntity);
            log.info("Updated " + basketJson.getLoginId() + " " + new Date());
            return MessageFormat.format(returnMessage.getBasketUpdateSuccess(), basketJson.getLoginId());
        } else {
            return returnMessage.getBasketUpdateError();
        }
    }

    @Override
    public String deleteBasket(Long basketId) {
        BasketEntity basketEntity = this.basketRepository.findById(basketId).orElse(null);
        if (Objects.nonNull(basketEntity)){
            this.basketRepository.deleteById(basketId);
            log.info("Deleted " + basketEntity.getBasketId() + " address" + new Date());
            return MessageFormat.format(returnMessage.getBasketDeleteSuccess(), basketId);
        } else {
            return  MessageFormat.format(returnMessage.getBasketDeleteError(), basketId);
        }
    }

  @Override
  public String deleteByLoginId(Long loginId) {
    for(Basket basket : showBasketByLoginId(loginId)){
      deleteBasket(basket.getBasketId());
    }
    return "Корзина очищена";
  }
}
