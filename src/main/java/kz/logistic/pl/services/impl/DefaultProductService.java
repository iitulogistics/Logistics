package kz.logistic.pl.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import kz.logistic.pl.models.entities.ProductsEntity;
import kz.logistic.pl.models.pojos.Product;
import kz.logistic.pl.models.pojos.impl.DefaultProduct;
import kz.logistic.pl.models.pojos.json.ProductJson;
import kz.logistic.pl.poi.ReadExcelFile;
import kz.logistic.pl.repositories.ProductRepository;
import kz.logistic.pl.services.ProductService;
import kz.logistic.pl.utils.ReturnMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Slf4j
public class DefaultProductService implements ProductService {

  @Value("${upload.path}")
  private String uploadDir;
  private ProductRepository productRepository;
  private ReturnMessage returnMessage;


  @Autowired(required = false)
  public void setReturnMessage(ReturnMessage returnMessage) {
    this.returnMessage = returnMessage;
  }

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public String addProduct(
    String productNameKk, String productNameRu, String productNameEn, Long productCategoryId,
    Long productSubcategoryId, String uniqueIdNumber, String serialNumber, String manufacturer,
    String size, Integer weight, Integer price, String productDescription, Long sellerCompanyId,
    Long specialCharacteristicsId) {
    ProductsEntity productsEntity = new ProductsEntity();
    productsEntity.setProductNameKk(productNameKk);
    productsEntity.setProductNameRu(productNameRu);
    productsEntity.setProductNameEn(productNameEn);
    productsEntity.setProductDescription(productDescription);
    productsEntity.setSellerCompanyId(sellerCompanyId);
    productsEntity.setSpecialCharacteristicId(specialCharacteristicsId);
    productsEntity.setProductSubcategoryId(productSubcategoryId);
    productsEntity.setProductCategoryId(productCategoryId);
    productsEntity.setManufacturer(manufacturer);
    productsEntity.setPrice(price);
    productsEntity.setSerialNumber(serialNumber);
    productsEntity.setSize(size);
    productsEntity.setUniqueIdNumber(uniqueIdNumber);
    productsEntity.setWeight(weight);
    productRepository.save(productsEntity);
    log.info("New product added:\nName: "
      + productNameEn + "\nseller company id: " + sellerCompanyId);
    return java.text.MessageFormat.format(returnMessage.getProductAddSuccess(), productsEntity.getProductId());
  }

  @Override
  public String addProductJson(ProductJson productJson) {
    ProductsEntity productsEntity = new ProductsEntity();
    productsEntity.setProductNameKk(productJson.getProductNameKk());
    productsEntity.setProductNameRu(productJson.getProductNameRu());
    productsEntity.setProductNameEn(productJson.getProductNameEn());
    productsEntity.setProductDescription(productJson.getProductDescription());
    productsEntity.setSellerCompanyId(productJson.getSellerCompanyId());
    productsEntity.setSpecialCharacteristicId(productJson.getSpecialCharacteristicId());
    productsEntity.setProductSubcategoryId(productJson.getProductSubcategoryId());
    productsEntity.setProductCategoryId(productJson.getProductCategoryId());
    productsEntity.setManufacturer(productJson.getManufacturer());
    productsEntity.setPrice(productJson.getPrice());
    productsEntity.setSerialNumber(productJson.getSerialNumber());
    productsEntity.setSize(productJson.getSize());
    productsEntity.setUniqueIdNumber(productJson.getUniqueIdNumber());
    productsEntity.setWeight(productJson.getWeight());
    productRepository.save(productsEntity);
    log.info("New product added:\nName: "
      + productJson.getProductNameEn() + "\nseller company id: "
      + productJson.getSellerCompanyId());
    return java.text.MessageFormat.format(returnMessage.getProductAddSuccess(), productsEntity.getProductId());
  }

  @Override
  public void addProductExcel(MultipartFile multipartFile) {
    ReadExcelFile readExcelFile = new ReadExcelFile();
    try {
      List<ProductsEntity> list = readExcelFile.readProductFromExcelFile(multipartFile);
      for (ProductsEntity productsEntity : list) {
        productRepository.save(productsEntity);
        log.info("New product added: " + productsEntity.getProductNameEn());
      }
    } catch (IOException e) {
      log.info("Error");
      e.printStackTrace();
    }
  }

  @Override
  public List<Product> showAllProducts() {
    List<ProductsEntity> productsEntityList = new ArrayList<>();
    Iterable<ProductsEntity> iterable = this.productRepository.findAll();
    iterable.forEach(productsEntityList::add);
    return getCollectProduct(productsEntityList);
  }

  private List<Product> getCollectProduct(List<ProductsEntity> productsEntityList) {
    return productsEntityList.stream().map(productsEntity ->
      getProduct(productsEntity))
      .collect(Collectors.toList());
  }

  @Override
  public List<Product> showProductBySeller(Long sellerCompanyId) {
    List<ProductsEntity> productsEntityList = new ArrayList<>();
    Iterable<ProductsEntity> iterable = this.productRepository.findBySellerCompanyId(sellerCompanyId);
    iterable.forEach(productsEntityList::add);
    return getCollectProduct(productsEntityList);
  }

  @Override
  public List<Product> showProductByCategoryId(Long productCategoryId) {
    List<ProductsEntity> productsEntityList = new ArrayList<>();
    Iterable<ProductsEntity> iterable = this.productRepository.findByProductCategoryId(productCategoryId);
    iterable.forEach(productsEntityList::add);
    return getCollectProduct(productsEntityList);
  }

  @Override
  public List<Product> showProductBySubCategoryId(Long productSubCategoryId) {
    List<ProductsEntity> productsEntityList = new ArrayList<>();
    Iterable<ProductsEntity> iterable = this.productRepository.findByProductSubcategoryId(productSubCategoryId);
    iterable.forEach(productsEntityList::add);
    return getCollectProduct(productsEntityList);
  }

  @Override
  public DefaultProduct showProduct(Long productId) {
    ProductsEntity productsEntity = this.productRepository.findById(productId).orElse(null);
    return getProduct(productsEntity);
  }

  @Override
  public String updateProduct(Long productId, ProductJson productJson) {
    ProductsEntity productsEntity = this.productRepository.findById(productId).orElse(null);
    if (Objects.nonNull(productsEntity)) {
      if (productJson.getProductNameEn() != null) {
        productsEntity.setProductNameEn(productJson.getProductNameEn());
      }
      if (productJson.getProductNameKk() != null) {
        productsEntity.setProductNameKk(productJson.getProductNameKk());
      }
      if (productJson.getProductNameRu() != null) {
        productsEntity.setProductNameRu(productJson.getProductNameRu());
      }
      if (productJson.getProductDescription() != null) {
        productsEntity.setProductDescription(productJson.getProductDescription());
      }
      if (productJson.getSellerCompanyId() != null) {
        productsEntity.setSellerCompanyId(productJson.getSellerCompanyId());
      }
      if (productJson.getManufacturer() != null) {
        productsEntity.setManufacturer(productJson.getManufacturer());
      }
      if (productJson.getPrice() != null) {
        productsEntity.setPrice(productJson.getPrice());
      }
      if (productJson.getProductCategoryId() != null) {
        productsEntity.setProductCategoryId(productJson.getProductCategoryId());
      }
      if (productJson.getProductSubcategoryId() != null) {
        productsEntity.setProductSubcategoryId(productJson.getProductSubcategoryId());
      }
      if (productJson.getSerialNumber() != null) {
        productsEntity.setSerialNumber(productJson.getSerialNumber());
      }
      if (productJson.getSize() != null) {
        productsEntity.setSize(productJson.getSize());
      }
      if (productJson.getWeight() != null) {
        productsEntity.setWeight(productJson.getWeight());
      }
      if (productJson.getUniqueIdNumber() != null) {
        productsEntity.setUniqueIdNumber(productJson.getUniqueIdNumber());
      }
      if (productJson.getSpecialCharacteristicId() != null) {
        productsEntity.setSpecialCharacteristicId(productJson.getSpecialCharacteristicId());
      }

      this.productRepository.save(productsEntity);
      log.info("Updated " + productsEntity.getProductNameEn() + " product" + new Date());
      return java.text.MessageFormat.format(returnMessage.getProductUpdateSuccess(), productsEntity.getProductNameEn());
    } else {
      return java.text.MessageFormat.format(returnMessage.getProductUpdateError(), productsEntity.getProductNameEn());
    }
  }

  @Override
  public String addPhoto(Long id, MultipartFile file) {
    ProductsEntity productsEntity = this.productRepository.getOne(id);
    if (productsEntity == null)
      return returnMessage.getProductUpdateError();
    try {
      String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
      File transferFile = new File(uploadDir + filename);
      file.transferTo(transferFile);

      String photosUrlList = productsEntity.getProductsImg();
      if (photosUrlList == null)
        photosUrlList = "";
      if (photosUrlList.equals(""))
        productsEntity.setProductsImg(filename);
      else
        productsEntity.setProductsImg(photosUrlList + "," + filename);
      this.productRepository.save(productsEntity);
      return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/product/uploads/")
        .path(filename)
        .toUriString();
    } catch (IOException e) {
      return "Ошибка";
    }
  }

  @Override
  public byte[] getPhoto(String name) throws IOException {
    File file = new File(uploadDir + name);
    FileInputStream fileInputStream = new FileInputStream(file);
    return IOUtils.toByteArray(fileInputStream);
  }

  @Override
  public List<Product> getProductsByName(String name) {
    List<ProductsEntity> productsEntityList = productRepository.getProductsByName(name);

    return productsEntityList.stream().map(productsEntity ->
      getProduct(productsEntity)
    ).collect(Collectors.toList());
  }

  private DefaultProduct getProduct(ProductsEntity productsEntity) {
    return DefaultProduct.builder()
      .productId(productsEntity.getProductId())
      .productNameKk(productsEntity.getProductNameKk())
      .productNameRu(productsEntity.getProductNameRu())
      .productNameEn(productsEntity.getProductNameEn())
      .productDescription(productsEntity.getProductDescription())
      .sellerCompanyId(productsEntity.getSellerCompanyId())
      .manufacturer(productsEntity.getManufacturer())
      .price(productsEntity.getPrice())
      .productCategoryId(productsEntity.getProductCategoryId())
      .productSubcategoryId(productsEntity.getProductSubcategoryId())
      .size(productsEntity.getSize())
      .weight(productsEntity.getWeight())
      .photoUrlsList(productsEntity.getProductsImg())
      .specialCharacteristicsId(productsEntity.getSpecialCharacteristicId())
      .serialNumber(productsEntity.getSerialNumber())
      .uniqueIdNumber(productsEntity.getUniqueIdNumber()).build();
  }

  @Override
  public List<Product> getProductsByCategoryId(Long id) {
    List<ProductsEntity> productsEntityList = productRepository.getProductsByCategoryId(id);

    return productsEntityList.stream().map(productsEntity -> getProduct(productsEntity)).collect(Collectors.toList());
  }

  @Override
  public List<Product> getProductsByIds(List<Long> ids) {
    List<Product> products = new ArrayList<>();
    for (Long id : ids) {
      products.add(getProduct(productRepository.getOne(id)));
    }
    return products;
  }

  @Override
  public String deleteProduct(Long productId) {
    ProductsEntity productsEntity = this.productRepository.findById(productId).orElse(null);
    if (Objects.nonNull(productsEntity)) {
      log.info("Deleted " + productsEntity.getProductNameEn() + " city" + new Date());
      this.productRepository.deleteById(productsEntity.getProductId());
      return "Продукт удален";
    } else {
      return "Продукт с таким id не существует";
    }
  }

}