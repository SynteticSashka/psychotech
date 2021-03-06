/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.records;


import jooq_generated.tables.Diagnostic;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * The table <code>public.diagnostic</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DiagnosticRecord extends UpdatableRecordImpl<DiagnosticRecord> implements Record4<Long, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.diagnostic.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.diagnostic.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.diagnostic.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.diagnostic.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.diagnostic.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.diagnostic.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.diagnostic.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.diagnostic.type</code>.
     */
    public String getType() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Diagnostic.DIAGNOSTIC.ID;
    }

    @Override
    public Field<String> field2() {
        return Diagnostic.DIAGNOSTIC.NAME;
    }

    @Override
    public Field<String> field3() {
        return Diagnostic.DIAGNOSTIC.DESCRIPTION;
    }

    @Override
    public Field<String> field4() {
        return Diagnostic.DIAGNOSTIC.TYPE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public String component4() {
        return getType();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public String value4() {
        return getType();
    }

    @Override
    public DiagnosticRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public DiagnosticRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public DiagnosticRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public DiagnosticRecord value4(String value) {
        setType(value);
        return this;
    }

    @Override
    public DiagnosticRecord values(Long value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DiagnosticRecord
     */
    public DiagnosticRecord() {
        super(Diagnostic.DIAGNOSTIC);
    }

    /**
     * Create a detached, initialised DiagnosticRecord
     */
    public DiagnosticRecord(Long id, String name, String description, String type) {
        super(Diagnostic.DIAGNOSTIC);

        setId(id);
        setName(name);
        setDescription(description);
        setType(type);
    }
}
