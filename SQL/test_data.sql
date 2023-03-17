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
INSERT INTO camera  VALUES (null, '111', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '112', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '113', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '114', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '115', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '116', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '117', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '118', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '119', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '120', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '121', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '122', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '123', '1F', 4, 'Dormitor');
INSERT INTO camera  VALUES (null, '124', '1F', 4, 'Dormitor');
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
INSERT INTO persoane VALUES ('5020701119930', 'Kovacs', 'Paul', 'Plopilor 13', '0770123654', 'student');
INSERT INTO studenti VALUES ('5020701119930', 'M', null);
INSERT INTO persoane VALUES ('5020701119936', 'Popescu', 'Alexandru', 'Plopilor 18', '0770123444', 'alex@yahoo.com');
INSERT INTO studenti VALUES ('5020701119936', 'M', 3);
INSERT INTO persoane VALUES ('5020701119932', 'Ionescu', 'Ion', 'Plopilor 14', '0770123000', 'ion@yahoo.com');
INSERT INTO studenti VALUES ('5020701119932', 'M', 4);
INSERT INTO persoane VALUES ('5020701119933', 'Marin', 'George', 'Plopilor 88', '0770123111', 'george@yahoo.com');
INSERT INTO studenti VALUES ('5020701119933', 'M', 4);
INSERT INTO persoane VALUES ('5020701119001', 'Dumitrescu', 'Vlad', 'Plopilor 33', '0770123001', 'vladd@yahoo.com');
INSERT INTO studenti VALUES ('5020701119001', 'M', null);
INSERT INTO persoane VALUES ('5020701119003', 'Dumitrescu', 'Lucian', 'Plopilor 152', '0770123003', 'lucian@yahoo.com');
INSERT INTO studenti VALUES ('5020701119003', 'M', null);
INSERT INTO persoane VALUES ('5020701119002', 'Grigorescu', 'Vlad', 'Plopilor 15', '0770123002', 'vlad@yahoo.com');
INSERT INTO studenti VALUES ('5020701119002', 'M', null);
INSERT INTO persoane VALUES ('6020701119002', 'Grigorescu', 'Andreea', 'Plopilor 122', '0780123001', 'andreea@yahoo.com');
INSERT INTO studenti VALUES ('6020701119002', 'F', null);
INSERT INTO persoane VALUES ('6020701119003', 'Stan', 'Lucia', 'Plopilor 315', '0790123002', 'lucia@yahoo.com');
INSERT INTO studenti VALUES ('6020701119003', 'F', 5);
INSERT INTO persoane VALUES ('6020701119004', 'Tudor', 'Adriana', 'Plopilor 115', '0780123003', 'adriana@yahoo.com');
INSERT INTO studenti VALUES ('6020701119004', 'F', 6);
INSERT INTO persoane VALUES ('6020701119005', 'Grigorescu', 'Flavia', 'Plopilor 45', '0780123004', 'flavia@yahoo.com');
INSERT INTO studenti VALUES ('6020701119005', 'F', 6);
INSERT INTO persoane VALUES ('6020701119006', 'Maria', 'Ana', 'Plopilor 162', '0780123005', 'ana@yahoo.com');
INSERT INTO studenti VALUES ('6020701119006', 'F', null);

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

-- Programari
INSERT INTO camera  VALUES (null, '130', '1F', null, 'Spalatorie');
INSERT INTO programSpalatorie VALUES (null, 35, 5, '2023-01-20 08:00:00');
INSERT INTO programSpalatorie VALUES (null, 35, 6, '2023-01-20 11:00:00');
INSERT INTO programSpalatorie VALUES (null, 35, 7, '2023-01-20 14:00:00');
INSERT INTO programSpalatorie VALUES (null, 35, 8, '2023-01-20 17:00:00');
INSERT INTO programSpalatorie VALUES (null, 35, 9, '2023-01-20 20:00:00');
INSERT INTO programSpalatorie VALUES (null, 35, 25, '2023-01-20 23:00:00');
INSERT INTO programSpalatorie VALUES (null, 35, 26, '2023-01-21 8:00:00');

DROP USER IF EXISTS 'superadmin';
CREATE USER 'superadmin' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'superadmin' WITH GRANT OPTION;

DROP USER IF EXISTS 'admin';
CREATE USER 'admin' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'admin' WITH GRANT OPTION;

DROP USER IF EXISTS 'student';
CREATE USER 'student' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'student' WITH GRANT OPTION;

DROP USER IF EXISTS 'bucatar';
CREATE USER 'bucatar' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'bucatar' WITH GRANT OPTION;

DROP USER IF EXISTS 'mester';
CREATE USER 'mester' IDENTIFIED BY '3321';
GRANT ALL PRIVILEGES ON *.* TO 'mester' WITH GRANT OPTION;