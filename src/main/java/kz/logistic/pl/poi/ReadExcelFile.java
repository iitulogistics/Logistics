package kz.logistic.pl.poi;

import kz.logistic.pl.models.entities.ProductsEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadExcelFile {

  public List<ProductsEntity> readProductFromExcelFile(MultipartFile multipartFile) throws IOException {

    FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();

    Workbook workbook = null;

    String fileExtensionName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."));
    if (fileExtensionName.equals(".xlsx")) {
      workbook = new XSSFWorkbook(fileInputStream);
    } else if (fileExtensionName.equals(".xls")) {
      workbook = new HSSFWorkbook(fileInputStream);

    }
    Sheet sheet = workbook.getSheet("Sheet1");

    List<ProductsEntity> list = new ArrayList<>();

    for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {

      Row row = sheet.getRow(i);

      String product_name_kk = row.getCell(0).getStringCellValue(),
        product_name_ru = row.getCell(1).getStringCellValue(),
        product_name_en = row.getCell(2).getStringCellValue(),
        unique_id_number = row.getCell(4).getStringCellValue(),
        serial_namber = row.getCell(5).getStringCellValue(),
        manufacturer = row.getCell(6).getStringCellValue(),
        size = row.getCell(7).getStringCellValue(),
        product_description = row.getCell(10).getStringCellValue(),
        product_img = row.getCell(14).getStringCellValue();
      long product_subcategory_id = (long) row.getCell(3).getNumericCellValue(),
        seller_company_id = (int) row.getCell(11).getNumericCellValue(),
        special_characteristic_id = (int) row.getCell(12).getNumericCellValue(),
        product_category_id = (int) row.getCell(13).getNumericCellValue();
      int weight = (int) row.getCell(8).getNumericCellValue(),
        price = (int) row.getCell(9).getNumericCellValue();

      ProductsEntity productsEntity = new ProductsEntity();
      productsEntity.setProductNameEn(product_name_en);
      productsEntity.setProductNameRu(product_name_ru);
      productsEntity.setProductNameKk(product_name_kk);
      productsEntity.setUniqueIdNumber(unique_id_number);
      productsEntity.setSerialNumber(serial_namber);
      productsEntity.setSize(size);
      productsEntity.setProductDescription(product_description);
      productsEntity.setProductsImg(product_img);
      productsEntity.setManufacturer(manufacturer);
      productsEntity.setProductSubcategoryId(product_subcategory_id);
      productsEntity.setWeight(weight);
      productsEntity.setPrice(price);
      productsEntity.setSellerCompanyId(seller_company_id);
      productsEntity.setSpecialCharacteristicId(special_characteristic_id);
      productsEntity.setProductCategoryId(product_category_id);

      list.add(productsEntity);
    }

    return list;
  }
}
