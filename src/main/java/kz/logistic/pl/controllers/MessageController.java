package kz.logistic.pl.controllers;

import kz.logistic.pl.models.entities.MessageEntity;
import kz.logistic.pl.repositories.MessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
  private final MessageRepository repository;

  public MessageController(MessageRepository repository){
    this.repository = repository;
  }

  @MessageMapping("/sendMessage")
  @SendTo("/sendMessage/message")
  public ResponseEntity<?> sendMessage(MessageEntity message) throws Exception{
    repository.save(message);
    return ResponseEntity.ok(message);
  }
}
