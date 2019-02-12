package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressesEntity, Long> {
}