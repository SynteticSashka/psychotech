package ru.psychotech.config;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

  @Bean
  public jooq_generated.tables.daos.ClientsDao clientsDao(DSLContext dslContext) {
    return new jooq_generated.tables.daos.ClientsDao(dslContext.configuration());
  }
}
