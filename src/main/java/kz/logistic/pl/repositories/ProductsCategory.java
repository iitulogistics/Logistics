package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCategory extends JpaRepository<ProductsCategoryEntity, Long> {
}
