UPDATE simulator.teams
SET "group" = 'A'
WHERE "name" IN ('Netherlands', 'Ecuador', 'Senegal', 'Qatar');

UPDATE simulator.teams
SET "group" = 'B'
WHERE "name" IN ('England', 'Wales', 'United States', 'Iran');

UPDATE simulator.teams
SET "group" = 'C'
WHERE "name" IN ('Saudi Arabia', 'Poland', 'Mexico', 'Argentina');

UPDATE simulator.teams
SET "group" = 'D'
WHERE "name" IN ('France', 'Tunisia', 'Denmark', 'Australia');

UPDATE simulator.teams
SET "group" = 'E'
WHERE "name" IN ('Germany', 'Spain', 'Japan', 'Costa Rica');

UPDATE simulator.teams
SET "group" = 'F'
WHERE "name" IN ('Belgium', 'Morocco', 'Canada', 'Croatia');

UPDATE simulator.teams
SET "group" = 'G'
WHERE "name" IN ('Brazil', 'Cameroon', 'Switzerland', 'Serbia');

UPDATE simulator.teams
SET "group" = 'H'
WHERE "name" IN ('South Korea', 'Portugal', 'Uruguay', 'Ghana');

UPDATE simulator.teams
SET "seed" = 1
WHERE "name" IN ('Qatar', 'Brazil', 'Belgium', 'France', 'Argentina', 'England', 'Spain', 'Portugal');


UPDATE simulator.teams
SET "seed" = 2
WHERE "name" IN ('Mexico', 'Netherlands', 'Denmark', 'Germany', 'Uruguay', 'Switzerland', 'United States', 'Croatia');

UPDATE simulator.teams
SET "seed" = 3
WHERE "name" IN ('Senegal', 'Tunisia', 'South Korea', 'Poland', 'Serbia', 'Morocco', 'Japan', 'Iran');

UPDATE simulator.teams
SET "seed" = 4
WHERE "name" IN ('Cameroon', 'Canada', 'Ecuador', 'Saudi Arabia', 'Ghana', 'Wales', 'Costa Rica', 'Australia');
