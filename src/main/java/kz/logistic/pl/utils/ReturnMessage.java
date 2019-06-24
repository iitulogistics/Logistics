package kz.logistic.pl.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@PropertySource("classpath:application-return.properties")
@Configuration
public class ReturnMessage {

    @Value("${city.add.success}")
    String cityAddSuccess;
    @Value("${city.add.error}")
    String cityAddError;
    @Value("${city.update.success}")
    String cityUpdateSuccess;
    @Value("${city.update.error}")
    String cityUpdateError;
    @Value("${city.delete.success}")
    String cityDeleteSuccess;
    @Value("${city.delete.error}")
    String cityDeleteError;
    @Value("${country.add.success}")
    String countryAddSuccess;
    @Value("${country.add.error}")
    String countryAddError;
    @Value("${country.update.success}")
    String countryUpdateSuccess;
    @Value("${country.update.error}")
    String countryUpdateError;
    @Value("${country.delete.success}")
    String countryDeleteSuccess;
    @Value("${country.delete.error}")
    String countryDeleteError;
    @Value("${address.add.success}")
    String addressAddSuccess;
    @Value("${address.add.error}")
    String addressAddError;
    @Value("${address.update.success}")
    String addressUpdateSuccess;
    @Value("${address.update.error}")
    String addressUpdateError;
    @Value("${address.delete.success}")
    String addressDeleteSuccess;
    @Value("${address.delete.error}")
    String addressDeleteError;
    @Value("${creditcard.add.success}")
    String creditcardAddSuccess;
    @Value("${creditcard.add.error}")
    String creditcardAddError;
    @Value("${creditcard.update.success}")
    String creditcardUpdateSuccess;
    @Value("${creditcard.update.error}")
    String creditcardUpdateError;
    @Value("${creditcard.delete.success}")
    String creditcardDeleteSuccess;
    @Value("${creditcard.delete.error}")
    String creditcardDeleteError;
    @Value("${customer.add.success}")
    String customerAddSuccess;
    @Value("${customer.add.error}")
    String customerAddError;
    @Value("${customer.update.success}")
    String customerUpdateSuccess;
    @Value("${customer.update.error}")
    String customerUpdateError;
    @Value("${customer.delete.success}")
    String customerDeleteSuccess;
    @Value("${customer.delete.error}")
    String customerDeleteError;
    @Value("${district.add.success}")
    String districtAddSuccess;
    @Value("${district.add.error}")
    String districtAddError;
    @Value("${district.update.success}")
    String districtUpdateSuccess;
    @Value("${district.update.error}")
    String districtUpdateError;
    @Value("${district.delete.success}")
    String districtDeleteSuccess;
    @Value("${district.delete.error}")
    String districtDeleteError;
    @Value("${order.add.success}")
    String orderAddSuccess;
    @Value("${order.add.error}")
    String orderAddError;
    @Value("${order.update.success}")
    String orderUpdateSuccess;
    @Value("${order.update.error}")
    String orderUpdateError;
    @Value("${order.delete.success}")
    String orderDeleteSuccess;
    @Value("${order.delete.error}")
    String orderDeleteError;
    @Value("${productcategory.add.success}")
    String productcategoryAddSuccess;
    @Value("${productcategory.add.error}")
    String productcategoryAddError;
    @Value("${productcategory.update.success}")
    String productcategoryUpdateSuccess;
    @Value("${productcategory.update.error}")
    String productcategoryUpdateError;
    @Value("${productcategory.delete.success}")
    String productcategoryDeleteSuccess;
    @Value("${productcategory.delete.error}")
    String productcategoryDeleteError;
    @Value("${product.add.success}")
    String productAddSuccess;
    @Value("${product.add.error}")
    String productAddError;
    @Value("${product.update.success}")
    String productUpdateSuccess;
    @Value("${product.update.error}")
    String productUpdateError;
    @Value("${product.delete.success}")
    String productDeleteSuccess;
    @Value("${product.delete.error}")
    String productDeleteError;
    @Value("${productsubcategory.add.success}")
    String productsubcategoryAddSuccess;
    @Value("${productsubcategory.add.error}")
    String productsubcategoryAddError;
    @Value("${productsubcategory.update.success}")
    String productsubcategoryUpdateSuccess;
    @Value("${productsubcategory.update.error}")
    String productsubcategoryUpdateError;
    @Value("${productsubcategory.delete.success}")
    String productsubcategoryDeleteSuccess;
    @Value("${productsubcategory.delete.error}")
    String productsubcategoryDeleteError;
    @Value("${region.add.success}")
    String regionAddSuccess;
    @Value("${region.add.error}")
    String regionAddError;
    @Value("${region.update.success}")
    String regionUpdateSuccess;
    @Value("${region.update.error}")
    String regionUpdateError;
    @Value("${region.delete.success}")
    String regionDeleteSuccess;
    @Value("${region.delete.error}")
    String regionDeleteError;
    @Value("${role.add.success}")
    String roleAddSuccess;
    @Value("${role.add.error}")
    String roleAddError;
    @Value("${role.update.success}")
    String roleUpdateSuccess;
    @Value("${role.update.error}")
    String roleUpdateError;
    @Value("${role.delete.success}")
    String roleDeleteSuccess;
    @Value("${role.delete.error}")
    String roleDeleteError;
    @Value("${sellercategory.add.success}")
    String sellercategoryAddSuccess;
    @Value("${sellercategory.add.error}")
    String sellercategoryAddError;
    @Value("${sellercategory.update.success}")
    String sellercategoryUpdateSuccess;
    @Value("${sellercategory.update.error}")
    String sellercategoryUpdateError;
    @Value("${sellercategory.delete.success}")
    String sellercategoryDeleteSuccess;
    @Value("${sellercategory.delete.error}")
    String sellercategoryDeleteError;
    @Value("${sellercompany.add.success}")
    String sellercompanyAddSuccess;
    @Value("${sellercompany.add.error}")
    String sellercompanyAddError;
    @Value("${sellercompany.update.success}")
    String sellercompanyUpdateSuccess;
    @Value("${sellercompany.update.error}")
    String sellercompanyUpdateError;
    @Value("${sellercompany.delete.success}")
    String sellercompanyDeleteSuccess;
    @Value("${sellercompany.delete.error}")
    String sellercompanyDeleteError;
    @Value("${shipper.add.success}")
    String shipperAddSuccess;
    @Value("${shipper.add.error}")
    String shipperAddError;
    @Value("${shipper.update.success}")
    String shipperUpdateSuccess;
    @Value("${shipper.update.error}")
    String shipperUpdateError;
    @Value("${shipper.delete.success}")
    String shipperDeleteSuccess;
    @Value("${shipper.delete.error}")
    String shipperDeleteError;
    @Value("${specialcharacteristic.add.success}")
    String specialcharacteristicAddSuccess;
    @Value("${specialcharacteristic.add.error}")
    String specialcharacteristicAddError;
    @Value("${specialcharacteristic.update.success}")
    String specialcharacteristicUpdateSuccess;
    @Value("${specialcharacteristic.update.error}")
    String specialcharacteristicUpdateError;
    @Value("${specialcharacteristic.delete.success}")
    String specialcharacteristicDeleteSuccess;
    @Value("${specialcharacteristic.delete.error}")
    String specialcharacteristicDeleteError;
}

