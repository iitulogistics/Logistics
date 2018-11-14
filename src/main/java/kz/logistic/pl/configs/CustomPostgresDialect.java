package kz.logistic.pl.configs;


import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class CustomPostgresDialect extends PostgreSQL95Dialect {
  public CustomPostgresDialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
  }
}
