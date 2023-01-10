drop database if exists DormManager;
create database DormManager;
USE DormManager;

-- Facilitati ----------------------------------------------------------------------------------
CREATE TABLE complexStudentesc
(nume varchar(50) not null unique primary key);

CREATE TABLE camin
(id varchar(4) not null unique primary key,
adresa varchar(200) not null,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complexStudentesc(nume) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE camera
(id int auto_increment primary key, 
nr_camera int not null,
camin varchar(50) not null,
nr_locuri int not null,
tip_camera enum ('Birou Admin', 'Casierie', 'Biblioteca', 'Sala de lectura', 'Dormitor', 'Baie', 'Oficiu', 'Spalatorie', 'Altele'),
CHECK (nr_locuri >= 0),
FOREIGN KEY(camin) REFERENCES camin(id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE camereConectate
(camera1 int not null,
camera2 int not null,
FOREIGN KEY(camera1) REFERENCES camera(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(camera2) REFERENCES camera(id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE cantina
(id int auto_increment unique primary key,
adresa varchar(200) not null,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complexStudentesc(nume) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE terenDeFotbal
(id int auto_increment not null primary key,
nume varchar(50) not null unique,
adresa varchar(200) not null,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complexStudentesc(nume) ON UPDATE CASCADE ON DELETE CASCADE);

-- Persoane ------------------------------------------------------------------------------------

CREATE TABLE persoane
(cnp char(13) not null unique primary key,
nume varchar(50) not null,
prenume varchar(50) not null,
adresa varchar(200) not null,
nr_telefon char(12) unique not null,
email varchar(50) unique not null);

CREATE TABLE superAdmini
(cnp char(13) not null unique primary key,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE admini
(cnp char(13) not null unique primary key,
id_camin varchar(4) not null,
FOREIGN KEY (id_camin) REFERENCES camin(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE studenti
(cnp char(13) not null unique primary key,
sex enum ('M', 'F'),
camera int,
FOREIGN KEY (camera) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE bucatari
(cnp char(13) not null unique primary key,
cantina int not null,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cantina) REFERENCES cantina(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE mesteri
(cnp char(13) not null unique primary key,
complex varchar(50),
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (complex) REFERENCES complexStudentesc(nume) ON DELETE CASCADE ON UPDATE CASCADE);

-- Diverse -------------------------------------------------------------------------------------

CREATE TABLE preparateCantina
(id int not null unique auto_increment primary key,
nume varchar(50) not null,
descriere varchar(200));

CREATE TABLE meniuCantina
(id int not null unique auto_increment primary key,
cantina int not null,
data_servirii DATE not null,
preparat int not null,
FOREIGN KEY (preparat) REFERENCES PreparateCantina(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cantina) REFERENCES cantina(id) ON DELETE CASCADE ON UPDATE CASCADE);
	
CREATE TABLE programTeren
(id int not null unique auto_increment primary key,
student char(13) not null,
data_programare date not null,
teren int not null,
FOREIGN KEY (student) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (teren) REFERENCES TerenDeFotbal(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE programSpalatorie
(id int not null unique auto_increment primary key,
cameraSpalatorie int not null,
cameraStudenti int not null,
data_programare date not null,
FOREIGN KEY (cameraSpalatorie) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cameraStudenti) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE cereriReparatii
(id int not null unique auto_increment primary key,
cameraCuProbleme int not null,
data_creare date not null,
stadiu enum ("Necompletata", "Completata"),
id_mester char(13),
FOREIGN KEY (cameraCuProbleme) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_mester) REFERENCES mesteri(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE cereriTransferCamere
(id int not null unique auto_increment primary key,
student1 char(13) not null,
student2  char(13) not null,
data_creare date not null,
stadiu enum ("Necompletata", "Completata"),
FOREIGN KEY (student1) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (student2) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE cereriTransferCamin
(id int not null unique auto_increment primary key,
student char(13) not null,
camin varchar(4) not null,
data_creare date not null,
stadiu enum ("Necompletata", "Completata"),
FOREIGN KEY (student) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (camin) REFERENCES camin(id) ON DELETE CASCADE ON UPDATE CASCADE);

SET SQL_SAFE_UPDATES = 0;