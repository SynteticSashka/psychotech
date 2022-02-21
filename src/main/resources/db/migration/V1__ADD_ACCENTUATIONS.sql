CREATE SEQUENCE diagnostic_seq;

CREATE TABLE diagnostic (
    id                 bigint            NOT NULL DEFAULT nextval('diagnostic_seq'),
    name               character varying NOT NULL,
    description        character varying NOT NULL,
    type               character varying,

    CONSTRAINT pk_diagnostic PRIMARY KEY (id)
);

CREATE SEQUENCE questions_seq;

CREATE TABLE questions (
     id                 bigint            NOT NULL DEFAULT nextval('questions_seq'),
     diagnostic_id      bigint            NOT NULL REFERENCES diagnostic(id),
     text               character varying NOT NULL,

     CONSTRAINT pk_questions PRIMARY KEY(id)
);

CREATE SEQUENCE scales_seq;

CREATE TABLE scales (
     id                 bigint            NOT NULL DEFAULT nextval('scales_seq'),
     diagnostic_id      bigint            NOT NULL REFERENCES diagnostic(id),
     name               character varying NOT NULL,
     description        character varying NOT NULL,

     CONSTRAINT pk_scales PRIMARY KEY(id)
);

CREATE SEQUENCE diagnostic_results_seq;

CREATE TABLE diagnostic_results (
    id                  bigint            NOT NULL DEFAULT nextval('diagnostic_results_seq'),
    client_id           bigint            NOT NULL REFERENCES clients(id),
    diagnostic_id       bigint            NOT NULL REFERENCES diagnostic(id),
    scale_id            bigint            NOT NULL REFERENCES scales(id),
    result              int               NOT NULL,

    CONSTRAINT pk_diagnostic_results PRIMARY KEY(id)
);