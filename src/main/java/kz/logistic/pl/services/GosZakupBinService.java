package kz.logistic.pl.services;


import kz.logistic.pl.models.pojos.json.InfoBinJson;

import java.io.IOException;

public interface GosZakupBinService {

    InfoBinJson showCompanyInformation(String bin) throws IOException;

}
