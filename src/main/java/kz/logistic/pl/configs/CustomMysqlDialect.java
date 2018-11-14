package kz.logistic.pl.configs;

import org.hibernate.dialect.MySQL57Dialect;

import java.sql.Types;

public class CustomMysqlDialect extends MySQL57Dialect {
  public CustomMysqlDialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
  }
}
