package kz.logistic.pl.models.builders;

import kz.logistic.pl.models.pojos.LocalizedMessage;

public interface LocalizedMessageModelBuilder {

    LocalizedMessageModelBuilder ru(String message);

    LocalizedMessageModelBuilder kk(String message);

    LocalizedMessageModelBuilder en(String message);

    LocalizedMessageModelBuilder ruArgs(Object[] args);

    LocalizedMessageModelBuilder kkArgs(Object[] args);

    LocalizedMessageModelBuilder enArgs(Object[] args);

    LocalizedMessage build();

}
