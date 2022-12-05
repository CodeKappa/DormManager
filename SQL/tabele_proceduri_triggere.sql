DROP DATABASE IF EXISTS DormManager;
CREATE DATABASE DormManager;
USE DormManager;

DROP TABLE IF EXISTS complex_studentesc;
CREATE TABLE complex_studentesc
(nume varchar(50) NOT NULL UNIQUE PRIMARY KEY);

DROP TABLE IF EXISTS camin;
CREATE TABLE camin
(id varchar(4) NOT NULL UNIQUE PRIMARY KEY,
adresa varchar(200) NOT NULL,
nr_camere int NOT NULL,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complex_studentesc(nume) ON UPDATE CASCADE,
CHECK (nr_camere > 0));

DROP TABLE IF EXISTS camera;
CREATE TABLE camera
(id int AUTO_INCREMENT PRIMARY KEY, 
nr_camera int NOT NULL,
etaj int NOT NULL,
camin varchar(50) NOT NULL,
nr_locuri int NOT NULL,
tip_camera ENUM ('Birou Admin', 'Casierie', 'Biblioteca', 'Sala de lectura', 'Dormitor', 'Baie', 'Oficiu', 'Spalatorie', 'Altele'),
UNIQUE KEY(nr_camera, etaj, camin),
FOREIGN KEY(camin) REFERENCES camin(id) ON UPDATE CASCADE,
CHECK (nr_locuri >= 0));

DROP TABLE IF EXISTS camere_conectate;
CREATE TABLE camere_conectate
(camera1 int NOT NULL,
camera2 int NOT NULL,

FOREIGN KEY(camera1) REFERENCES camera(id) ON UPDATE CASCADE,
FOREIGN KEY(camera2) REFERENCES camera(id) ON UPDATE CASCADE);

DROP TABLE IF EXISTS cantina;
CREATE TABLE cantina
(id int AUTO_INCREMENT UNIQUE PRIMARY KEY,
nume varchar(50) NOT NULL UNIQUE,
adresa varchar(200) NOT NULL,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complex_studentesc(nume) ON UPDATE CASCADE);

DROP TABLE IF EXISTS teren_de_fotbal;
CREATE TABLE teren_de_fotbal
(id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
nume varchar(50) NOT NULL UNIQUE,
adresa varchar(200) NOT NULL,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complex_studentesc(nume) ON UPDATE CASCADE);

DROP TABLE IF EXISTS persoane;
CREATE TABLE persoane
(cnp char(13) NOT NULL UNIQUE PRIMARY KEY,
nume varchar(50) NOT NULL,
prenume varchar(50) NOT NULL,
adresa varchar(200) NOT NULL,
nr_telefon char(12) UNIQUE NOT NULL,
email varchar(50) UNIQUE NOT NULL);

DROP TABLE IF EXISTS super_admini;
CREATE TABLE super_admini
(cnp char(13) NOT NULL UNIQUE PRIMARY KEY,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS admini;
CREATE TABLE admini
(cnp char(13) NOT NULL UNIQUE PRIMARY KEY,
id_camin varchar(4) NOT NULL,
FOREIGN KEY (id_camin) REFERENCES camin(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS studenti;
CREATE TABLE studenti
(cnp char(13) NOT NULL UNIQUE PRIMARY KEY,
sex ENUM ('M', 'F'),
camera int NOT NULL,
FOREIGN KEY (camera) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS bucatari;
CREATE TABLE bucatari
(cnp char(13) NOT NULL UNIQUE PRIMARY KEY,
cantina int NOT NULL,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cantina) REFERENCES cantina(id) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS mesteri;
CREATE TABLE mesteri
(cnp char(13) NOT NULL UNIQUE PRIMARY KEY,
complex varchar(50),
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (complex) REFERENCES complex_studentesc(nume) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS preparate_cantina;
CREATE TABLE preparate_cantina
(id int NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
nume varchar(50) NOT NULL,
descriere varchar(200));

DROP TABLE IF EXISTS meniu_cantina;
CREATE TABLE meniu_cantina
(id int NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
cantina int NOT NULL,
data_servirii DATE NOT NULL,
preparat int NOT NULL,
FOREIGN KEY (preparat) REFERENCES preparate_cantina(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cantina) REFERENCES cantina(id) ON DELETE CASCADE ON UPDATE CASCADE);
	
DROP TABLE IF EXISTS program_teren;
CREATE TABLE program_teren
(id int NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
student char(13) NOT NULL,
data_programare date NOT NULL,
teren int NOT NULL,
FOREIGN KEY (student) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (teren) REFERENCES teren_de_fotbal(id) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS program_spalatorie;
CREATE TABLE program_spalatorie
(id int NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
cameraSpalatorie int NOT NULL,
cameraStudenti int NOT NULL,
data_programare date NOT NULL,
FOREIGN KEY (cameraSpalatorie) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cameraStudenti) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS cereri_reparatii;
CREATE TABLE cereri_reparatii
(id int NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
cameraCuProbleme int NOT NULL,
data_creare date NOT NULL,
stadiu ENUM ("Necompletata", "Completata"),
id_mester char(13),
FOREIGN KEY (cameraCuProbleme) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_mester) REFERENCES mesteri(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

#Trigger to check when adding to program_spalatorie that cameraSpalatorie is a 'Spalatorie' and cameratStudenti is a 'Dormitor'