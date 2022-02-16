package ru.psychotech.repository;

import static jooq_generated.tables.Clients.CLIENTS;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.psychotech.model.Client;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository {
  private final DSLContext dslContext;
  private final jooq_generated.tables.daos.ClientsDao dao;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public List<Client> getList() {
    return dslContext.selectFrom(CLIENTS)
        .where(CLIENTS.DELETED.eq(false))
        .fetch()
        .map(r -> r.into(Client.class));
  }

  public Optional<Client> update(Client client) {
    return dslContext.update(CLIENTS)
        .set(dslContext.newRecord(CLIENTS, client))
        .where(
            CLIENTS.ID.eq(client.getId()),
            CLIENTS.DELETED.eq(false)
        )
        .returning()
        .fetchOptional()
        .map(r -> r.into(Client.class));
  }

  public Optional<Client> get(Long id) {
    return this.dslContext.selectFrom(CLIENTS)
        .where(
            CLIENTS.ID.eq(id),
            CLIENTS.DELETED.eq(false)
        )
        .fetchOptional()
        .map(r -> r.into(Client.class));
  }

  public Client findByEmail(String email) throws UsernameNotFoundException {
    return dslContext.selectFrom(CLIENTS)
        .where(
            CLIENTS.EMAIL.eq(email),
            CLIENTS.DELETED.eq(false)
        )
        .fetchOptional()
        .map(r -> r.into(Client.class))
        .orElseThrow(() -> new UsernameNotFoundException("User"));
  }

  public Long create(Client client) {
    // перед добавляем проверяем, нет ли пользователя с такой почтой
    boolean isExist = dslContext.selectFrom(CLIENTS)
        .where(CLIENTS.EMAIL.eq(client.getEmail()))
        .fetchOptional().map(r -> r.into(Client.class)).isPresent();

    if (isExist) return -1L;

    client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));

    return dslContext.insertInto(CLIENTS)
        .columns(
            CLIENTS.NAME,
            CLIENTS.LASTNAME,
            CLIENTS.EMAIL,
            CLIENTS.PASSWORD
        )
        .values(
            client.getName(),
            client.getLastname(),
            client.getEmail(),
            client.getPassword())
        .returning(CLIENTS.ID)
        .fetchOptional()
        .orElseThrow(() -> new DataAccessException("Error inserting entity: " + client.getId()))
        .get(CLIENTS.ID);
  }

  public void delete(Long id) {
    this.dslContext.update(CLIENTS)
        .set(CLIENTS.DELETED, true)
        .where(CLIENTS.ID.eq(id))
        .execute();
  }

}
