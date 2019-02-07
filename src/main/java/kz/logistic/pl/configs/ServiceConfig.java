package kz.logistic.pl.configs;

import kz.logistic.pl.models.builders.LocalizedMessageModelBuilder;
import kz.logistic.pl.models.builders.impl.MessageSourceLocalizedMessageModelBuilder;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.factories.impl.MessageSourceBuilderFactory;
import kz.logistic.pl.services.impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

  @Bean
  @Qualifier("defaultRoleService")
  public DefaultRoleService roleService() {
    return new DefaultRoleService();
  }

    @Bean
    @Qualifier("defaultShipperService")
    public DefaultShipperService shipperService() {
        return new DefaultShipperService();
    }


  @Bean
  @Qualifier("defaultSellerCompanyService")
  public DefaultSellerCompanyService sellerCompanyService() {
      return new DefaultSellerCompanyService();
  }

  @Bean
  @Qualifier("defaultProductSubCategoryService")
  public DefaultProductSubCategoryService productSubCategoryService() {
    return new DefaultProductSubCategoryService();
  }

    @Bean
    @Qualifier("defaultProductCategoryService")
    public DefaultProductCategoryService productCategoryService() {
        return new DefaultProductCategoryService();
    }

    @Bean
    @Qualifier("defaultCustomerService")
    public DefaultCustomerService customerService() {
        return new DefaultCustomerService();
    }

  @Bean
  public LocalizedMessageBuilderFactory localizedMessageBuilderFactory() {
    return new MessageSourceBuilderFactory();
  }

  @Bean
  public LocalizedMessageModelBuilder localizedMessageModelBuilder() {
    return new MessageSourceLocalizedMessageModelBuilder();
  }
}
