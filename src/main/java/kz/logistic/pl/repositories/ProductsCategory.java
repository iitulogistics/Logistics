package kz.logistic.pl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCategory extends JpaRepository<ProductsCategory, Long> {
}
