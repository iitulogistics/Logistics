package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface
OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findBySellerCompanyId(Long sellerCompanyId);

    List<OrdersEntity> findByCustomerId(Long customerId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update OrdersEntity o set o.customerId = ?2, o.deliveringStatus = ?3, o.orderAmount = ?4, " +
      "o.orderDate = ?5, o.orderNumber = ?6, o.orderAmount = ?7, o.productId = ?8, o.sellerCompanyId = ?9, " +
      "o.totalPrice = ?10, o.unitPrice = ?11, o.productCount = ?12 where o.orderId = ?1")
    void updateOrderById(Long id, Long customerId, String deliveringStatus, int orderAmount,
                         Date orderDate, Long orderNumber,
                         int productAmount, Long productId, Long sellerCompanyId,
                         int totalPrice, int unitPrice, int productCount);
}
