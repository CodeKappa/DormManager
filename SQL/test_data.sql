USE DormManager;

INSERT INTO complexStudentesc VALUES ('Observator');
INSERT INTO complexStudentesc VALUES ('Marasti');

INSERT INTO camin  VALUES ('1F', 'Fabricii de Zahar', 'Marasti');
INSERT INTO camin  VALUES ('2F', 'Fabricii de Zahar', 'Marasti');
INSERT INTO camin  VALUES ('7', 'Observatorului', 'Observator');
INSERT INTO camin  VALUES ('4', 'Observatorului', 'Observator');

INSERT INTO camera  VALUES (null, '101', '1F', 4, 'Casierie');
INSERT INTO camera  VALUES (null, '102', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '103', '1F', 4, 'Dormitor');
INSERT INTO camereConectate  VALUES (2, 3);
INSERT INTO camera  VALUES (null, '104', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '105', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '106', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '107', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '108', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '109', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '110', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '201', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '202', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '203', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '301', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '401', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '601', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '701', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '801', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '901', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '1001', '1F', 4, 'Dormitor');

INSERT INTO cantina  VALUES (null, 'adresa cantina', 'Observator');

-- Studenti
INSERT INTO persoane VALUES ('1020701119930', 'Numescu', 'Prenumescu', 'Plopilor 13', '0770123654', 'student');
INSERT INTO studenti VALUES ('1020701119930', 'M', null);
INSERT INTO persoane VALUES ('1020701119936', 'Numescu6', 'Prenumescu6', 'Plopilor 18', '0770123444', 'numescu6@yahoo.com');
INSERT INTO studenti VALUES ('1020701119936', 'M', 3);
INSERT INTO persoane VALUES ('1020701119932', 'Numescu2', 'Prenumescu2', 'Plopilor 14', '0770123000', 'numescu2@yahoo.com');
INSERT INTO studenti VALUES ('1020701119932', 'F', 4);
INSERT INTO persoane VALUES ('1020701119933', 'Numescu3', 'Prenumescu3', 'Plopilor 15', '0770123111', 'numescu3@yahoo.com');
INSERT INTO studenti VALUES ('1020701119933', 'F', 4);
INSERT INTO persoane VALUES ('1020701119001', 'Numescu3', 'Prenumescu3', 'Plopilor 15', '0770123001', 'numescu001@yahoo.com');
INSERT INTO studenti VALUES ('1020701119001', 'F', null);
INSERT INTO persoane VALUES ('1020701119003', 'Numescu3', 'Prenumescu3', 'Plopilor 15', '0770123003', 'numescu002@yahoo.com');
INSERT INTO studenti VALUES ('1020701119003', 'M', null);
INSERT INTO persoane VALUES ('1020701119002', 'Numescu3', 'Prenumescu3', 'Plopilor 15', '0770123002', 'numescu003@yahoo.com');
INSERT INTO studenti VALUES ('1020701119002', 'F', null);

-- Admini
INSERT INTO persoane VALUES ('1020701119934', 'Numescu4', 'Prenumescu4', 'Plopilor 16', '0770123222', 'admin');
INSERT INTO admini VALUES ('1020701119934', '1F');
INSERT INTO persoane VALUES ('1020701119935', 'Numescu5', 'Prenumescu5', 'Plopilor 17', '0770123333', 'numescu5@yahoo.com');
INSERT INTO admini VALUES ('1020701119935', '2F');

-- Bucatari
INSERT INTO persoane VALUES ('1020701119914', 'Numescu6', 'Prenumescu6', 'Plopilor 18', '0772123222', 'bucatar');
INSERT INTO bucatari VALUES ('1020701119914', 1);

-- Super Admini
INSERT INTO persoane VALUES ('1020701119915', 'Numescu7', 'Prenumescu7', 'Plopilor 19', '0773123333', 'superadmin');
INSERT INTO superAdmini VALUES ('1020701119915');

-- Mesteri
INSERT INTO persoane VALUES ('1220701119915', 'Numescu8', 'Prenumescu8', 'Plopilor 20', '0783123333', 'mester');
INSERT INTO mesteri VALUES ('1220701119915', 'Observator');


DROP USER IF EXISTS 'superadmin'@'192.168.123.209';
CREATE USER 'superadmin'@'192.168.123.209' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'superadmin'@'192.168.123.209' WITH GRANT OPTION;

DROP USER IF EXISTS 'admin'@'192.168.123.209';
CREATE USER 'admin'@'192.168.123.209' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'192.168.123.209' WITH GRANT OPTION;

DROP USER IF EXISTS 'student'@'192.168.123.209';
CREATE USER 'student'@'192.168.123.209' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'student'@'192.168.123.209' WITH GRANT OPTION;

DROP USER IF EXISTS 'bucatar'@'192.168.123.209';
CREATE USER 'bucatar'@'192.168.123.209' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'bucatar'@'192.168.123.209' WITH GRANT OPTION;

DROP USER IF EXISTS 'mester'@'192.168.123.209';
CREATE USER 'mester'@'192.168.123.209' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'mester'@'192.168.123.209' WITH GRANT OPTION;