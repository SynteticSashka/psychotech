CREATE SEQUENCE clients_seq;

CREATE TABLE clients (
   id                 int               NOT NULL DEFAULT nextval('clients_seq'),
   name               character varying NOT NULL,
   lastname           character varying NOT NULL,
   email              character varying NOT NULL,
   password           character varying NOT NULL,
   deleted            boolean NOT NULL DEFAULT false,

   CONSTRAINT pk_users PRIMARY KEY (id)
);