package kz.logistic.pl.configs;

import java.sql.Types;

import org.hibernate.dialect.MySQL57Dialect;

public class CustomMysqlDialect extends MySQL57Dialect {
  public CustomMysqlDialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
  }
}
