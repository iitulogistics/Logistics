package kz.logistic.pl.models.factories.impl;

import kz.logistic.pl.models.builders.LocalizedMessageModelBuilder;
import kz.logistic.pl.models.builders.impl.MessageSourceLocalizedMessageModelBuilder;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class MessageSourceBuilderFactory implements
        LocalizedMessageBuilderFactory {

  private ApplicationContext applicationContext;

  @Autowired
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public LocalizedMessageModelBuilder builder() {
    return applicationContext.getBean(MessageSourceLocalizedMessageModelBuilder.class);
  }

}
