package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.CustomerEntity;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("update CustomerEntity c set c.customerNameEn = ?2, c.customerNameRu = ?3, c.customerNameKk = ?4," +
    "c.addInfo = ?5, c.email = ?6, c.iinOrBin = ?7, c.mobilePhone = ?8, c.phoneNumber = ?9, " +
    "c.addressId = ?10 where c.customerId = ?1")
  void updateCustomerById(Long id,
                          String customerNameEn, String customerNameRu, String customerNameKk,
                          String addInfo, String email, String iinOrBin, String mobilePhone,
                          String phoneNumber, Long addressId);
}
