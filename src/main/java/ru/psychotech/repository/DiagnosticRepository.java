package ru.psychotech.repository;

import static jooq_generated.Tables.DIAGNOSTIC;
import static jooq_generated.Tables.QUESTIONS;
import static jooq_generated.Tables.SCALES;

import jooq_generated.tables.pojos.Diagnostic;
import jooq_generated.tables.pojos.Scales;
import jooq_generated.tables.pojos.Questions;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiagnosticRepository {
  private final DSLContext dslContext;

  public Optional<Diagnostic> getCommonDescription(Long id) {
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

  public void saveResult(Long clientId, Long daignosticId, Map<Long, Integer> result) {

  }
}
