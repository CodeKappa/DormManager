drop database if exists DormManager;
create database DormManager;
USE DormManager;

-- Facilitati ----------------------------------------------------------------------------------
CREATE TABLE complexStudentesc
(nume varchar(50) not null unique primary key);

CREATE TABLE camin
(id varchar(4) not null unique primary key,
adresa varchar(200) not null,
nr_camere int not null,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complexStudentesc(nume) ON UPDATE CASCADE ON DELETE CASCADE,
CHECK (nr_camere > 0));

CREATE TABLE Camera
(id int auto_increment primary key, 
nr_camera int not null,
etaj int not null,
camin varchar(50) not null,
nr_locuri int not null,
tip_camera enum ('Birou Admin', 'Casierie', 'Biblioteca', 'Sala de lectura', 'Dormitor', 'Baie', 'Oficiu', 'Spalatorie', 'Altele'),
CHECK (nr_locuri >= 0),
FOREIGN KEY(camin) REFERENCES camin(id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE CamereConectate
(camera1 int not null,
camera2 int not null,
FOREIGN KEY(camera1) REFERENCES camera(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(camera2) REFERENCES camera(id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE Cantina
(id int auto_increment unique primary key,
nume varchar(50) not null unique,
adresa varchar(200) not null,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complexStudentesc(nume) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE TerenDeFotbal
(id int auto_increment not null primary key,
nume varchar(50) not null unique,
adresa varchar(200) not null,
complex varchar(50),
FOREIGN KEY(complex) REFERENCES complexStudentesc(nume) ON UPDATE CASCADE ON DELETE CASCADE);

-- Persoane ------------------------------------------------------------------------------------

CREATE TABLE Persoane
(cnp char(13) not null unique primary key,
nume varchar(50) not null,
prenume varchar(50) not null,
adresa varchar(200) not null,
nr_telefon char(12) unique not null,
email varchar(50) unique not null);

CREATE TABLE SuperAdmini
(cnp char(13) not null unique primary key,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Admini
(cnp char(13) not null unique primary key,
id_camin varchar(4) not null,
FOREIGN KEY (id_camin) REFERENCES camin(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Studenti
(cnp char(13) not null unique primary key,
sex enum ('M', 'F'),
camera int not null,
FOREIGN KEY (camera) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Bucatari
(cnp char(13) not null unique primary key,
cantina int not null,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cantina) REFERENCES cantina(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Mesteri
(cnp char(13) not null unique primary key,
complex varchar(50),
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (complex) REFERENCES complexStudentesc(nume) ON DELETE CASCADE ON UPDATE CASCADE);

-- Diverse -------------------------------------------------------------------------------------

CREATE TABLE PreparateCantina
(id int not null unique auto_increment primary key,
nume varchar(50) not null,
descriere varchar(200));

CREATE TABLE MeniuCantina
(id int not null unique auto_increment primary key,
cantina int not null,
data_servirii DATE not null,
preparat int not null,
FOREIGN KEY (preparat) REFERENCES PreparateCantina(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cantina) REFERENCES cantina(id) ON DELETE CASCADE ON UPDATE CASCADE);
	
CREATE TABLE ProgramTeren
(id int not null unique auto_increment primary key,
student char(13) not null,
data_programare date not null,
teren int not null,
FOREIGN KEY (student) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (teren) REFERENCES TerenDeFotbal(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE ProgramSpalatorie
(id int not null unique auto_increment primary key,
cameraSpalatorie int not null,
cameraStudenti int not null,
data_programare date not null,
FOREIGN KEY (cameraSpalatorie) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cameraStudenti) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE CereriReparatii
(id int not null unique auto_increment primary key,
cameraCuProbleme int not null,
data_creare date not null,
stadiu enum ("Necompletata", "Completata"),
id_mester char(13),
FOREIGN KEY (cameraCuProbleme) REFERENCES camera(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_mester) REFERENCES mesteri(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE CereriTransferCamere
(id int not null unique auto_increment primary key,
student1 char(13) not null,
student2  char(13) not null,
data_creare date not null,
stadiu enum ("Necompletata", "Completata"),
FOREIGN KEY (student1) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (student2) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE CereriTransferCamin
(id int not null unique auto_increment primary key,
student char(13) not null,
camin varchar(4) not null,
data_creare date not null,
stadiu enum ("Necompletata", "Completata"),
FOREIGN KEY (student) REFERENCES studenti(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (camin) REFERENCES camin(id) ON DELETE CASCADE ON UPDATE CASCADE);

#Trigger to check when adding to ProgramSpalatorie that cameraSpalatorie is a 'Spalatorie' and cameratStudenti is a 'Dormitor'