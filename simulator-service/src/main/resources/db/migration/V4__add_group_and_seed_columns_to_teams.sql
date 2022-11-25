ALTER TABLE simulator.teams 
    ADD COLUMN "group" CHAR(1) NOT NULL default '';

ALTER TABLE simulator.teams
    ADD COLUMN "seed" smallint NOT NULL default 0;
