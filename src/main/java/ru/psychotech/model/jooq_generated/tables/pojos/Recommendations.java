/*
 * This file is generated by jOOQ.
 */
package jooq_generated.tables.pojos;


import java.io.Serializable;


/**
 * The table <code>public.recommendations</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Recommendations implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long   id;
    private Long   diagnosticId;
    private String text;

    public Recommendations() {}

    public Recommendations(Recommendations value) {
        this.id = value.id;
        this.diagnosticId = value.diagnosticId;
        this.text = value.text;
    }

    public Recommendations(
        Long   id,
        Long   diagnosticId,
        String text
    ) {
        this.id = id;
        this.diagnosticId = diagnosticId;
        this.text = text;
    }

    /**
     * Getter for <code>public.recommendations.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.recommendations.id</code>.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.recommendations.diagnostic_id</code>.
     */
    public Long getDiagnosticId() {
        return this.diagnosticId;
    }

    /**
     * Setter for <code>public.recommendations.diagnostic_id</code>.
     */
    public void setDiagnosticId(Long diagnosticId) {
        this.diagnosticId = diagnosticId;
    }

    /**
     * Getter for <code>public.recommendations.text</code>.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Setter for <code>public.recommendations.text</code>.
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Recommendations (");

        sb.append(id);
        sb.append(", ").append(diagnosticId);
        sb.append(", ").append(text);

        sb.append(")");
        return sb.toString();
    }
}
