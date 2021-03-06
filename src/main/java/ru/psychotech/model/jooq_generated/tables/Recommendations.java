/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables;


import java.util.Arrays;
import java.util.List;

import jooq_generated.Keys;
import jooq_generated.Public;
import jooq_generated.tables.records.RecommendationsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * The table <code>public.recommendations</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Recommendations extends TableImpl<RecommendationsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.recommendations</code>
     */
    public static final Recommendations RECOMMENDATIONS = new Recommendations();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RecommendationsRecord> getRecordType() {
        return RecommendationsRecord.class;
    }

    /**
     * The column <code>public.recommendations.id</code>.
     */
    public final TableField<RecommendationsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.recommendations.diagnostic_id</code>.
     */
    public final TableField<RecommendationsRecord, Long> DIAGNOSTIC_ID = createField(DSL.name("diagnostic_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.recommendations.text</code>.
     */
    public final TableField<RecommendationsRecord, String> TEXT = createField(DSL.name("text"), SQLDataType.VARCHAR, this, "");

    private Recommendations(Name alias, Table<RecommendationsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Recommendations(Name alias, Table<RecommendationsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.recommendations</code> table reference
     */
    public Recommendations(String alias) {
        this(DSL.name(alias), RECOMMENDATIONS);
    }

    /**
     * Create an aliased <code>public.recommendations</code> table reference
     */
    public Recommendations(Name alias) {
        this(alias, RECOMMENDATIONS);
    }

    /**
     * Create a <code>public.recommendations</code> table reference
     */
    public Recommendations() {
        this(DSL.name("recommendations"), null);
    }

    public <O extends Record> Recommendations(Table<O> child, ForeignKey<O, RecommendationsRecord> key) {
        super(child, key, RECOMMENDATIONS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<RecommendationsRecord, Long> getIdentity() {
        return (Identity<RecommendationsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<RecommendationsRecord> getPrimaryKey() {
        return Keys.PK_RECOMMENDATIONS;
    }

    @Override
    public List<UniqueKey<RecommendationsRecord>> getKeys() {
        return Arrays.<UniqueKey<RecommendationsRecord>>asList(Keys.PK_RECOMMENDATIONS);
    }

    @Override
    public List<ForeignKey<RecommendationsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RecommendationsRecord, ?>>asList(Keys.RECOMMENDATIONS__RECOMMENDATIONS_DIAGNOSTIC_ID_FKEY);
    }

    private transient Diagnostic _diagnostic;

    public Diagnostic diagnostic() {
        if (_diagnostic == null)
            _diagnostic = new Diagnostic(this, Keys.RECOMMENDATIONS__RECOMMENDATIONS_DIAGNOSTIC_ID_FKEY);

        return _diagnostic;
    }

    @Override
    public Recommendations as(String alias) {
        return new Recommendations(DSL.name(alias), this);
    }

    @Override
    public Recommendations as(Name alias) {
        return new Recommendations(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Recommendations rename(String name) {
        return new Recommendations(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Recommendations rename(Name name) {
        return new Recommendations(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
