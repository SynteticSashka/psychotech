/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables;


import java.util.Arrays;
import java.util.List;

import jooq_generated.Keys;
import jooq_generated.Public;
import jooq_generated.tables.records.DiagnosticRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * The table <code>public.diagnostic</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Diagnostic extends TableImpl<DiagnosticRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.diagnostic</code>
     */
    public static final Diagnostic DIAGNOSTIC = new Diagnostic();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DiagnosticRecord> getRecordType() {
        return DiagnosticRecord.class;
    }

    /**
     * The column <code>public.diagnostic.id</code>.
     */
    public final TableField<DiagnosticRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.diagnostic.name</code>.
     */
    public final TableField<DiagnosticRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.diagnostic.description</code>.
     */
    public final TableField<DiagnosticRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.diagnostic.type</code>.
     */
    public final TableField<DiagnosticRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR, this, "");

    private Diagnostic(Name alias, Table<DiagnosticRecord> aliased) {
        this(alias, aliased, null);
    }

    private Diagnostic(Name alias, Table<DiagnosticRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.diagnostic</code> table reference
     */
    public Diagnostic(String alias) {
        this(DSL.name(alias), DIAGNOSTIC);
    }

    /**
     * Create an aliased <code>public.diagnostic</code> table reference
     */
    public Diagnostic(Name alias) {
        this(alias, DIAGNOSTIC);
    }

    /**
     * Create a <code>public.diagnostic</code> table reference
     */
    public Diagnostic() {
        this(DSL.name("diagnostic"), null);
    }

    public <O extends Record> Diagnostic(Table<O> child, ForeignKey<O, DiagnosticRecord> key) {
        super(child, key, DIAGNOSTIC);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<DiagnosticRecord, Long> getIdentity() {
        return (Identity<DiagnosticRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<DiagnosticRecord> getPrimaryKey() {
        return Keys.PK_DIAGNOSTIC;
    }

    @Override
    public List<UniqueKey<DiagnosticRecord>> getKeys() {
        return Arrays.<UniqueKey<DiagnosticRecord>>asList(Keys.PK_DIAGNOSTIC);
    }

    @Override
    public Diagnostic as(String alias) {
        return new Diagnostic(DSL.name(alias), this);
    }

    @Override
    public Diagnostic as(Name alias) {
        return new Diagnostic(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Diagnostic rename(String name) {
        return new Diagnostic(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Diagnostic rename(Name name) {
        return new Diagnostic(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
