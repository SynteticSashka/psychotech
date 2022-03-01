/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.daos;


import java.util.List;

import jooq_generated.tables.Diagnostic;
import jooq_generated.tables.records.DiagnosticRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * The table <code>public.diagnostic</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DiagnosticDao extends DAOImpl<DiagnosticRecord, jooq_generated.tables.pojos.Diagnostic, Long> {

    /**
     * Create a new DiagnosticDao without any configuration
     */
    public DiagnosticDao() {
        super(Diagnostic.DIAGNOSTIC, jooq_generated.tables.pojos.Diagnostic.class);
    }

    /**
     * Create a new DiagnosticDao with an attached configuration
     */
    public DiagnosticDao(Configuration configuration) {
        super(Diagnostic.DIAGNOSTIC, jooq_generated.tables.pojos.Diagnostic.class, configuration);
    }

    @Override
    public Long getId(jooq_generated.tables.pojos.Diagnostic object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Diagnostic.DIAGNOSTIC.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchById(Long... values) {
        return fetch(Diagnostic.DIAGNOSTIC.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public jooq_generated.tables.pojos.Diagnostic fetchOneById(Long value) {
        return fetchOne(Diagnostic.DIAGNOSTIC.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Diagnostic.DIAGNOSTIC.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchByName(String... values) {
        return fetch(Diagnostic.DIAGNOSTIC.NAME, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Diagnostic.DIAGNOSTIC.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchByDescription(String... values) {
        return fetch(Diagnostic.DIAGNOSTIC.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchRangeOfType(String lowerInclusive, String upperInclusive) {
        return fetchRange(Diagnostic.DIAGNOSTIC.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Diagnostic> fetchByType(String... values) {
        return fetch(Diagnostic.DIAGNOSTIC.TYPE, values);
    }
}
