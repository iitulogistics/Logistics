package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BasketRepository extends JpaRepository<BasketEntity, Long> {

  List<BasketEntity> findByLoginIdAndProductId(Long loginId, Long productId);

  List<BasketEntity> findByLoginId(Long loginId);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update BasketEntity b set b.loginId = ?2, b.productId = ?3 where b.basketId = ?1")
  void updateBasketById(Long id, Long loginId, Long productId);
}

