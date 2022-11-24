CREATE TABLE simulator.teams
(
    id   oid          NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT teams_pkey PRIMARY KEY (id),
    CONSTRAINT name_unq UNIQUE (name)
);