/*
 * This file is generated by jOOQ.
 */
package jooq_generated;


import java.util.Arrays;
import java.util.List;

import jooq_generated.tables.Clients;
import jooq_generated.tables.Diagnostic;
import jooq_generated.tables.DiagnosticResults;
import jooq_generated.tables.Questions;
import jooq_generated.tables.Recommendations;
import jooq_generated.tables.Scales;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * The schema <code>public</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.clients</code>.
     */
    public final Clients CLIENTS = Clients.CLIENTS;

    /**
     * The table <code>public.diagnostic</code>.
     */
    public final Diagnostic DIAGNOSTIC = Diagnostic.DIAGNOSTIC;

    /**
     * The table <code>public.diagnostic_results</code>.
     */
    public final DiagnosticResults DIAGNOSTIC_RESULTS = DiagnosticResults.DIAGNOSTIC_RESULTS;

    /**
     * The table <code>public.questions</code>.
     */
    public final Questions QUESTIONS = Questions.QUESTIONS;

    /**
     * The table <code>public.recommendations</code>.
     */
    public final Recommendations RECOMMENDATIONS = Recommendations.RECOMMENDATIONS;

    /**
     * The table <code>public.scales</code>.
     */
    public final Scales SCALES = Scales.SCALES;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Clients.CLIENTS,
            Diagnostic.DIAGNOSTIC,
            DiagnosticResults.DIAGNOSTIC_RESULTS,
            Questions.QUESTIONS,
            Recommendations.RECOMMENDATIONS,
            Scales.SCALES);
    }
}
