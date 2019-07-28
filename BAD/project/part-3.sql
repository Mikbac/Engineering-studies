--drop DATABASE Sklep
--GO

--CREATE DATABASE Sklep
--GO

--USE Sklep
--GO

SET LANGUAGE polski
GO

-------- USU� TABELE
IF OBJECT_ID('Reklamacje', 'U') IS NOT NULL 
	DROP TABLE Reklamacje;

IF OBJECT_ID('RealizacjeZamowien', 'U') IS NOT NULL 
	DROP TABLE RealizacjeZamowien;

IF OBJECT_ID('Zamowienia', 'U') IS NOT NULL 
	DROP TABLE Zamowienia;

IF OBJECT_ID('Uzytkownicy', 'U') IS NOT NULL 
	DROP TABLE Uzytkownicy;

IF OBJECT_ID('Produkty', 'U') IS NOT NULL 
	DROP TABLE Produkty;

IF OBJECT_ID('Dostawcy', 'U') IS NOT NULL 
	DROP TABLE Dostawcy;

IF OBJECT_ID('KategorieProduktow', 'U') IS NOT NULL 
	DROP TABLE KategorieProduktow;

--------- CREATE - UTW�RZ TABELE I POWI�ZANIA

CREATE TABLE Uzytkownicy
(
	idUzytkownika	INT IDENTITY(1,1) NOT NULL PRIMARY KEY, -- sprawdzic, czy od 1 co 1
	imie	VARCHAR(30) NOT NULL,
	nazwisko	VARCHAR(30) NOT NULL,
	adres	VARCHAR(30) NOT NULL,
	haslo	VARCHAR(30) NOT NULL,
	email	VARCHAR(30) NOT NULL UNIQUE,
	numerTelefonu	VARCHAR(9) NOT NULL,
	dataRejestracji DATETIME NOT NULL,
	CHECK (haslo != imie)
);

CREATE TABLE KategorieProduktow
(
	idKategorii	INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	nazwa	VARCHAR(30) NOT NULL,
	czasNaRealizacjeWDniach	INT NOT NULL
);

CREATE TABLE Dostawcy
(
	idDostawcy	INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	nazwa	VARCHAR(30) NOT NULL,
	dlugoscGwarancjiWLatach INT NOT NULL,
	adresSerwisu	VARCHAR(30) NOT NULL,
	nrTelefonu	VARCHAR(9) NOT NULL
);

CREATE TABLE Produkty
(
	idProduktu	INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	idKategorii	INT REFERENCES KategorieProduktow(idKategorii) NOT NULL,
	idDostawcy INT REFERENCES Dostawcy(idDostawcy) NOT NULL,
	cena MONEY NOT NULL,
	nazwa	VARCHAR(30) NOT NULL,
	ilosc INT NOT NULL
);

CREATE TABLE Zamowienia
(
	idZamowienia	INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	idZamawiajacego	INT REFERENCES Uzytkownicy(idUzytkownika) NOT NULL,
	dataPrzyjeciaZamowienia DATETIME NOT NULL,
	[status] VARCHAR(30) NOT NULL CHECK ([status] IN ('w realizacji', 'zrealizowany', 'anulowane'))
);

CREATE TABLE RealizacjeZamowien
(
	idRealizacji	INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	idZamowienia	INT NOT NULL REFERENCES Zamowienia(idZamowienia),
	idProduktu	INT NOT NULL REFERENCES Produkty(idProduktu),
	ilosc	INT NOT NULL,
	[stan]	VARCHAR(30) NOT NULL CHECK ([stan] IN ('przygotowywany', 'gotowy', 'anulowane'))
);

CREATE TABLE Reklamacje
(
	idReklamacji	INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	idReklamowanejRealizacji	INT NOT NULL REFERENCES RealizacjeZamowien(idRealizacji),
	[rodzajReklamacji]	VARCHAR(30) NOT NULL CHECK ([rodzajReklamacji] IN ('reklamacja', 'zwrot', 'wymiana')),
	[statusReklamacji]	VARCHAR(30) NOT NULL CHECK ([statusReklamacji] IN ('zatwierdzona', 'niezatwierdzona')),
	[stanRealizowaniaReklamacji]	VARCHAR(30) NOT NULL CHECK ([stanRealizowaniaReklamacji] IN ('zrealizowana', 'niezrealizowana'))
);

---------- INSERT - WSTAW DANE

INSERT INTO Uzytkownicy VALUES ('Pawe�', 'Ry�', 'Ponza�, Chopina 12', 'sdfasdf', 'pawel@onet.pl', '999888777', '01-01-2015' );
INSERT INTO Uzytkownicy VALUES ('Dominik', 'Mi�', 'Warszawa, Berlinga 12', 'admin1', 'owca@wp.pl', '123123123', '02-02-2011' );
INSERT INTO Uzytkownicy VALUES ('Roman', 'Dzik', 'Wroc�aw, Witosa 12', 'asdfsd', 'zubr@o2.pl', '938147576', '01-03-2000' );
INSERT INTO Uzytkownicy VALUES ('Adrianna', 'Ko�', 'Konin, Nagietkowa 12', 'rteytr', 'labed@onet.pl', '345789123', '2-03-2013' );
INSERT INTO Uzytkownicy VALUES ('Marta', 'Kobra', 'G�ski, Harcerska 12', 'xcvdsf', 'koza@wp.pl', '098765432', '21-04-2014' );
INSERT INTO Uzytkownicy VALUES ('Dominika', 'Bonzo', 'G�dki, Dolna 12', 'wepioer', 'dwor@o2.pl', '346123842', '11-05-2012' );
INSERT INTO Uzytkownicy VALUES ('Roksana', 'Martyniuk', 'Wroc�aw, G�rna 12', 'huiwfe', 'daniel@wp.pl', '123456784', '02-06-2011' );
INSERT INTO Uzytkownicy VALUES ('Andrzej', 'Zebra', 'Warszawa, W�ska 12', 'wefohwe', 'zubr@wp.pl', '930495869', '12-07-2013' );
INSERT INTO Uzytkownicy VALUES ('Adam', 'Wielb��d', 'Pozna�, Szeroka 12', 'wiefndwe', 'dom12@onet.pl', '384756123', '12-07-2013' );
INSERT INTO Uzytkownicy VALUES ('Adrian', 'Koza', '�rem, Niska 12', 'wnefowe', 'rawal12@o2.pl', '123857604', '02-08-2014' );
INSERT INTO Uzytkownicy VALUES ('Dominika', 'G�', 'Krak�w, Paderewskiego 12', 'wjefwehn', 'polok@o2.pl', '765439586', '21-09-2015' );
INSERT INTO Uzytkownicy VALUES ('Patryk', 'Bodo', 'Zawoja, Stokrotkowa 12', 'qwoejkpqw', 'kkoza@o2.pl', '384756304', '22-10-2016' );
INSERT INTO Uzytkownicy VALUES ('Miko�aj', 'Nowak', 'Wroc�aw, Tulipanowa 12', '23ipoj', 'lowca@onet.pl', '123876543', '12-11-2017' );
INSERT INTO Uzytkownicy VALUES ('Wiktor', 'Kowalski', 'Oslo, Nowa 12', 'io2nh3q', 'zaba@wp.pl', '765489304', '14-12-2010' );
INSERT INTO Uzytkownicy VALUES ('Agata', 'Wis�a', 'Wenecja, Dmowskiego 12', '2ip3jor', 'kolokwium@o2.pl', '182736409', '26-11-2011' );
INSERT INTO Uzytkownicy VALUES ('Tomasz', 'Odra', 'Konin, Stara 12', 'p394jr', 'zycie@wp.pl', '637485960', '02-12-2012' );
INSERT INTO Uzytkownicy VALUES ('Judyta', 'Sapkowski', 'Warszawa, Wenecka 12', '19-je', 'daniel@onet.pl', '654321987', '06-11-2013' );
INSERT INTO Uzytkownicy VALUES ('Paulina', 'Tolkien', 'Gda�sk, Ostra 12', 'p2io3jhfr', 'brzoza@o2.pl', '876543217', '08-01-2014' );
INSERT INTO Uzytkownicy VALUES ('Joanna', 'Ryba', 'Sopot, D�browskiego 12', 'i2po3jrf', 'sosna@onet.pl', '654326789', '19-02-2012' );
INSERT INTO Uzytkownicy VALUES ('Klaudia', 'Rybka', 'Gdynia, Berlinga 12', 'iuyg', 'wierzba@onet.pl', '321123456', '21-03-2015' );
INSERT INTO Uzytkownicy VALUES ('Patrycja', 'Nowak', 'Krak�w, Rybia 12', 'o2i3hjf', 'klon@wp.pl', '098767890', '22-03-2016' );
INSERT INTO Uzytkownicy VALUES ('Natalia', 'Kowalski', 'Srem, �ososiowa 12', 'oi384f', 'tulipan@wp.pl', '456789876', '23-04-2017' );
INSERT INTO Uzytkownicy VALUES ('Katolina', 'Komeda', '�roda, Nowa 12', 'jipo12e', 'dominik56789@wp.pl', '456789987', '24-05-2017' );
INSERT INTO Uzytkownicy VALUES ('Filip', 'Gawron', 'Zawoja, Stara 12', '9u23j', 'rotard@wp.pl', '543256789', '15-06-2011' );
INSERT INTO Uzytkownicy VALUES ('Piotr', 'Celka', 'Gdynia, Stary Rynek 12', 'bjjuiw', 'regis@wp.pl', '876543234', '16-07-2012' );
INSERT INTO Uzytkownicy VALUES ('Dorota', 'Baran', 'Zakopane, Dmowskiego 12', '89hui23', 'lulek@wp.pl', '987654323', '17-08-2013' );
INSERT INTO Uzytkownicy VALUES ('Magdalena', 'Baranek', 'Ostr�w, D�browskiego 12', 'ujbh234rf', 'ortodonta123@onet.pl', '345678765', '18-09-2015' );
INSERT INTO Uzytkownicy VALUES ('Korlenia', 'G�browicz', 'Wrze�nia, Owalna 12', 'ojn2f', 'kosiarka88@onet.pl', '456789876', '19-10-2015' );
INSERT INTO Uzytkownicy VALUES ('Weronika', 'Orwell', 'Pozna�, Harcerska 12', 'oin23f', '123wojownik@wp.pl', '234567876', '20-11-2016' );
INSERT INTO Uzytkownicy VALUES ('Anna', 'Sienkiewicz', 'Warszawa, Stokrotkowa 12', 'oihn2f2', 'mag999@onet.pl', '654345678', '21-12-2017' );
INSERT INTO Uzytkownicy VALUES ('Norbert', 'Chrobry', 'Gdynia, Centralna 12', 'oi2nh3f', 'mop123@o2.pl', '098765434', '22-11-2011' );
INSERT INTO Uzytkownicy VALUES ('Aleksander', 'Kaczka', 'Gda�sk, Tulipanowa 12', 'oi23fh', 'york567@onet.pl', '876543254', '23-11-2012' );
INSERT INTO Uzytkownicy VALUES ('Aleksandra', 'Marcinek', 'Sopot, Witosa 12', 'oi2h3f', 'owczarek55@onet.pl', '123456765', '24-01-2013' );
INSERT INTO Uzytkownicy VALUES ('Jakub', 'Panicz', '�rem, Wroc�awka 12', 'oi2hn3f', 'gosia12@onet.pl', '234567876', '26-02-2014' );
INSERT INTO Uzytkownicy VALUES ('Mariola', 'Siedlecka', 'G�dki, Chopina 12', 'ugh34', 'pitbull96@o2.pl', '456787654', '25-03-2015' );

INSERT INTO KategorieProduktow VALUES ('Smartfony', 5);
INSERT INTO KategorieProduktow VALUES ('Tablety', 3);
INSERT INTO KategorieProduktow VALUES ('Laptopy', 2);
INSERT INTO KategorieProduktow VALUES ('Dyski', 6);
INSERT INTO KategorieProduktow VALUES ('S�uchawki', 1);
INSERT INTO KategorieProduktow VALUES ('Myszki', 2);
INSERT INTO KategorieProduktow VALUES ('Obudowy', 3);
INSERT INTO KategorieProduktow VALUES ('Procesory', 5);
INSERT INTO KategorieProduktow VALUES ('�adowarki', 7);
INSERT INTO KategorieProduktow VALUES ('Kable', 3);

INSERT INTO Dostawcy VALUES ('Toshiba', 2, 'Pozna�, Umultowka 87', '123123123');
INSERT INTO Dostawcy VALUES ('Sony', 3, 'Warszawa, Kopernika 44', '123456789');
INSERT INTO Dostawcy VALUES ('Samsung', 4, 'Pozna�, Chopina 34', '910293849');
INSERT INTO Dostawcy VALUES ('Brother', 5, 'Wroc�aw, Berlinga 45', '182930495');
INSERT INTO Dostawcy VALUES ('Zelmer', 6, 'Gda�ska, Maczka 12', '182934958');
INSERT INTO Dostawcy VALUES ('Intel', 5, 'G�dki, D�browskiego 55', '910293847');
INSERT INTO Dostawcy VALUES ('Asus', 8, 'Gda�ska, Szulca 56', '175849306');
INSERT INTO Dostawcy VALUES ('Siemens', 8, 'Pozna�, Nagietkowa 78', '178394065');
INSERT INTO Dostawcy VALUES ('Apple', 2, 'Wroc�aw, Kwiatowa 34', '768795049');
INSERT INTO Dostawcy VALUES ('Razer', 3, 'Warszawa, Dolna 47', '888339123');
INSERT INTO Dostawcy VALUES ('Bosh', 4, 'Gda�ska, Regisa 25', '857694058');
INSERT INTO Dostawcy VALUES ('ABB', 5, 'Warszawa, Sapkowskiego 46', '654319786');
INSERT INTO Dostawcy VALUES ('Miele', 6, 'Pozna�, Wroc�awka 57', '102938457');
INSERT INTO Dostawcy VALUES ('Philips', 2, 'Wroc�aw, Sienna 68', '654321098');
INSERT INTO Dostawcy VALUES ('Alstom', 1, 'G�dki, Jana Paw�a II 69', '637489607');
INSERT INTO Dostawcy VALUES ('Aeg', 2, 'Wroc�aw, Lipowa 70', '123948576');
INSERT INTO Dostawcy VALUES ('Neff', 2, 'Warszawa, Sosnowa 11', '102984754');

INSERT INTO Produkty VALUES (1, 3, 1200, 'Smartfon Galaxy S7', 1);
INSERT INTO Produkty VALUES (1, 3, 1234, 'Smartfon Galaxy M0', 3);
INSERT INTO Produkty VALUES (1, 3, 4567, 'Smartfon Galaxy W9', 44);
INSERT INTO Produkty VALUES (1, 2, 3456, 'Smartfon Z1', 12);
INSERT INTO Produkty VALUES (1, 2, 6789, 'Smartfon Z5', 42);
INSERT INTO Produkty VALUES (1, 2, 123, 'Smartfon M2', 55);
INSERT INTO Produkty VALUES (2, 3, 2534, 'Tablet wer q43', 0);
INSERT INTO Produkty VALUES (2, 4, 457, 'Tablet q90', 93);
INSERT INTO Produkty VALUES (2, 2, 786, 'Tablet q60', 65);
INSERT INTO Produkty VALUES (2, 3, 345, 'Tablet q50', 22);
INSERT INTO Produkty VALUES (2, 4, 234, 'Tablet q40', 23);
INSERT INTO Produkty VALUES (2, 10, 867, 'Tablet q30', 90);
INSERT INTO Produkty VALUES (3, 11, 543, 'Laptop DVX-123', 2);
INSERT INTO Produkty VALUES (3, 12, 2345, 'Laptop DVX-999', 1);
INSERT INTO Produkty VALUES (3, 13, 456, 'Laptop DVX-23', 23);
INSERT INTO Produkty VALUES (3, 14, 345, 'Laptop DVX-001', 0);
INSERT INTO Produkty VALUES (3, 1, 867, 'Laptop DVX-003', 0);
INSERT INTO Produkty VALUES (3, 3, 645, 'Laptop DVX-992', 0);
INSERT INTO Produkty VALUES (4, 1, 234, 'Dysk SSD 512MB', 23);
INSERT INTO Produkty VALUES (4, 5, 686, 'Dysk SSD 1GB', 44);
INSERT INTO Produkty VALUES (4, 6, 566, 'Dysk SSD 2GB', 12);
INSERT INTO Produkty VALUES (4, 7, 456, 'Dysk HDD 1TB', 33);
INSERT INTO Produkty VALUES (4, 8, 456, 'Dysk HDD 2TB', 80);
INSERT INTO Produkty VALUES (4, 9, 756, 'Dysk HDD 5TB', 43);
INSERT INTO Produkty VALUES (5, 8, 34, 'S�uchawki raw1', 22);
INSERT INTO Produkty VALUES (5, 9, 76, 'S�uchawki war3', 12);
INSERT INTO Produkty VALUES (5, 6, 76, 'S�uchawki war4', 32);
INSERT INTO Produkty VALUES (5, 5, 34, 'S�uchawki ww4', 5);
INSERT INTO Produkty VALUES (5, 4, 45, 'S�uchawki rr3', 0);
INSERT INTO Produkty VALUES (5, 3, 56, 'S�uchawki rwx10', 0);
INSERT INTO Produkty VALUES (6, 1, 234, 'Myska Rival 100', 23);
INSERT INTO Produkty VALUES (6, 2, 456, 'Myska Rival 200', 44);
INSERT INTO Produkty VALUES (6, 3, 234, 'Myska Rival 300', 12);
INSERT INTO Produkty VALUES (6, 4, 345, 'Myska Rival 400', 43);
INSERT INTO Produkty VALUES (6, 5, 765, 'Myska Rival 500', 1);
INSERT INTO Produkty VALUES (6, 11, 234, 'Myska Rival 600', 5);
INSERT INTO Produkty VALUES (7, 3, 456, 'Obudowa Gladius 120', 9);
INSERT INTO Produkty VALUES (7, 4, 213, 'Obudowa Gladius 220', 7);
INSERT INTO Produkty VALUES (7, 2, 765, 'Obudowa Gladius 320', 8);
INSERT INTO Produkty VALUES (7, 3, 234, 'Obudowa Gladius 420', 2);
INSERT INTO Produkty VALUES (7, 4, 345, 'Obudowa Gladius 520', 9);
INSERT INTO Produkty VALUES (7, 10, 234, 'Obudowa Gladius 620', 0);
INSERT INTO Produkty VALUES (8, 6, 645, 'Procesor i5-7500', 6);
INSERT INTO Produkty VALUES (8, 6, 534, 'Procesor i6-6600', 3);
INSERT INTO Produkty VALUES (8, 6, 876, 'Procesor i7-5500', 4);
INSERT INTO Produkty VALUES (8, 6, 765, 'Procesor i5-4500', 8);
INSERT INTO Produkty VALUES (8, 6, 456, 'Procesor i6-7600', 9);
INSERT INTO Produkty VALUES (8, 6, 435, 'Procesor i7-5400', 7);
INSERT INTO Produkty VALUES (9, 11, 978, '�adowarka mA 2200', 6);
INSERT INTO Produkty VALUES (9, 15, 645, '�adowarka mA 1000', 7);
INSERT INTO Produkty VALUES (9, 16, 345, '�adowarka mA 1200', 3);
INSERT INTO Produkty VALUES (9, 17, 654, '�adowarka mA 2000', 6);
INSERT INTO Produkty VALUES (9, 12, 234, '�adowarka mA 1500', 11);
INSERT INTO Produkty VALUES (9, 13, 645, '�adowarka mA 1700', 32);
INSERT INTO Produkty VALUES (10, 17, 234, 'Kabel HDMI 1m', 6);
INSERT INTO Produkty VALUES (10, 17, 645, 'Kabel HDMI 2m', 0);
INSERT INTO Produkty VALUES (10, 17, 876, 'Kabel HDMI 3m', 8);
INSERT INTO Produkty VALUES (10, 16, 234, 'Kabel USB 1m', 6);
INSERT INTO Produkty VALUES (10, 15, 123, 'Kabel USB 2m', 6);
INSERT INTO Produkty VALUES (10, 15, 423, 'Kabel USB 3m', 9);

INSERT INTO Zamowienia VALUES (1, '01-02-2015', 'w realizacji');
INSERT INTO Zamowienia VALUES (2, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (3, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (11, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (15, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (12, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (23, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (30, '01-02-2015', 'zrealizowany');
INSERT INTO Zamowienia VALUES (19, '01-02-2015', 'anulowane');
INSERT INTO Zamowienia VALUES (6, '01-02-2015', 'anulowane');

INSERT INTO RealizacjeZamowien VALUES (1, 12, 2, 'przygotowywany');
INSERT INTO RealizacjeZamowien VALUES (1, 13, 23, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (1, 14, 23, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (2, 55, 12, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (3, 6, 1, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (4, 7, 12, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (5, 8, 1, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (6, 9, 2, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (7, 10, 3, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (8, 12, 4, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (8, 23, 5, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (8, 22, 6, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (9, 25, 7, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (10, 7, 8, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (10, 28, 9, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (10, 30, 10, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (10, 34, 12, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (10, 55, 6, 'gotowy');
INSERT INTO RealizacjeZamowien VALUES (10, 35, 7, 'przygotowywany');
INSERT INTO RealizacjeZamowien VALUES (10, 46, 8, 'przygotowywany');

INSERT INTO Reklamacje VALUES (5, 'reklamacja' , 'niezatwierdzona', 'niezrealizowana');
INSERT INTO Reklamacje VALUES (4, 'zwrot' ,'zatwierdzona', 'niezrealizowana');
INSERT INTO Reklamacje VALUES (7, 'wymiana' ,'zatwierdzona', 'zrealizowana');
INSERT INTO Reklamacje VALUES (9, 'reklamacja' ,'niezatwierdzona', 'niezrealizowana');
INSERT INTO Reklamacje VALUES (10, 'zwrot' ,'niezatwierdzona', 'niezrealizowana');

------------ SELECT
SELECT * FROM Uzytkownicy;
SELECT * FROM Zamowienia;
SELECT * FROM RealizacjeZamowien;
SELECT * FROM Reklamacje;
SELECT * FROM KategorieProduktow;
SELECT * FROM Produkty;
SELECT * FROM Dostawcy;