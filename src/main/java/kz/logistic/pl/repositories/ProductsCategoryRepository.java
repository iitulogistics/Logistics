package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsCategoryRepository extends JpaRepository<ProductsCategoryEntity, Long> {
}
