package kz.logistic.pl.configs;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class CustomPostgresDialect extends PostgreSQL95Dialect {
  public CustomPostgresDialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
  }
}
