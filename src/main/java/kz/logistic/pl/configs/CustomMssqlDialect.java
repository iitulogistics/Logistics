package kz.logistic.pl.configs;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2012Dialect;


public class CustomMssqlDialect extends SQLServer2012Dialect {
  public CustomMssqlDialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
  }
}
