package kz.logistic.pl.services.impl;

import kz.logistic.pl.models.entities.SellerCategoryEntity;
import kz.logistic.pl.models.factories.LocalizedMessageBuilderFactory;
import kz.logistic.pl.models.pojos.SellerCategory;
import kz.logistic.pl.models.pojos.impl.DefaultSellerCategory;
import kz.logistic.pl.models.pojos.json.SellerCategoryJson;
import kz.logistic.pl.repositories.SellerCategoryRepository;
import kz.logistic.pl.services.SellerCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultSellerCategoryService implements SellerCategoryService {

  private SellerCategoryRepository sellerCategoryRepository;
  private LocalizedMessageBuilderFactory localizedMessageBuilderFactory;

  @Autowired(required = false)
  public void setSellerCategoryRepository(SellerCategoryRepository sellerCategoryRepository) {
    this.sellerCategoryRepository = sellerCategoryRepository;
  }

  @Autowired(required = false)
  public void setLocalizedMessageBuilderFactory(LocalizedMessageBuilderFactory localizedMessageBuilderFactory) {
    this.localizedMessageBuilderFactory = localizedMessageBuilderFactory;
  }

  @Override
  public List<SellerCategory> showAllSellerCategories() {
    List<SellerCategoryEntity> entities = this.sellerCategoryRepository.findAll();

    return entities.stream().map(sellerCategoryEntity -> DefaultSellerCategory.builder()
      .sellerCategoryId(sellerCategoryEntity.getSellerCategoryId())
      .sellerCategoryName(
        localizedMessageBuilderFactory.builder()
          .kk(sellerCategoryEntity.getCategoryNameKk())
          .ru(sellerCategoryEntity.getCategoryNameRu())
          .en(sellerCategoryEntity.getCategoryNameEn()).build())
      .addInfo(sellerCategoryEntity.getAddInfo()).build()).collect(Collectors.toList());
  }

  @Override
  public DefaultSellerCategory showSellerCategory(Long sellerCategoryId) {
    SellerCategoryEntity sellerCategoryEntity = this.sellerCategoryRepository.findById(sellerCategoryId).orElse(null);
    return DefaultSellerCategory.builder()
      .sellerCategoryId(sellerCategoryEntity.getSellerCategoryId())
      .sellerCategoryName(localizedMessageBuilderFactory.builder()
        .kk(sellerCategoryEntity.getCategoryNameKk())
        .ru(sellerCategoryEntity.getCategoryNameRu())
        .en(sellerCategoryEntity.getCategoryNameEn()).build())
      .addInfo(sellerCategoryEntity.getAddInfo()).build();

  }


  @Override
  public String addSellerCategory(String sellerCategoryNameKk, String sellerCategoryNameRu, String sellerCategoryNameEn, String addInfo) {
    SellerCategoryEntity sellerCategoryEntity = new SellerCategoryEntity();
    sellerCategoryEntity.setAddInfo(addInfo);
    sellerCategoryEntity.setCategoryNameKk(sellerCategoryNameKk);
    sellerCategoryEntity.setCategoryNameRu(sellerCategoryNameRu);
    sellerCategoryEntity.setCategoryNameEn(sellerCategoryNameEn);
    this.sellerCategoryRepository.save(sellerCategoryEntity);
    log.info("Added new seller category " + new Date());
    return "Категория продавца добавлена";
  }

  @Override
  public String addSellerCategoryJson(SellerCategoryJson sellerCategoryJson) {
    SellerCategoryEntity sellerCategoryEntity = new SellerCategoryEntity();
    sellerCategoryEntity.setAddInfo(sellerCategoryJson.getAddInfo());
    sellerCategoryEntity.setCategoryNameKk(sellerCategoryJson.getSellerCategoryNameKk());
    sellerCategoryEntity.setCategoryNameRu(sellerCategoryJson.getSellerCategoryNameRu());
    sellerCategoryEntity.setCategoryNameEn(sellerCategoryJson.getSellerCategoryNameEn());
    this.sellerCategoryRepository.save(sellerCategoryEntity);
    log.info("Added new seller category by JSON " + new Date());
    return "Категория продавца добавлена JSON";
  }

  @Override
  public String updateSellerCategory(Long sellerCategoryId, SellerCategoryJson sellerCategoryJson) {
    SellerCategoryEntity sellerCategoryEntity = this.sellerCategoryRepository.findById(sellerCategoryId).orElse(null);
    if (Objects.nonNull(sellerCategoryEntity)) {
      if (sellerCategoryJson.getAddInfo() != null) {
        sellerCategoryEntity.setAddInfo(sellerCategoryJson.getAddInfo());
      }
      if (sellerCategoryJson.getSellerCategoryNameKk() != null) {
        sellerCategoryEntity.setCategoryNameKk(sellerCategoryJson.getSellerCategoryNameKk());
      }
      if (sellerCategoryJson.getSellerCategoryNameRu() != null) {
        sellerCategoryEntity.setCategoryNameRu(sellerCategoryJson.getSellerCategoryNameRu());
      }
      if (sellerCategoryJson.getSellerCategoryNameEn() != null) {
        sellerCategoryJson.setSellerCategoryNameEn(sellerCategoryJson.getSellerCategoryNameEn());
      }
      this.sellerCategoryRepository.save(sellerCategoryEntity);
      return "Категория-продавца обновлена";
    } else {
      return "Категория-продавца с таким ID не существует";
    }
  }

  @Override
  public String deleteSellerCategory(Long sellerCategoryId) {
    SellerCategoryEntity sellerCategoryEntity = this.sellerCategoryRepository.findById(sellerCategoryId).orElse(null);
    this.sellerCategoryRepository.deleteById(sellerCategoryEntity.getSellerCategoryId());
    return "Данные категории-продавца удалены";
  }
}
