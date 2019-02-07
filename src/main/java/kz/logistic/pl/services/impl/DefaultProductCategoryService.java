package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.ProductsCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.ProductCategory;
import kz.logistic.pl.models.pojos.impl.DefaultProductCategory;
import kz.logistic.pl.models.pojos.json.ProductCategoryJson;
import kz.logistic.pl.repositories.ProductsCategoryRepository;
import kz.logistic.pl.services.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DefaultProductCategoryService implements ProductCategoryService {

    private ProductsCategoryRepository productsCategoryRepository;
    private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

    @Autowired(required = false)
    public void setProductsCategoryRepository(ProductsCategoryRepository productsCategoryRepository) {
        this.productsCategoryRepository = productsCategoryRepository;
    }

    @Autowired(required = false)
    public void setLocalizedMessageBuilderFactory(LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
        this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
    }

    @Override
    public List<ProductCategory> showAllProduct() {
        List<ProductsCategoryEntity> entities = this.productsCategoryRepository.findAll();

        return entities.stream().map(productsCategoryEntity -> DefaultProductCategory.builder()
                .id(productsCategoryEntity.getProductCategoryId())
                .categoryName(localizedMessageBuilderFactory.builder()
                        .kk(productsCategoryEntity.getCategoryNameKk())
                        .ru(productsCategoryEntity.getCategoryNameRu())
                        .en(productsCategoryEntity.getCategoryNameEn()).build())
                .addInfo(productsCategoryEntity.getAddInfo())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void addCategory(String categoryNameKk, String categoryNameRu, String categoryNameEn, String addInfo) {
        ProductsCategoryEntity categoryEntity = new ProductsCategoryEntity();
        categoryEntity.setCategoryNameKk(categoryNameKk);
        categoryEntity.setCategoryNameRu(categoryNameRu);
        categoryEntity.setCategoryNameEn(categoryNameEn);
        categoryEntity.setAddInfo(addInfo);

        this.productsCategoryRepository.save(categoryEntity);
        log.info("Added new ProductCategory " + categoryNameRu + " " + new Date());
    }

    @Override
    public void addCategoryJson(ProductCategoryJson productCategoryJson) {
        ProductsCategoryEntity categoryEntity = new ProductsCategoryEntity();
        categoryEntity.setCategoryNameKk(productCategoryJson.getCategoryNameKk());
        categoryEntity.setCategoryNameRu(productCategoryJson.getCategoryNameRu());
        categoryEntity.setCategoryNameEn(productCategoryJson.getCategoryNameEn());
        categoryEntity.setAddInfo(productCategoryJson.getAddInfo());

        this.productsCategoryRepository.save(categoryEntity);
        log.info("Added new ProductCategory " + productCategoryJson.getCategoryNameRu() + " via JSON " + new Date());
    }
}
