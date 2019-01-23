package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.SellerCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerCompanyRepository extends JpaRepository<SellerCompanyEntity, Long> {
    String findByMobilePhone(String mobilePhone);
}
