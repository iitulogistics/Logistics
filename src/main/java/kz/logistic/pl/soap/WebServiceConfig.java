package kz.logistic.pl.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/ws/*");
  }

  @Bean(name = "country")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countrySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("CountriesPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/country");
    wsdl11Definition.setSchema(countrySchema);
    return wsdl11Definition;
  }

  @Bean(name = "city")
  public DefaultWsdl11Definition defaultWsdl11DefinitionCity(XsdSchema citySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("CitiesPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/city");
    wsdl11Definition.setSchema(citySchema);
    return wsdl11Definition;
  }

  @Bean(name = "region")
  public DefaultWsdl11Definition defaultWsdl11DefinitionRegion(XsdSchema regionSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("RegionsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/region");
    wsdl11Definition.setSchema(regionSchema);
    return wsdl11Definition;
  }

  @Bean(name = "district")
  public DefaultWsdl11Definition defaultWsdl11DefinitionDistrict(XsdSchema districtSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("DistrictsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/district");
    wsdl11Definition.setSchema(districtSchema);
    return wsdl11Definition;
  }

  @Bean(name = "address")
  public DefaultWsdl11Definition defaultWsdl11DefinitionAddress(XsdSchema addressSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("AddressPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/address");
    wsdl11Definition.setSchema(addressSchema);
    return wsdl11Definition;
  }

  @Bean(name = "basket")
  public DefaultWsdl11Definition defaultWsdl11DefinitionBasket(XsdSchema basketSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("BasketPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/basket");
    wsdl11Definition.setSchema(basketSchema);
    return wsdl11Definition;
  }

  @Bean(name = "credit_card")
  public DefaultWsdl11Definition defaultWsdl11DefinitionCreditCard(XsdSchema creditCardSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("CreditCardPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/credit_card");
    wsdl11Definition.setSchema(creditCardSchema);
    return wsdl11Definition;
  }

  @Bean(name = "customer")
  public DefaultWsdl11Definition defaultWsdl11DefinitionCustomer(XsdSchema customerSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("CustomerPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/customer");
    wsdl11Definition.setSchema(customerSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema countrySchema() {
    return new SimpleXsdSchema(new ClassPathResource("country.xsd"));
  }

  @Bean
  public XsdSchema citySchema() {
    return new SimpleXsdSchema(new ClassPathResource("city.xsd"));
  }

  @Bean
  public XsdSchema regionSchema() {
    return new SimpleXsdSchema(new ClassPathResource("region.xsd"));
  }

  @Bean
  public XsdSchema districtSchema() {
    return new SimpleXsdSchema(new ClassPathResource("district.xsd"));
  }

  @Bean
  public XsdSchema addressSchema() {
    return new SimpleXsdSchema(new ClassPathResource("address.xsd"));
  }

  @Bean
  public XsdSchema basketSchema() {
    return new SimpleXsdSchema(new ClassPathResource("basket.xsd"));
  }

  @Bean
  public XsdSchema creditCardSchema() {
    return new SimpleXsdSchema(new ClassPathResource("credit_card.xsd"));
  }

  @Bean
  public XsdSchema customerSchema() {
    return new SimpleXsdSchema(new ClassPathResource("customer.xsd"));
  }
}
