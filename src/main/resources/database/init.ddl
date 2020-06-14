DROP TABLE IF EXISTS pets;
 
CREATE TABLE pets (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  type VARCHAR(250) NOT NULL
);
 
INSERT INTO pets (name, type) VALUES
  ('bugs', 'rabbit'),
  ('bertha', 'seal')