/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.daos;


import java.util.List;

import jooq_generated.tables.Recommendations;
import jooq_generated.tables.records.RecommendationsRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * The table <code>public.recommendations</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RecommendationsDao extends DAOImpl<RecommendationsRecord, jooq_generated.tables.pojos.Recommendations, Long> {

    /**
     * Create a new RecommendationsDao without any configuration
     */
    public RecommendationsDao() {
        super(Recommendations.RECOMMENDATIONS, jooq_generated.tables.pojos.Recommendations.class);
    }

    /**
     * Create a new RecommendationsDao with an attached configuration
     */
    public RecommendationsDao(Configuration configuration) {
        super(Recommendations.RECOMMENDATIONS, jooq_generated.tables.pojos.Recommendations.class, configuration);
    }

    @Override
    public Long getId(jooq_generated.tables.pojos.Recommendations object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Recommendations> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Recommendations.RECOMMENDATIONS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Recommendations> fetchById(Long... values) {
        return fetch(Recommendations.RECOMMENDATIONS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public jooq_generated.tables.pojos.Recommendations fetchOneById(Long value) {
        return fetchOne(Recommendations.RECOMMENDATIONS.ID, value);
    }

    /**
     * Fetch records that have <code>diagnostic_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Recommendations> fetchRangeOfDiagnosticId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Recommendations.RECOMMENDATIONS.DIAGNOSTIC_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>diagnostic_id IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Recommendations> fetchByDiagnosticId(Long... values) {
        return fetch(Recommendations.RECOMMENDATIONS.DIAGNOSTIC_ID, values);
    }

    /**
     * Fetch records that have <code>text BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Recommendations> fetchRangeOfText(String lowerInclusive, String upperInclusive) {
        return fetchRange(Recommendations.RECOMMENDATIONS.TEXT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>text IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Recommendations> fetchByText(String... values) {
        return fetch(Recommendations.RECOMMENDATIONS.TEXT, values);
    }
}
