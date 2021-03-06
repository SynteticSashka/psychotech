/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.pojos;


import java.io.Serializable;


/**
 * The table <code>public.diagnostic</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Diagnostic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long   id;
    private String name;
    private String description;
    private String type;

    public Diagnostic() {}

    public Diagnostic(Diagnostic value) {
        this.id = value.id;
        this.name = value.name;
        this.description = value.description;
        this.type = value.type;
    }

    public Diagnostic(
        Long   id,
        String name,
        String description,
        String type
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    /**
     * Getter for <code>public.diagnostic.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.diagnostic.id</code>.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.diagnostic.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.diagnostic.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.diagnostic.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.diagnostic.description</code>.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>public.diagnostic.type</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>public.diagnostic.type</code>.
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Diagnostic (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(type);

        sb.append(")");
        return sb.toString();
    }
}
