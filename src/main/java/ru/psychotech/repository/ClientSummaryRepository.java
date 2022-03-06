package ru.psychotech.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.TableField;
import org.springframework.stereotype.Repository;
import jooq_generated.tables.pojos.ClientsSummary;
import jooq_generated.tables.records.ClientsSummaryRecord;

import java.util.Optional;

import static jooq_generated.tables.ClientsSummary.CLIENTS_SUMMARY;

@Repository
@RequiredArgsConstructor
public class ClientSummaryRepository {
  private final DSLContext dslContext;

  public Optional<ClientsSummary> getClientSummary(Long id) {
    return this.dslContext.selectFrom(CLIENTS_SUMMARY)
        .where(CLIENTS_SUMMARY.CLIENT_ID.eq(id))
        .fetchOptional()
        .map(r -> r.into(ClientsSummary.class));
  }

  public void setSummary(Long clientId, Long diagnosticId, Integer value) {
    TableField<ClientsSummaryRecord, Integer> targetColumn = null;

    if (mapColumn(diagnosticId).equals("energyLevel")) targetColumn = CLIENTS_SUMMARY.ENERGY_LEVEL;

    this.dslContext.update(CLIENTS_SUMMARY)
        .set(targetColumn, value)
        .where(CLIENTS_SUMMARY.CLIENT_ID.eq(clientId))
        .execute();
  }

  public void clearSummary(Long clientId, Long diagnosticId) {
    this.setSummary(clientId, diagnosticId, 0);
  }

  private String mapColumn(Long diagnosticId) {
    switch (diagnosticId.intValue()) {
      case 1:
        return "energyLevel";
      default:
        return "";
    }
  }
}
