package kz.logistic.pl.services;


import java.io.IOException;
import java.util.Map;

public interface GosZakupBinService {

  Map<String, Object> showCompanyInformation(String bin) throws IOException;

}
