package kz.logistic.pl.repositories;

import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

  @Query("select m from MessageEntity m where m.sendTo = ?1")
  List<MessageEntity> getMessageByRecipient(LoginEntity l);

  @Query("select m from MessageEntity m where m.sendFrom = ?1")
  List<MessageEntity> getMessageBySender(LoginEntity l);
}
