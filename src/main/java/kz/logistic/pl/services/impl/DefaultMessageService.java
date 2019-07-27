package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.LoginEntity;
import kz.logistic.pl.models.entities.MessageEntity;
import kz.logistic.pl.repositories.MessageRepository;
import kz.logistic.pl.services.MessageServices;

import java.util.List;

public class DefaultMessageService implements MessageServices {
  private final MessageRepository repository;

  public DefaultMessageService(MessageRepository repository){
    this.repository = repository;
  }

  @Override
  public void addMessage(MessageEntity entity) {
    repository.save(entity);
  }

  @Override
  public List<MessageEntity> getMessageBySender(LoginEntity login) {
    return repository.getMessageBySender(login);
  }

  @Override
  public List<MessageEntity> getMessageByRecipient(LoginEntity login) {
    return repository.getMessageByRecipient(login);
  }

  @Override
  public void deleteMessage(MessageEntity entity) {
    repository.delete(entity);
  }
}
