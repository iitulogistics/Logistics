package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.ProductsSubCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.ProductSubCategory;
import kz.logistic.pl.models.pojos.impl.DefaulProductSubCategory;
import kz.logistic.pl.models.pojos.json.ProductSubCategoryJson;
import kz.logistic.pl.repositories.ProductSubCategoryRepository;
import kz.logistic.pl.services.ProductSubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DefaultProductSubCategoryService implements ProductSubCategoryService {

    private ProductSubCategoryRepository productSubCategoryRepository;
    private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

    @Autowired(required = false)
    public void setProductSubCategoryRepository(ProductSubCategoryRepository productSubCategoryRepository) {
        this.productSubCategoryRepository = productSubCategoryRepository;
    }

    @Autowired(required = false)
    public void setLocalizedMessageBuilderFactory(LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
        this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
    }

    @Override
    public List<ProductSubCategory> showAllProductSubCategory() {
        List<ProductsSubCategoryEntity> entities = this.productSubCategoryRepository.findAll();

        return entities.stream().map(productsSubCategoryEntity -> DefaulProductSubCategory.builder()
                .id(productsSubCategoryEntity.getProductSubcategoryId())
                .subCategoryName(
                        localizedMessageBuilderFactory.builder()
                                .kk(productsSubCategoryEntity.getSubCategoryNameKk())
                                .ru(productsSubCategoryEntity.getSubCategoryNameRu())
                                .en(productsSubCategoryEntity.getSubCategoryNameEn()).build())
                .productCategoryId(productsSubCategoryEntity.getProductCategoryId())
                .subCategoryAddInfo(productsSubCategoryEntity.getSubCategoryAddInfo())
                .build()).collect(Collectors.toList());
    }


    @Override
    public void addProductSubCategory(String subCategoryNameKk, String subCategoryNameRu,
                                      String subCategoryNameEn, Long productCategoryId,
                                      String subCategoryAddInfo) {
        ProductsSubCategoryEntity subCategoryEntity = new ProductsSubCategoryEntity();
        subCategoryEntity.setSubCategoryNameKk(subCategoryNameKk);
        subCategoryEntity.setSubCategoryNameRu(subCategoryNameRu);
        subCategoryEntity.setSubCategoryNameEn(subCategoryNameEn);
        subCategoryEntity.setProductCategoryId(productCategoryId);
        subCategoryEntity.setSubCategoryAddInfo(subCategoryAddInfo);

        this.productSubCategoryRepository.save(subCategoryEntity);
        log.info("Added new ProductSubCategory: " + subCategoryNameRu + " " + new Date());
    }

    @Override
    public void addProductSubCategoryJson(ProductSubCategoryJson productSubCategoryJson) {
        ProductsSubCategoryEntity subCategoryEntity = new ProductsSubCategoryEntity();
        subCategoryEntity.setSubCategoryNameKk(productSubCategoryJson.getSubCategoryNameKk());
        subCategoryEntity.setSubCategoryNameRu(productSubCategoryJson.getSubCategoryNameRu());
        subCategoryEntity.setSubCategoryNameEn(productSubCategoryJson.getSubCategoryNameEn());
        subCategoryEntity.setProductCategoryId(productSubCategoryJson.getProductCategoryId());
        subCategoryEntity.setSubCategoryAddInfo(productSubCategoryJson.getSubCategoryAddInfo());

        this.productSubCategoryRepository.save(subCategoryEntity);
        log.info("Added new ProductSubCategory: " + productSubCategoryJson.getSubCategoryNameRu() + " via Json " + new Date());
    }
}
