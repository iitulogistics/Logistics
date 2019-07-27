package kz.logistic.pl.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @OneToOne
  @JoinColumn(name = "login_id")
  LoginEntity sender;

  @OneToOne
  @JoinColumn(name = "login_id")
  LoginEntity recipient;

  String message;
}
