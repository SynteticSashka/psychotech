package ru.psychotech.config;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jooq_generated.tables.daos.ClientsDao;

@Configuration
public class DaoConfig {

  @Bean
  public ClientsDao clientsDao(DSLContext dslContext) {
    return new ClientsDao(dslContext.configuration());
  }
}
