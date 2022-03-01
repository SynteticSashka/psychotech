package ru.psychotech.repository;

import static jooq_generated.tables.Clients.CLIENTS;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.psychotech.model.Client;
import ru.psychotech.model.client.EditClient;
import ru.psychotech.model.client.NewClient;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository {
  private final DSLContext dslContext;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public List<Client> getList() {
    return dslContext.selectFrom(CLIENTS)
        .where(CLIENTS.DELETED.eq(false))
        .fetch()
        .map(r -> r.into(Client.class));
  }

  public Optional<Client> update(Long id, EditClient client) {
    var current = this.findByEmail(client.getEmail());

    if (current.isPresent() && (!current.get().getId().equals(id))) {
      return Optional.empty();
    }

    client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));

    return this.dslContext.update(CLIENTS)
        .set(CLIENTS.NAME, client.getName())
        .set(CLIENTS.LASTNAME, client.getLastname())
        .set(CLIENTS.EMAIL, client.getEmail())
        .set(CLIENTS.PASSWORD, client.getPassword())
        .where(
            CLIENTS.ID.eq(id),
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

  public Optional<Client> findByEmail(String email) {
    return dslContext.selectFrom(CLIENTS)
        .where(
            CLIENTS.EMAIL.eq(email),
            CLIENTS.DELETED.eq(false)
        )
        .fetchOptional()
        .map(r -> r.into(Client.class));
  }

  public Optional<Client> create(NewClient client) {
    boolean isExist = dslContext.selectFrom(CLIENTS)
        .where(
            CLIENTS.EMAIL.eq(client.getEmail()),
            CLIENTS.DELETED.eq(false)
            )
        .fetchOptional().map(r -> r.into(Client.class)).isPresent();

    if (isExist) return Optional.empty();

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
        .returning()
        .fetchOptional()
        .map(r -> r.into(Client.class));
  }

  public void delete(Long id) {
    this.dslContext.update(CLIENTS)
        .set(CLIENTS.DELETED, true)
        .where(CLIENTS.ID.eq(id))
        .execute();
  }
}
