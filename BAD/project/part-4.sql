-- --------widok1 - co kto zamowil--------

CREATE VIEW ZamowieniaUzytkownikow(imie, nazwisko, nazwa)
AS
(
SELECT imie, nazwisko, nazwa
FROM Uzytkownicy 
	LEFT OUTER JOIN Zamowienia z1 on idZamawiajacego=idUzytkownika
	LEFT OUTER JOIN RealizacjeZamowien z2 on z1.idZamowienia=z2.idZamowienia
	INNER JOIN Produkty p1 on p1.idProduktu=z2.idProduktu
);


SELECT * 
FROM   ZamowieniaUzytkownikow;

DROP VIEW ZamowieniaUzytkownikow


-- --------widok2 - ile w jakiej firmie zamowiono--------

CREATE VIEW ZamowieniaFirm(nazwa, ilosc)
AS
(
SELECT d1.nazwa, COUNT(d1.nazwa) AS 'ilosc'
FROM Uzytkownicy 
	LEFT OUTER JOIN Zamowienia z1 on idZamawiajacego=idUzytkownika
	LEFT OUTER JOIN RealizacjeZamowien z2 on z1.idZamowienia=z2.idZamowienia
	INNER JOIN Produkty p1 on p1.idProduktu=z2.idProduktu
	LEFT OUTER JOIN Dostawcy d1 on d1.idDostawcy=p1.idDostawcy
	GROUP BY d1.nazwa
);


SELECT * 
FROM   ZamowieniaFirm;

DROP VIEW ZamowieniaFirm

-- --------widok3 - ile zamowiono danych produktow--------

CREATE VIEW ZamowieniaTowarow(nazwa, ilosc)
AS
(
SELECT p1.nazwa, SUM(z2.ilosc) AS 'ilosc'
FROM Uzytkownicy 
	LEFT OUTER JOIN Zamowienia z1 on idZamawiajacego=idUzytkownika
	LEFT OUTER JOIN RealizacjeZamowien z2 on z1.idZamowienia=z2.idZamowienia
	INNER JOIN Produkty p1 on p1.idProduktu=z2.idProduktu
	GROUP BY p1.nazwa
);


SELECT * 
FROM   ZamowieniaTowarow;

DROP VIEW ZamowieniaTowarow

-- --------procedury1  - promocja, obniza ceny o podana wartosc, domyslnie 10--------


CREATE PROCEDURE Promocja
        @proc  TINYINT = 10
AS
BEGIN                       
        UPDATE Produkty
        SET cena = cena-cena * @proc/100
                FROM   Produkty
END;


EXEC Promocja 
     10

SELECT *
FROM Produkty


DROP PROC Promocja

-- --------procedury2  - podwyżka, podwyższa ceny o podana wartosc, domyslnie 10--------

CREATE PROCEDURE Podwyzka
        @proc  TINYINT = 10

AS
BEGIN                      
   
        UPDATE Produkty
        SET cena = cena+cena * @proc/100
                FROM   Produkty
            

END;


EXEC Podwyzka 
     10

SELECT *
FROM Produkty


DROP PROC Podwyzka

-- --------procedura3 - usuwanie wszystkich reklamacji do danej realizacji--------

CREATE PROCEDURE UsuwanieReklamacji
        @idReklamacji  INT = 0 

AS
BEGIN

		DELETE FROM Reklamacje
		WHERE idReklamowanejRealizacji = @idReklamacji        

END;


EXEC UsuwanieReklamacji 5




SELECT *
FROM Reklamacje



DROP PROC UsuwanieReklamacji

-- --------procedura 4 - dodawanie nowej realizacjiZamowienia, idZamowienia, idProduktu, ilosc--------

CREATE PROCEDURE DodawanieRealizacji
        @idZamowienia  INT,
		@idProduktu INT,
		@ilosc INT 


AS
BEGIN                      

   
  if (@ilosc > 0)
   BEGIN
  if ((SELECT ilosc FROM Produkty WHERE idProduktu=@idProduktu) >= @ilosc)
   BEGIN
		INSERT INTO RealizacjeZamowien (idZamowienia, idProduktu, ilosc, stan) VALUES (@idZamowienia, @idProduktu, @ilosc, 'przygotowywany')
		UPDATE Produkty SET ilosc = ilosc - @ilosc WHERE idProduktu = @idProduktu
    END


END
END;


EXEC DodawanieRealizacji 2, 4, 5


SELECT *
FROM RealizacjeZamowien

SELECT *
FROM Produkty


DROP PROC DodawanieRealizacji


-- --------procedura5  - podawanie informacji o ilosci elementow w danej kategorii--------


CREATE PROCEDURE kategoriaProduktow
        @idKategorii  TINYINT 

AS
BEGIN                      
   
SELECT a2.nazwa, SUM(a1.ilosc) AS 'ilosc towarow w kategorii'
FROM Produkty a1
LEFT OUTER JOIN KategorieProduktow a2 ON a1.idKategorii=a2.idKategorii
WHERE a2.idKategorii=@idKategorii
GROUP BY a2.nazwa
   
            

END;



EXEC kategoriaProduktow 
     1



DROP PROC kategoriaProduktow

-- --------procedura6  - umozliwia anulowanie pewnego elementu zamowienia --------

CREATE PROCEDURE rezygnacjaZRealizacji
        @idZamowienia  TINYINT 

AS
BEGIN                      

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

BEGIN TRAN t1;


UPDATE Produkty SET ilosc = ilosc + (SELECT ilosc FROM RealizacjeZamowien WHERE idRealizacji=@idZamowienia)

UPDATE RealizacjeZamowien SET stan='anulowane' WHERE idRealizacji=@idZamowienia

UPDATE RealizacjeZamowien SET ilosc=0 WHERE idRealizacji=@idZamowienia


COMMIT TRAN t1;

            

END;



EXEC rezygnacjaZRealizacji 
     1
	 EXEC rezygnacjaZRealizacji 
     2
	 EXEC rezygnacjaZRealizacji 
     3

	 SELECT * FROM RealizacjeZamowien
	 SELECT * FROM Produkty

DROP PROC rezygnacjaZRealizacji
-- --------procedura7  - umozliwia anulowanie zamowienia, jeśli wszystkie jego elementy są anulowane --------


CREATE PROCEDURE rezygnacjaZZamowienia
        @idZamowienia  TINYINT 

AS
BEGIN                      

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

BEGIN TRAN t1;


IF NOT EXISTS (SELECT * FROM RealizacjeZamowien WHERE idZamowienia=@idZamowienia AND stan!='anulowane')
	UPDATE Zamowienia SET status='anulowane' WHERE idZamowienia=@idZamowienia
ELSE
print('nie wszystkie lementy zamowienia są anulowane')



COMMIT TRAN t1;

            

END;



EXEC rezygnacjaZZamowienia 
     1


	 SELECT * FROM Zamowienia




DROP PROC rezygnacjaZZamowienia


-- --------procedura 8 - usuniecie danych uzytkownika --------

CREATE PROCEDURE UsuwanieDanychUzytkownika
        @idUzytkownika  INT ,
		@haslo VARCHAR(30)

AS
BEGIN                      

IF ((SELECT haslo FROM Uzytkownicy WHERE idUzytkownika=@idUzytkownika)=(@haslo))
BEGIN
UPDATE Uzytkownicy SET email='usuniete'+ CONVERT(varchar(30), idUzytkownika), dataRejestracji = 00-00-0000, haslo='usuniete', numerTelefonu = 'usuniete' WHERE idUzytkownika=@idUzytkownika
print 'Usuniecie informacji zakonczone powodzeniem'
END
ELSE
print 'zle haslo!'
            

END;

EXEC UsuwanieDanychUzytkownika 
     1 , admin1

EXEC UsuwanieDanychUzytkownika 
     1 , sdfasdf

	 SELECT * FROM Uzytkownicy


DROP PROC UsuwanieDanychUzytkownika

-- --------procedura 9 - zmiana ilosci towaru w realizacji --------
CREATE PROCEDURE zmianaIlosciTwoarowWZamowieniu
        @idRealizacji  INT ,
		@ilosc INT

AS
BEGIN                      

DECLARE @idProduktu INT

SET @idProduktu = (SELECT idProduktu FROM RealizacjeZamowien WHERE idRealizacji=@idRealizacji)

IF ((((SELECT ilosc FROM Produkty WHERE idProduktu=@idProduktu)+(SELECT ilosc FROM RealizacjeZamowien WHERE idRealizacji=@idRealizacji)) >= @ilosc) AND ('anulowane'!=(SELECT stan FROM RealizacjeZamowien WHERE idRealizacji=@idRealizacji)))
BEGIN
UPDATE Produkty SET ilosc=ilosc+(SELECT ilosc FROM RealizacjeZamowien WHERE idRealizacji = @idRealizacji) WHERE idProduktu=(SELECT idProduktu FROM RealizacjeZamowien  WHERE idRealizacji = @idRealizacji)

UPDATE RealizacjeZamowien SET ilosc=@ilosc WHERE idRealizacji=@idRealizacji

UPDATE Produkty SET ilosc=ilosc-@ilosc  WHERE idProduktu = @idProduktu
END
          

END;

EXEC zmianaIlosciTwoarowWZamowieniu 
     1 , 11

EXEC zmianaIlosciTwoarowWZamowieniu 
     5 , 12


	 SELECT * FROM RealizacjeZamowien
	 SELECT * FROM Produkty


DROP PROC zmianaIlosciTwoarowWZamowieniu


-- --------funkcja1 - zwracanie informacji ile w sklepie wydal dany uzytkownik, idUzytkownika--------
create function zyskiZUzytkownika
        (@idUzytkownika INT)
        returns MONEY
as
begin
	
	DECLARE @wynik MONEY

	SET @wynik = (SELECT SUM(cena)
	FROM Uzytkownicy
	LEFT OUTER JOIN Zamowienia a3 ON a3.idZamawiajacego=idUzytkownika
	LEFT OUTER JOIN RealizacjeZamowien a2 ON a2.idRealizacji=a3.idZamowienia
	LEFT OUTER JOIN Produkty a1 ON a1.idProduktu=a2.idProduktu 
	WHERE idUzytkownika=@idUzytkownika)

	RETURN @wynik

end


SELECT dbo.zyskiZUzytkownika(1) 'uzytkownik wydal'


DROP FUNCTION zyskiZUzytkownika


-- --------funkcja2 - zwraca kupcow towaru o podanym id--------

create function kupcyProduktu
        (@idProduktu INT)
        returns table
as
        return SELECT  idUzytkownika, imie, nazwisko FROM Uzytkownicy 
		LEFT OUTER JOIN Zamowienia a3 ON a3.idZamawiajacego=idUzytkownika
	LEFT OUTER JOIN RealizacjeZamowien a2 ON a2.idRealizacji=a3.idZamowienia
	LEFT OUTER JOIN Produkty a1 ON a1.idProduktu=a2.idProduktu 
	WHERE a2.idProduktu = @idProduktu


SELECT * FROM dbo.kupcyProduktu(12)


DROP FUNCTION kupcyProduktu


-- --------wyzwalacz1 - uniemozliwia reklamowanie zamowienia, ktore nie zostalo zrealizowane--------

CREATE TRIGGER reklamacjaNaZamowienie ON Reklamacje
AFTER INSERT
AS
    if('zrealizowany'!=(SELECT [status] FROM Zamowienia WHERE idZamowienia = (SELECT idRealizacji FROM RealizacjeZamowien WHERE idRealizacji = (SELECT idReklamowanejRealizacji FROM inserted))))
		BEGIN
		PRINT 'nie można reklamować/zwracac/wymieniac przedmiotu z niezrealizowanego zamowienia!';
		ROLLBACK;
		END

     
GO


INSERT INTO Reklamacje VALUES (1, 'reklamacja', 'niezatwierdzona', 'niezrealizowana');
INSERT INTO Reklamacje VALUES (4, 'reklamacja', 'niezatwierdzona', 'niezrealizowana');


SELECT * FROM Reklamacje

DROP TRIGGER reklamacjaNaZamowienie

-- --------wyzwalacz2 - uniemozliwia usuwanie dostawcow--------

CREATE TRIGGER usuwanieDostawcow ON Dostawcy
INSTEAD OF DELETE
AS
     PRINT 'nie mozna usuwac firm/dostawcow!';
GO


DELETE FROM Dostawcy
WHERE idDostawcy = 1

SELECT * FROM Dostawcy

DROP TRIGGER usuwanieDostawcow

-- --------wyzwalacz3 - uniemozliwia usuwanie kategorii--------

CREATE TRIGGER usuwanieKategorii ON KategorieProduktow
INSTEAD OF DELETE
AS
     PRINT 'nie mozna usuwac kategorii produktww!';
GO


DELETE FROM KategorieProduktow
WHERE idKategorii = 1

SELECT * FROM KategorieProduktow

DROP TRIGGER usuwanieKategorii

-- --------wyzwalacz3 - uniemozliwia usuwanie Zamowienia--------

CREATE TRIGGER usuwanieZamowien ON Zamowienia
INSTEAD OF DELETE
AS
     PRINT 'nie mozna usuwac zamowien!';
GO


DELETE FROM Zamowienia
WHERE idZamowienia = 1

SELECT * FROM Zamowienia

DROP TRIGGER usuwanieZamowien

-- --------wyzwalacz4 - usuwanie konta uzytkownika - usuniecie danych--------

CREATE TRIGGER usuwanieUzytkownika ON Uzytkownicy
INSTEAD OF DELETE
AS

PRINT 'nie mozna usuwac uzytkownika!';

GO

DELETE FROM Uzytkownicy
WHERE idUzytkownika = 1

SELECT * FROM Uzytkownicy

DROP TRIGGER usuwanieUzytkownika




