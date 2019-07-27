package kz.logistic.pl.services;

import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.MessageEntity;

import java.util.List;

public interface MessageServices {
  void addMessage(MessageEntity entity);

  List<MessageEntity> getMessageBySender(LoginEntity login);
  List<MessageEntity> getMessageByRecipient(LoginEntity login);

  void deleteMessage(MessageEntity entity);
}
