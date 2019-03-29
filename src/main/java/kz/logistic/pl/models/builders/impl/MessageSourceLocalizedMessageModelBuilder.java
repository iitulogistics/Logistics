package kz.logistic.pl.models.builders.impl;

import java.util.Locale;
import kz.logistic.pl.models.builders.LocalizedMessageModelBuilder;
import kz.logistic.pl.models.pojos.LocalizedMessage;
import kz.logistic.pl.models.pojos.impl.DefaultLocalizedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;


@Scope("prototype")
public class MessageSourceLocalizedMessageModelBuilder implements
        LocalizedMessageModelBuilder {

  private static final String RU = "ru";
  private static final String KK = "kk";
  private static final String EN = "en";

  private String ru;
  private String kk;
  private String en;
  private Object[] ruArgs = null;
  private Object[] kkArgs = null;
  private Object[] enArgs = null;
  private MessageSource messageSource;

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public LocalizedMessageModelBuilder ru(String message) {
    this.ru = message;

    return this;
  }

  @Override
  public LocalizedMessageModelBuilder kk(String message) {
    this.kk = message;
    return this;
  }

  @Override
  public LocalizedMessageModelBuilder en(String message) {
    this.en = message;
    return this;
  }

  @Override
  public LocalizedMessageModelBuilder ruArgs(Object[] args) {
    this.ruArgs = args;

    return this;
  }

  @Override
  public LocalizedMessageModelBuilder kkArgs(Object[] args) {
    this.kkArgs = args;

    return this;
  }

  @Override
  public LocalizedMessageModelBuilder enArgs(Object[] args) {
    this.enArgs = args;
    return this;
  }

  private String processMessage(String message, Object[] args, String locale) {
    try {
      return messageSource.getMessage(message, args, new Locale(locale));
    } catch (NoSuchMessageException e) {
      return message;
    }
  }

  @Override
  public synchronized LocalizedMessage build() {
    return new DefaultLocalizedMessage(
            processMessage(ru, ruArgs, RU),
            processMessage(kk, kkArgs, KK),
            processMessage(en, enArgs, EN));
  }

}