package kz.logistic.pl.soap.seller_category;

import kz.logistic.pl.models.entities.SellerCategoryEntity;
import kz.logistic.pl.repositories.SellerCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.logistic.seller_category.SellerCategory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SellerCategoryRepositorySoap {
  private static final Map<Long, SellerCategory> sellerMap = new HashMap<>();

  private SellerCategoryRepository sellerCategoryRepository;

  @Autowired(required = false)
  public void setSellerCategoryRepository(SellerCategoryRepository sellerCategoryRepository) {
    this.sellerCategoryRepository = sellerCategoryRepository;
  }

  @PostConstruct
  public void initData() {
    List<SellerCategoryEntity> entities = this.sellerCategoryRepository.findAll();
    entities.forEach(sellerCategoryEntity -> {
      SellerCategory sellerCategory = convertToSellerCategory(sellerCategoryEntity);

      sellerMap.put(sellerCategory.getSellerCategoryId(), sellerCategory);
    });
  }

  public SellerCategory findSellerCategoryId(Long id) {
    return sellerMap.get(id);
  }

  public SellerCategory addSellerCategory(String addInfo, String categoryNameEn, String categoryNameRu, String categoryNameKk) {
    SellerCategoryEntity sellerCategoryEntity = new SellerCategoryEntity();
    sellerCategoryEntity.setAddInfo(addInfo);
    sellerCategoryEntity.setCategoryNameEn(categoryNameEn);
    sellerCategoryEntity.setCategoryNameRu(categoryNameRu);
    sellerCategoryEntity.setCategoryNameKk(categoryNameKk);

    sellerCategoryRepository.save(sellerCategoryEntity);

    SellerCategory sellerCategory = convertToSellerCategory(sellerCategoryEntity);
    sellerMap.put(sellerCategory.getSellerCategoryId(), sellerCategory);

    return sellerCategory;
  }

  public SellerCategory updateSellerCategory(Long id, String addInfo, String categoryNameEn, String categoryNameRu,
                                             String categoryNameKk) {
    SellerCategory sellerCategory = sellerMap.get(id);
    sellerCategory.setAddInfo(addInfo);
    sellerCategory.setCategoryNameEn(categoryNameEn);
    sellerCategory.setCategoryNameRu(categoryNameRu);
    sellerCategory.setCategoryNameKk(categoryNameKk);

    sellerCategoryRepository.updateSellerCategoryById(id, addInfo, categoryNameEn, categoryNameRu, categoryNameKk);
    return sellerCategory;
  }

  public String deleteSellerCategory(Long id) {
    SellerCategoryEntity sellerCategoryEntity = this.sellerCategoryRepository.findById(id).orElse(null);

    if (sellerCategoryEntity != null) {
      this.sellerCategoryRepository.deleteById(id);
      return "id " + id + " удалён";
    } else {
      return "id " + id + " не удалён";
    }
  }

  private SellerCategory convertToSellerCategory(SellerCategoryEntity entity) {
    SellerCategory sellerCategory = new SellerCategory();
    sellerCategory.setAddInfo(entity.getAddInfo());
    sellerCategory.setCategoryNameEn(entity.getCategoryNameEn());
    sellerCategory.setCategoryNameRu(entity.getCategoryNameRu());
    sellerCategory.setCategoryNameKk(entity.getCategoryNameKk());
    sellerCategory.setSellerCategoryId(entity.getSellerCategoryId());

    return sellerCategory;
  }
}
