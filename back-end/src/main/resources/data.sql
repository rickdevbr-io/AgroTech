DROP TABLE IF EXISTS DRONES;
 
CREATE TABLE DRONES(
  id bigint AUTO_INCREMENT PRIMARY KEY,
  latitude varchar(15) NOT NULL,
  longitude varchar(15) NOT NULL,
  temperatura tinyint NOT NULL,
  umidade tinyint NOT NULL
);

INSERT INTO DRONES(latitude, longitude, temperatura, umidade) VALUES
  ('-23°32’51”S', '100°01’10”W', 30, 90);
INSERT INTO DRONES(latitude, longitude, temperatura, umidade) VALUES
  ('-23°32’51”S', '100°01’10”W', 30, 90);
INSERT INTO DRONES(latitude, longitude, temperatura, umidade) VALUES
  ('-23°32’51”S', '100°01’10”W', 30, 90);
INSERT INTO DRONES(latitude, longitude, temperatura, umidade) VALUES
  ('-23°32’51”S', '100°01’10”W', -1, 90);
INSERT INTO DRONES(latitude, longitude, temperatura, umidade) VALUES
  ('-23°32’51”S', '100°01’10”W', 2, 15);