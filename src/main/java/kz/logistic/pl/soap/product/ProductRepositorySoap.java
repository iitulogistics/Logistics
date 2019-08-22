package kz.logistic.pl.soap.product;

import kz.logistic.pl.models.entities.PaymentEntity;
import kz.logistic.pl.models.entities.ProductsEntity;
import kz.logistic.pl.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.payment.Payment;
import soap.logistic.product.Product;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductRepositorySoap {
  private static final Map<Long, Product> productMap = new HashMap<>();

  private ProductRepository productRepository;

  @Autowired(required = false)
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @PostConstruct
  public void initData() {
    List<ProductsEntity> entities = this.productRepository.findAll();
    entities.forEach(productEntity -> {
      Product product = convertToProduct(productEntity);

      productMap.put(product.getProductId(), product);
    });
  }

  public Product findProductId(Long id) {
    return productMap.get(id);
  }

  public Product addProduct(String manufacturer, int price, long productCategoryId, String productDescription,
                            String productNameEn, String productNameKk, String productNameRu,
                            String productsImg, long productSubcategoryId, long sellerCompanyId,
                            String serialNumber, String size, long specialCharacteristicId,
                            String uniqueIdNumber, int weight) throws ParseException {
    ProductsEntity productsEntity = new ProductsEntity();
    productsEntity.setManufacturer(manufacturer);
    productsEntity.setPrice(price);
    productsEntity.setProductCategoryId(productCategoryId);
    productsEntity.setProductDescription(productDescription);
    productsEntity.setProductNameEn(productNameEn);
    productsEntity.setProductNameKk(productNameKk);
    productsEntity.setProductNameRu(productNameRu);
    productsEntity.setProductsImg(productsImg);
    productsEntity.setProductSubcategoryId(productSubcategoryId);
    productsEntity.setSellerCompanyId(sellerCompanyId);
    productsEntity.setSerialNumber(serialNumber);
    productsEntity.setSize(size);
    productsEntity.setSpecialCharacteristicId(specialCharacteristicId);
    productsEntity.setUniqueIdNumber(uniqueIdNumber);
    productsEntity.setWeight(weight);

    productRepository.save(productsEntity);

    Product product = convertToProduct(productsEntity);
    productMap.put(product.getProductId(), product);

    return product;
  }

  public Product updateProduct(long id, String manufacturer, int price, long productCategoryId, String productDescription,
                               String productNameEn, String productNameKk, String productNameRu,
                               String productsImg, long productSubcategoryId, long sellerCompanyId,
                               String serialNumber, String size, long specialCharacteristicId,
                               String uniqueIdNumber, int weight) throws ParseException {
    Product product = productMap.get(id);
    product.setManufacturer(manufacturer);
    product.setPrice(price);
    product.setProductCategoryId(productCategoryId);
    product.setProductDescription(productDescription);
    product.setProductNameEn(productNameEn);
    product.setProductNameKk(productNameKk);
    product.setProductNameRu(productNameRu);
    product.setProductsImg(productsImg);
    product.setProductSubcategoryId(productSubcategoryId);
    product.setSellerCompanyId(sellerCompanyId);
    product.setSerialNumber(serialNumber);
    product.setSize(size);
    product.setSpecialCharacteristicId(specialCharacteristicId);
    product.setUniqueIdNumber(uniqueIdNumber);
    product.setWeight(weight);

    productRepository.updateProductById(id, manufacturer, price, productCategoryId, productDescription,
      productNameEn, productNameKk, productNameRu, productsImg, productSubcategoryId, sellerCompanyId,
      serialNumber, size, specialCharacteristicId, uniqueIdNumber, weight);
    return product;
  }

  public String deleteProduct(Long id) {
    ProductsEntity productsEntity = this.productRepository.findById(id).orElse(null);

    if (productsEntity != null) {
      this.productRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private Product convertToProduct(ProductsEntity entity) {
    Product product = new Product();
    product.setManufacturer(entity.getManufacturer());
    product.setPrice(entity.getPrice());
    product.setProductCategoryId(entity.getProductCategoryId());
    product.setProductDescription(entity.getProductDescription());
    product.setProductId(entity.getProductId());
    product.setProductNameEn(entity.getProductNameEn());
    product.setProductNameKk(entity.getProductNameKk());
    product.setProductNameRu(entity.getProductNameRu());
    product.setProductsImg(entity.getProductsImg());
    product.setProductSubcategoryId(entity.getProductSubcategoryId());
    product.setSellerCompanyId(entity.getSellerCompanyId());
    product.setSerialNumber(entity.getSerialNumber());
    product.setSize(entity.getSize());
    product.setSpecialCharacteristicId(entity.getSpecialCharacteristicId());
    product.setUniqueIdNumber(entity.getUniqueIdNumber());
    product.setWeight(entity.getWeight());

    return product;
  }
}
