package kz.logistic.pl.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kz.logistic.pl.models.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

  //@Query("select l from LoginEntity l where not l.customerEntity = null")
  List<LoginEntity> findByCustomerEntityCustomerIdIsNotNull();

  @Query("select l from LoginEntity l where l.username = ?1 and l.password = ?2")
  LoginEntity findByUsernameAndPassword(String username, String password);

  @Query("select l from LoginEntity l where l.username = ?1")
  Optional<LoginEntity> findByUsername(String username);
}
