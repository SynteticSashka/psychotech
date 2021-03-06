/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.daos;


import java.util.List;

import jooq_generated.tables.Questions;
import jooq_generated.tables.records.QuestionsRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * The table <code>public.questions</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class QuestionsDao extends DAOImpl<QuestionsRecord, jooq_generated.tables.pojos.Questions, Long> {

    /**
     * Create a new QuestionsDao without any configuration
     */
    public QuestionsDao() {
        super(Questions.QUESTIONS, jooq_generated.tables.pojos.Questions.class);
    }

    /**
     * Create a new QuestionsDao with an attached configuration
     */
    public QuestionsDao(Configuration configuration) {
        super(Questions.QUESTIONS, jooq_generated.tables.pojos.Questions.class, configuration);
    }

    @Override
    public Long getId(jooq_generated.tables.pojos.Questions object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Questions> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Questions.QUESTIONS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Questions> fetchById(Long... values) {
        return fetch(Questions.QUESTIONS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public jooq_generated.tables.pojos.Questions fetchOneById(Long value) {
        return fetchOne(Questions.QUESTIONS.ID, value);
    }

    /**
     * Fetch records that have <code>diagnostic_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Questions> fetchRangeOfDiagnosticId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Questions.QUESTIONS.DIAGNOSTIC_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>diagnostic_id IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Questions> fetchByDiagnosticId(Long... values) {
        return fetch(Questions.QUESTIONS.DIAGNOSTIC_ID, values);
    }

    /**
     * Fetch records that have <code>text BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Questions> fetchRangeOfText(String lowerInclusive, String upperInclusive) {
        return fetchRange(Questions.QUESTIONS.TEXT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>text IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Questions> fetchByText(String... values) {
        return fetch(Questions.QUESTIONS.TEXT, values);
    }
}
