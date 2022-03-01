/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables;


import java.util.Arrays;
import java.util.List;

import jooq_generated.Keys;
import jooq_generated.Public;
import jooq_generated.tables.records.DiagnosticResultsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * The table <code>public.diagnostic_results</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DiagnosticResults extends TableImpl<DiagnosticResultsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.diagnostic_results</code>
     */
    public static final DiagnosticResults DIAGNOSTIC_RESULTS = new DiagnosticResults();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DiagnosticResultsRecord> getRecordType() {
        return DiagnosticResultsRecord.class;
    }

    /**
     * The column <code>public.diagnostic_results.id</code>.
     */
    public final TableField<DiagnosticResultsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.diagnostic_results.client_id</code>.
     */
    public final TableField<DiagnosticResultsRecord, Long> CLIENT_ID = createField(DSL.name("client_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.diagnostic_results.diagnostic_id</code>.
     */
    public final TableField<DiagnosticResultsRecord, Long> DIAGNOSTIC_ID = createField(DSL.name("diagnostic_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.diagnostic_results.scale_id</code>.
     */
    public final TableField<DiagnosticResultsRecord, Long> SCALE_ID = createField(DSL.name("scale_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.diagnostic_results.result</code>.
     */
    public final TableField<DiagnosticResultsRecord, Integer> RESULT = createField(DSL.name("result"), SQLDataType.INTEGER.nullable(false), this, "");

    private DiagnosticResults(Name alias, Table<DiagnosticResultsRecord> aliased) {
        this(alias, aliased, null);
    }

    private DiagnosticResults(Name alias, Table<DiagnosticResultsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.diagnostic_results</code> table reference
     */
    public DiagnosticResults(String alias) {
        this(DSL.name(alias), DIAGNOSTIC_RESULTS);
    }

    /**
     * Create an aliased <code>public.diagnostic_results</code> table reference
     */
    public DiagnosticResults(Name alias) {
        this(alias, DIAGNOSTIC_RESULTS);
    }

    /**
     * Create a <code>public.diagnostic_results</code> table reference
     */
    public DiagnosticResults() {
        this(DSL.name("diagnostic_results"), null);
    }

    public <O extends Record> DiagnosticResults(Table<O> child, ForeignKey<O, DiagnosticResultsRecord> key) {
        super(child, key, DIAGNOSTIC_RESULTS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<DiagnosticResultsRecord, Long> getIdentity() {
        return (Identity<DiagnosticResultsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<DiagnosticResultsRecord> getPrimaryKey() {
        return Keys.PK_DIAGNOSTIC_RESULTS;
    }

    @Override
    public List<UniqueKey<DiagnosticResultsRecord>> getKeys() {
        return Arrays.<UniqueKey<DiagnosticResultsRecord>>asList(Keys.PK_DIAGNOSTIC_RESULTS);
    }

    @Override
    public List<ForeignKey<DiagnosticResultsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DiagnosticResultsRecord, ?>>asList(Keys.DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_CLIENT_ID_FKEY, Keys.DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_DIAGNOSTIC_ID_FKEY, Keys.DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_SCALE_ID_FKEY);
    }

    private transient Clients _clients;
    private transient Diagnostic _diagnostic;
    private transient Scales _scales;

    public Clients clients() {
        if (_clients == null)
            _clients = new Clients(this, Keys.DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_CLIENT_ID_FKEY);

        return _clients;
    }

    public Diagnostic diagnostic() {
        if (_diagnostic == null)
            _diagnostic = new Diagnostic(this, Keys.DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_DIAGNOSTIC_ID_FKEY);

        return _diagnostic;
    }

    public Scales scales() {
        if (_scales == null)
            _scales = new Scales(this, Keys.DIAGNOSTIC_RESULTS__DIAGNOSTIC_RESULTS_SCALE_ID_FKEY);

        return _scales;
    }

    @Override
    public DiagnosticResults as(String alias) {
        return new DiagnosticResults(DSL.name(alias), this);
    }

    @Override
    public DiagnosticResults as(Name alias) {
        return new DiagnosticResults(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DiagnosticResults rename(String name) {
        return new DiagnosticResults(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DiagnosticResults rename(Name name) {
        return new DiagnosticResults(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Long, Long, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
