/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.daos;


import java.util.List;

import jooq_generated.tables.Clients;
import jooq_generated.tables.records.ClientsRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * The table <code>public.clients</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ClientsDao extends DAOImpl<ClientsRecord, jooq_generated.tables.pojos.Clients, Long> {

    /**
     * Create a new ClientsDao without any configuration
     */
    public ClientsDao() {
        super(Clients.CLIENTS, jooq_generated.tables.pojos.Clients.class);
    }

    /**
     * Create a new ClientsDao with an attached configuration
     */
    public ClientsDao(Configuration configuration) {
        super(Clients.CLIENTS, jooq_generated.tables.pojos.Clients.class, configuration);
    }

    @Override
    public Long getId(jooq_generated.tables.pojos.Clients object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Clients.CLIENTS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchById(Long... values) {
        return fetch(Clients.CLIENTS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public jooq_generated.tables.pojos.Clients fetchOneById(Long value) {
        return fetchOne(Clients.CLIENTS.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Clients.CLIENTS.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchByName(String... values) {
        return fetch(Clients.CLIENTS.NAME, values);
    }

    /**
     * Fetch records that have <code>lastname BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfLastname(String lowerInclusive, String upperInclusive) {
        return fetchRange(Clients.CLIENTS.LASTNAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>lastname IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchByLastname(String... values) {
        return fetch(Clients.CLIENTS.LASTNAME, values);
    }

    /**
     * Fetch records that have <code>email BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(Clients.CLIENTS.EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchByEmail(String... values) {
        return fetch(Clients.CLIENTS.EMAIL, values);
    }

    /**
     * Fetch records that have <code>password BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfPassword(String lowerInclusive, String upperInclusive) {
        return fetchRange(Clients.CLIENTS.PASSWORD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchByPassword(String... values) {
        return fetch(Clients.CLIENTS.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>role BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfRole(String lowerInclusive, String upperInclusive) {
        return fetchRange(Clients.CLIENTS.ROLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>role IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchByRole(String... values) {
        return fetch(Clients.CLIENTS.ROLE, values);
    }

    /**
     * Fetch records that have <code>deleted BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchRangeOfDeleted(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Clients.CLIENTS.DELETED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>deleted IN (values)</code>
     */
    public List<jooq_generated.tables.pojos.Clients> fetchByDeleted(Boolean... values) {
        return fetch(Clients.CLIENTS.DELETED, values);
    }
}
