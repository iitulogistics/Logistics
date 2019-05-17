package kz.logistic.pl.configs;

import kz.logistic.pl.models.builders.LocalizedMessageModelBuilder;
import kz.logistic.pl.models.builders.impl.MessageSourceLocalizedMessageModelBuilder;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.factories.impl.MessageSourceBuilderFactory;
import kz.logistic.pl.models.pojos.impl.DefaultOrder;
import kz.logistic.pl.services.impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    @Qualifier("defaultTokenService")
    public DefaultOtpService otpService() {
        return new DefaultOtpService();
    }

    @Bean
    @Qualifier("defaultOrderService")
    public DefaultOrderService orderService() {
        return new DefaultOrderService();
    }

    @Bean
    @Qualifier("defaultSelleCategoryService")
    public DefaultSellerCategoryService categoryService() {
        return new DefaultSellerCategoryService();
    }

    @Bean
    @Qualifier("defaultDistrictService")
    public DefaultDistrictService districtService() {
        return new DefaultDistrictService();
    }

    @Bean
    @Qualifier("defaultAddressService")
    public DefaultAddressService addressService() {
        return new DefaultAddressService();
    }

    @Bean
    @Qualifier("defaultCreditCardService")
    public DefaultCreditcardService creditCardService() {
        return new DefaultCreditcardService();
    }

    @Bean
    @Qualifier("defaultRegionService")
    public DefaultRegionService regionService() {
        return new DefaultRegionService();
    }

    @Bean
    @Qualifier("defaultGosZakupService")
    public DefaultGosZakupService defaultGosZakupService() {
        return new DefaultGosZakupService();
    }

    @Bean
    @Qualifier("defaultCountryService")
    public DefaultCountryService countryService() {
        return new DefaultCountryService();
    }

    @Bean
    @Qualifier("defaultCityService")
    public DefaultCityService cityService() {
        return new DefaultCityService();
    }

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
    @Qualifier("defaultProductService")
    public DefaultProductService productService() {
        return new DefaultProductService();
    }

    @Bean
    @Qualifier("defaultAuthenticationService")
    public DefaultAuthenticationService authenticationService() {
        return new DefaultAuthenticationService();
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
    @Qualifier("defaultSpecialCharacteristicService")
    public DefaultSpecialCharacteristicService specialCharacteristicService() {
        return new DefaultSpecialCharacteristicService();
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
