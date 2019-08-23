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

  @Bean(name = "order")
  public DefaultWsdl11Definition defaultWsdl11DefinitionOrder(XsdSchema orderSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("OrderPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/order");
    wsdl11Definition.setSchema(orderSchema);
    return wsdl11Definition;
  }

  @Bean(name = "otp")
  public DefaultWsdl11Definition defaultWsdl11DefinitionOtp(XsdSchema otpSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("OtpPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/otp");
    wsdl11Definition.setSchema(otpSchema);
    return wsdl11Definition;
  }

  @Bean(name = "payment")
  public DefaultWsdl11Definition defaultWsdl11DefinitionPayment(XsdSchema paymentSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PaymentPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/payment");
    wsdl11Definition.setSchema(paymentSchema);
    return wsdl11Definition;
  }

  @Bean(name = "product")
  public DefaultWsdl11Definition defaultWsdl11DefinitionProduct(XsdSchema productSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("ProductPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/product");
    wsdl11Definition.setSchema(productSchema);
    return wsdl11Definition;
  }

  @Bean(name = "product_category")
  public DefaultWsdl11Definition defaultWsdl11DefinitionProductCategory(XsdSchema productCategorySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("ProductCategoryPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/product_category");
    wsdl11Definition.setSchema(productCategorySchema);
    return wsdl11Definition;
  }

  @Bean(name = "product_subcategory")
  public DefaultWsdl11Definition defaultWsdl11DefinitionProductSubCategory(XsdSchema productSubCategorySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("ProductSubCategoryPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/product_subcategory");
    wsdl11Definition.setSchema(productSubCategorySchema);
    return wsdl11Definition;
  }

  @Bean(name = "seller_category")
  public DefaultWsdl11Definition defaultWsdl11DefinitionSellerCategory(XsdSchema sellerCategorySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("SellerCategoryPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/seller_category");
    wsdl11Definition.setSchema(sellerCategorySchema);
    return wsdl11Definition;
  }

  @Bean(name = "seller_company")
  public DefaultWsdl11Definition defaultWsdl11DefinitionSellerCompany(XsdSchema sellerCompanySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("SellerCompanyPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/seller_company");
    wsdl11Definition.setSchema(sellerCompanySchema);
    return wsdl11Definition;
  }

  @Bean(name = "shipper")
  public DefaultWsdl11Definition defaultWsdl11DefinitionShipper(XsdSchema shipperSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("ShipperPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/shipper");
    wsdl11Definition.setSchema(shipperSchema);
    return wsdl11Definition;
  }

  @Bean(name = "special_characteristic")
  public DefaultWsdl11Definition defaultWsdl11DefinitionSpecialCharacteristic(XsdSchema sellerCompanySchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("SpecialCharacteristicPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://logistic.soap/special_characteristic");
    wsdl11Definition.setSchema(sellerCompanySchema);
    return wsdl11Definition;
  }
  @Bean
  public XsdSchema countrySchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/country.xsd"));
  }

  @Bean
  public XsdSchema citySchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/city.xsd"));
  }

  @Bean
  public XsdSchema regionSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/region.xsd"));
  }

  @Bean
  public XsdSchema districtSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/district.xsd"));
  }

  @Bean
  public XsdSchema addressSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/address.xsd"));
  }

  @Bean
  public XsdSchema basketSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/basket.xsd"));
  }

  @Bean
  public XsdSchema creditCardSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/credit_card.xsd"));
  }

  @Bean
  public XsdSchema customerSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/customer.xsd"));
  }

  @Bean
  public XsdSchema orderSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/order.xsd"));
  }

  @Bean
  public XsdSchema otpSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/otp.xsd"));
  }

  @Bean
  public XsdSchema paymentSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/payment.xsd"));
  }

  @Bean
  public XsdSchema productSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/product.xsd"));
  }

  @Bean
  public XsdSchema productCategorySchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/product_category.xsd"));
  }

  @Bean
  public XsdSchema productSubCategorySchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/product_subcategory.xsd"));
  }

  @Bean
  public XsdSchema sellerCategorySchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/seller_category.xsd"));
  }

  @Bean
  public XsdSchema sellerCompanySchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/seller_company.xsd"));
  }

  @Bean
  public XsdSchema shipperSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/shipper.xsd"));
  }

  @Bean
  public XsdSchema specialCharacteristicSchema() {
    return new SimpleXsdSchema(new ClassPathResource("soap/special_characteristic.xsd"));
  }
}
