package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}