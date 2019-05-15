package kz.logistic.pl.dao.impl;

import kz.logistic.pl.dao.ProductDAO;
import kz.logistic.pl.models.entities.ProductsEntity;
import kz.logistic.pl.models.pojos.json.ProductJson;
import kz.logistic.pl.poi.ReadExcelFile;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

public class ProductDAOImpl implements ProductDAO {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Value("${elasticsearch.index.name}")
  private String indexName;

  @Value("${elasticsearch.name.type}")
  private String nameType;

  @Autowired
  ElasticsearchTemplate esTemplate;

  @Override
  public List<ProductsEntity> getAllProduct() {
    SearchQuery query = new NativeSearchQueryBuilder()
      .withQuery(matchAllQuery()).build();
    return esTemplate.queryForList(query, ProductsEntity.class);
  }

  @Override
  public ProductsEntity getProductById(Long id) {
    SearchQuery query = new NativeSearchQueryBuilder()
      .withFilter(QueryBuilders.matchQuery("productId", id)).build();
    List<ProductsEntity> list = esTemplate.queryForList(query, ProductsEntity.class);
    if(!list.isEmpty()){
      return list.get(0);
    }
    return null;
  }

  @Override
  public ProductsEntity addNewProduct(ProductsEntity product) {
    IndexQuery index = new IndexQuery();

    index.setIndexName(indexName);
    index.setType(nameType);
    index.setObject(product);

    LOG.info("Product indexed: {}", esTemplate.index(index));
    esTemplate.refresh(indexName);
    return product;
  }

  @Override
  public void addProductExcel(MultipartFile multipartFile) {
    ReadExcelFile excelFile = new ReadExcelFile();
    List<ProductsEntity> list = new ArrayList<>();
    try {
      list = excelFile.readProductFromExcelFile(multipartFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for(ProductsEntity entity : list){
      addNewProduct(entity);
    }
  }

  @Override
  public List<ProductsEntity> getProductByName(String name) {
    if(name == null) return null;
    SearchQuery query = new NativeSearchQueryBuilder()
      .withFilter(QueryBuilders.matchQuery("productNameKk", name))
      .withFilter(QueryBuilders.matchQuery("productNameRu", name))
      .withFilter(QueryBuilders.matchQuery("productNameEn", name))
      .build();
    return esTemplate.queryForList(query, ProductsEntity.class);
  }

  @Override
  public void addProduct(String productNameKk, String productNameRu, String productNameEn, Long productCategoryId, Long productSubcategoryId, String uniqueIdNumber, String serialNumber, String manufacturer, String size, Integer weight, Integer price, String productDescription, Long sellerCompanyId, Long specialCharacteristicsId) {
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
    addNewProduct(productsEntity);
  }

  @Override
  public void addProductJson(ProductJson productJson) {
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
    addNewProduct(productsEntity);
  }
}
