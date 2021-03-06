/*
 * This file is generated by jOOQ.
 */
package jooq_generated;


import jooq_generated.tables.Clients;
import jooq_generated.tables.Diagnostic;
import jooq_generated.tables.DiagnosticResults;
import jooq_generated.tables.Questions;
import jooq_generated.tables.Recommendations;
import jooq_generated.tables.Scales;
import jooq_generated.tables.records.ClientsRecord;
import jooq_generated.tables.records.DiagnosticRecord;
import jooq_generated.tables.records.DiagnosticResultsRecord;
import jooq_generated.tables.records.QuestionsRecord;
import jooq_generated.tables.records.RecommendationsRecord;
import jooq_generated.tables.records.ScalesRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ClientsRecord> PK_USERS = Internal.createUniqueKey(Clients.CLIENTS, DSL.name("pk_users"), new TableField[] { Clients.CLIENTS.ID }, true);
    public static final UniqueKey<DiagnosticRecord> PK_DIAGNOSTIC = Internal.createUniqueKey(Diagnostic.DIAGNOSTIC, DSL.name("pk_diagnostic"), new TableField[] { Diagnostic.DIAGNOSTIC.ID }, true);
    public static final UniqueKey<DiagnosticResultsRecord> PK_DIAGNOSTIC_RESULTS = Internal.createUniqueKey(DiagnosticResults.DIAGNOSTIC_RESULTS, DSL.name("pk_diagnostic_results"), new TableField[] { DiagnosticResults.DIAGNOSTIC_RESULTS.ID }, true);
    public static final UniqueKey<QuestionsRecord> PK_QUESTIONS = Internal.createUniqueKey(Questions.QUESTIONS, DSL.name("pk_questions"), new TableField[] { Questions.QUESTIONS.ID }, true);
    public static final UniqueKey<RecommendationsRecord> PK_RECOMMENDATIONS = Internal.createUniqueKey(Recommendations.RECOMMENDATIONS, DSL.name("pk_recommendations"), new TableField[] { Recommendations.RECOMMENDATIONS.ID }, true);
    public static final UniqueKey<ScalesRecord> PK_SCALES = Internal.createUniqueKey(Scales.SCALES, DSL.name("pk_scales"), new TableField[] { Scales.SCALES.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<DiagnosticResultsRecord, ClientsRecord> DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_CLIENT_ID_FKEY = Internal.createForeignKey(DiagnosticResults.DIAGNOSTIC_RESULTS, DSL.name("diagnostic_results_client_id_fkey"), new TableField[] { DiagnosticResults.DIAGNOSTIC_RESULTS.CLIENT_ID }, Keys.PK_USERS, new TableField[] { Clients.CLIENTS.ID }, true);
    public static final ForeignKey<DiagnosticResultsRecord, DiagnosticRecord> DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_DIAGNOSTIC_ID_FKEY = Internal.createForeignKey(DiagnosticResults.DIAGNOSTIC_RESULTS, DSL.name("diagnostic_results_diagnostic_id_fkey"), new TableField[] { DiagnosticResults.DIAGNOSTIC_RESULTS.DIAGNOSTIC_ID }, Keys.PK_DIAGNOSTIC, new TableField[] { Diagnostic.DIAGNOSTIC.ID }, true);
    public static final ForeignKey<DiagnosticResultsRecord, ScalesRecord> DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_SCALE_ID_FKEY = Internal.createForeignKey(DiagnosticResults.DIAGNOSTIC_RESULTS, DSL.name("diagnostic_results_scale_id_fkey"), new TableField[] { DiagnosticResults.DIAGNOSTIC_RESULTS.SCALE_ID }, Keys.PK_SCALES, new TableField[] { Scales.SCALES.ID }, true);
    public static final ForeignKey<QuestionsRecord, DiagnosticRecord> QUESTIONS__QUESTIONS_DIAGNOSTIC_ID_FKEY = Internal.createForeignKey(Questions.QUESTIONS, DSL.name("questions_diagnostic_id_fkey"), new TableField[] { Questions.QUESTIONS.DIAGNOSTIC_ID }, Keys.PK_DIAGNOSTIC, new TableField[] { Diagnostic.DIAGNOSTIC.ID }, true);
    public static final ForeignKey<RecommendationsRecord, DiagnosticRecord> RECOMMENDATIONS__RECOMMENDATIONS_DIAGNOSTIC_ID_FKEY = Internal.createForeignKey(Recommendations.RECOMMENDATIONS, DSL.name("recommendations_diagnostic_id_fkey"), new TableField[] { Recommendations.RECOMMENDATIONS.DIAGNOSTIC_ID }, Keys.PK_DIAGNOSTIC, new TableField[] { Diagnostic.DIAGNOSTIC.ID }, true);
    public static final ForeignKey<ScalesRecord, DiagnosticRecord> SCALES__SCALES_DIAGNOSTIC_ID_FKEY = Internal.createForeignKey(Scales.SCALES, DSL.name("scales_diagnostic_id_fkey"), new TableField[] { Scales.SCALES.DIAGNOSTIC_ID }, Keys.PK_DIAGNOSTIC, new TableField[] { Diagnostic.DIAGNOSTIC.ID }, true);
}
