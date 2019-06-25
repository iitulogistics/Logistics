package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findBySellerCompanyId(Long sellerCompanyId);

    List<OrdersEntity> findByCustomerId(Long customerId);
}
