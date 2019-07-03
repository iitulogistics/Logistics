package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BasketRepository extends JpaRepository<BasketEntity, Long> {

    List<BasketEntity> findByLoginIdAndProductId(Long loginId, Long productId);

    List<BasketEntity> findByLoginId(Long loginId);

}

