package ru.psychotech.repository;

import static jooq_generated.Tables.DIAGNOSTIC;
import static jooq_generated.Tables.QUESTIONS;
import static jooq_generated.Tables.DIAGNOSTIC_RESULTS;
import static jooq_generated.Tables.RECOMMENDATIONS;
import static jooq_generated.Tables.SCALES;

import jooq_generated.tables.pojos.Diagnostic;
import jooq_generated.tables.pojos.Scales;
import jooq_generated.tables.pojos.DiagnosticResults;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DiagnosticRepository {
  private final DSLContext dslContext;

  public List<Diagnostic> getDiagnosticList() {
    return this.dslContext.selectFrom(DIAGNOSTIC)
        .fetch()
        .map(r -> r.into(Diagnostic.class));
  }

  public List<Long> getCompleted(Long clientId) {
    return this.dslContext.select(DIAGNOSTIC_RESULTS.DIAGNOSTIC_ID).from(DIAGNOSTIC_RESULTS)
        .where(DIAGNOSTIC_RESULTS.CLIENT_ID.eq(clientId))
        .fetch()
        .getValues(DIAGNOSTIC_RESULTS.DIAGNOSTIC_ID)
        .stream()
        .distinct()
        .collect(Collectors.toList());
  }

  public void clearResults(Long clientId, Long diagnosticId) {
    this.dslContext.deleteFrom(DIAGNOSTIC_RESULTS)
        .where(
            DIAGNOSTIC_RESULTS.CLIENT_ID.eq(clientId),
            DIAGNOSTIC_RESULTS.DIAGNOSTIC_ID.eq(diagnosticId)
        ).execute();
}

  public Optional<Diagnostic> getDiagnostic(Long id) {
    return this.dslContext.selectFrom(DIAGNOSTIC)
        .where(DIAGNOSTIC.ID.eq(id))
        .fetchOptional()
        .map(r -> r.into(Diagnostic.class));
  }

  public List<Scales> getScales(Long diagnosticId) {
    return this.dslContext.selectFrom(SCALES)
        .where(SCALES.DIAGNOSTIC_ID.eq(diagnosticId))
        .fetch()
        .map(r -> r.into(Scales.class));
  }

  public List<String> getQuestions(Long diagnosticId) {
    return this.dslContext.select(QUESTIONS.TEXT).from(QUESTIONS)
        .where(QUESTIONS.DIAGNOSTIC_ID.eq(diagnosticId))
        .fetch()
        .getValues(QUESTIONS.TEXT);
  }

  public List<String> getRecommendationsText(Long diagnosticId) {
    return this.dslContext.select(RECOMMENDATIONS.TEXT)
        .from(RECOMMENDATIONS)
        .where(RECOMMENDATIONS.DIAGNOSTIC_ID.eq(diagnosticId))
        .orderBy(RECOMMENDATIONS.ID)
        .fetch()
        .getValues(RECOMMENDATIONS.TEXT);
  }

  public List<DiagnosticResults> getResults(Long clientId, Long diagnosticId) {
    return this.dslContext.selectFrom(DIAGNOSTIC_RESULTS)
        .where(
            DIAGNOSTIC_RESULTS.DIAGNOSTIC_ID.eq(diagnosticId),
            DIAGNOSTIC_RESULTS.CLIENT_ID.eq(clientId)
            )
        .fetch()
        .map(r -> r.into(DiagnosticResults.class));
  }

  public void saveResult(Long clientId, Long diagnosticId, Map<Long, Integer> result) {
      for (Map.Entry<Long, Integer> entry: result.entrySet()) {
        this.dslContext.insertInto(DIAGNOSTIC_RESULTS)
            .columns(
                DIAGNOSTIC_RESULTS.CLIENT_ID,
                DIAGNOSTIC_RESULTS.DIAGNOSTIC_ID,
                DIAGNOSTIC_RESULTS.SCALE_ID,
                DIAGNOSTIC_RESULTS.RESULT
            ).values(
                clientId,
                diagnosticId,
                entry.getKey(),
                entry.getValue()
            ).execute();
      }
  }
}

