DROP TABLE IF EXISTS MUTANTS;

CREATE TABLE MUTANTS (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  DNA VARCHAR(250) NOT NULL,
  ISMUTANT BOOLEAN NOT NULL
);