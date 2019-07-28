-- = Lab. 1 =

-- === Zad. 1 ===

{{{
SELECT nazwisko, 
	(placa + ISNULL(dod_funkc, 0)) * 12  AS [placa roczna]
FROM   Pracownicy;
}}}

-- === Zad. 2 ===

{{{
SELECT TOP 1 nazwa, dataRozp, kierownik
FROM   Projekty
ORDER BY dataRozp DESC;
}}}

-- === Zad. 3 ===

{{{
SELECT nazwa,
	DATEDIFF ( MONTH , dataRozp , ISNULL(dataZakonczFakt , GETDATE ()) ) AS [czas trwania]
FROM Projekty;
}}}

-- === Zad. 4 ===

{{{
SELECT nazwisko, placa, stanowisko
FROM Pracownicy
WHERE (stanowisko = 'adiunkt' OR stanowisko = 'doktorant') AND placa > 1500;
}}}

-- === Zad. 5 ===

{{{
SELECT nazwisko
FROM Pracownicy
WHERE szef IS NULL;
}}}

-- === Zad. 6 ===

{{{
SELECT nazwa
FROM Projekty
WHERE nazwa LIKE '%web%';
}}}

-- === Zad. 7 ===

{{{
SELECT CONVERT(DECIMAL(4,2),CAST(2 AS DECIMAL) / 4);
}}}

-- = Lab. 2 =

-- === Zad. 1 ===

{{{
    SELECT Pracownicy.nazwisko,
       Pracownicy.placa,Pracownicy.stanowisko,Stanowiska.placa_min,Stanowiska.placa_max             
 FROM Pracownicy

       INNER JOIN Stanowiska 
               ON Stanowiska.nazwa = Pracownicy.stanowisko;
}}}

-- === Zad. 2 ===

{{{
  
SELECT Pracownicy.nazwisko,
       Pracownicy.placa,Pracownicy.stanowisko,Stanowiska.placa_min,Stanowiska.placa_max             
 FROM Pracownicy

       INNER JOIN Stanowiska 
               ON Stanowiska.nazwa = Pracownicy.stanowisko AND Pracownicy.stanowisko = 'doktorant'

 Where  ((Pracownicy.placa<Stanowiska.placa_min) OR (Pracownicy.placa>Stanowiska.placa_max));
 
}}}

-- === Zad. 3 ===

{{{
  
SELECT Pracownicy.nazwisko, Projekty.nazwa AS projekt
  FROM Realizacje

      INNER JOIN Pracownicy
                    ON Pracownicy.id = Realizacje.idPrac
        INNER JOIN Projekty
                    ON Projekty.id = Realizacje.idProj
        ORDER BY Pracownicy.nazwisko ASC;
    
}}}

-- === Zad. 4 ===

{{{
  
SELECT P1.nazwisko, 
       P2.nazwisko AS szef
  FROM Pracownicy P1
 
       JOIN Pracownicy P2
         ON P2.id = P1.szef;
}}}

-- === Zad. 5 ===

{{{
   
SELECT P1.nazwisko, 
       P2.nazwisko AS szef
  FROM Pracownicy P1
 
       LEFT OUTER JOIN Pracownicy P2
         ON P2.id = P1.szef;
}}}

-- === Zad. 6 ===

{{{
   SELECT Pracownicy.nazwisko
  FROM Pracownicy 

       LEFT OUTER JOIN Projekty
                    ON Pracownicy.id = Projekty.kierownik

 WHERE Projekty.kierownik IS NULL;
}}}

-- === Zad. 7 ===

{{{
    SELECT Pracownicy.nazwisko
  FROM Pracownicy

       LEFT OUTER JOIN Realizacje 
                    ON Pracownicy.id = Realizacje.idPrac
                       AND Realizacje.idProj = '10'

 WHERE Realizacje.idProj IS NULL;
}}}

-- === Zad. 8 ===

{{{
   SELECT P1.nazwisko, 
       P2.nazwisko
  FROM Pracownicy P1
 
       JOIN Pracownicy P2
         ON P1.nazwisko = P2.nazwisko
            AND P1.id > P2.id;
}}}

-- === Zad. 9 ===

{{{
  SELECT P1.placa_min AS adiunkt,
P2.placa_min AS doktorant,
P3.placa_min AS dziekan,
P4.placa_min AS profesor
FROM Stanowiska P1 CROSS JOIN Stanowiska P2 CROSS JOIN Stanowiska P3 CROSS JOIN Stanowiska P4
WHERE P1.nazwa='adiunkt'
AND P2.nazwa='doktorant'
AND P3.nazwa='dziekan'
AND P4.nazwa='profesor';
}}}

-- === Zad. 10 ===

{{{
INSERT INTO Projekty
VALUES ('analiza danych', GETDATE(), DATEADD(YY, 2, GETDATE()), NULL, 10, 100);

    SELECT Pracownicy.nazwisko
  FROM Pracownicy
		 RIGHT OUTER JOIN  Projekty
		 ON Pracownicy.id = Projekty.kierownik

		 
		 LEFT OUTER JOIN  Realizacje
		 ON Pracownicy.id = Realizacje.idPrac
		 AND Realizacje.idProj=Projekty.id

		 WHERE Realizacje.idProj IS NULL;
}}}

-- = Lab. 3 =

-- === Zad. 1 ===

{{{
SELECT nazwisko
FROM  Pracownicy
WHERE  placa > (SELECT placa 
FROM Pracownicy 
WHERE nazwisko = 'R�ycka');
}}}

-- === Zad. 2 ===

{{{
SELECT nazwisko
FROM   Pracownicy
WHERE  id != ALL (SELECT kierownik 
FROM Projekty);
}}}

-- === Zad. 3 ===

{{{
SELECT nazwisko
FROM   Pracownicy
WHERE  id != ALL (SELECT idPrac FROM Realizacje WHERE idProj = '10');
}}}


-- === Zad. 4 ===

{{{
SELECT nazwisko
FROM   Pracownicy
WHERE  id  = SOME (SELECT Realizacje.idPrac FROM Realizacje WHERE Realizacje.idproj = 
	ALL(SELECT Projekty.id FROM Projekty WHERE Projekty.nazwa = 'e-learning'));
}}}


-- === Zad. 5 ===

{{{
SELECT nazwisko, placa
FROM   Pracownicy P1
WHERE  placa > ALL(SELECT placa FROM Pracownicy P2 WHERE P1.placa<P2.placa);
}}}


-- === Zad. 6 ===

{{{
SELECT nazwisko
FROM   Pracownicy p
WHERE  p.id = some (SELECT idprac
                     FROM   Realizacje
                    WHERE idproj=some(select idproj from realizacje where idprac=szef));
}}}


-- === Zad. 7 ===

{{{
SELECT nazwisko
FROM   Pracownicy p1
WHERE NOT  EXISTS (SELECT kierownik
	FROM   Projekty
	WHERE p1.id=kierownik);
}}}


-- === Zad. 8 ===

{{{
DELETE FROM Projekty WHERE id > 40;

SELECT  DISTINCT nazwisko
  FROM  Pracownicy PS1 
  WHERE NOT EXISTS
       (SELECT *
          FROM Projekty
         WHERE NOT EXISTS
               (SELECT *
                  FROM Realizacje  PS2
                 WHERE (PS1.id = PS2.idPrac)
                   AND (PS2.idProj = Projekty.id)));
}}}

-- = Lab. 4 =

-- === Zad. 1 ===
{{{
SELECT AVG(placa) 'srednia',
COUNT(*)   'liczba' 
FROM   Pracownicy
JOIN Realizacje ON Realizacje.idPrac=Pracownicy.id
JOIN Projekty ON Projekty.id=Realizacje.idProj
WHERE Projekty.nazwa='e-learning';
}}}


-- === Zad. 2 ===
{{{
SELECT szef 'szef', MIN(placa)   'minimum'  ,MAX(placa) 'maximum'
FROM   Pracownicy
WHERE szef IS NOT NULL
GROUP BY szef;
}}}


-- === Zad. 3 ===
{{{
SELECT  nazwisko, placa
FROM     Pracownicy
WHERE placa =(SELECT MAX(placa) FROM Pracownicy);
}}}


-- === Zad. 4 ===
{{{
SELECT  p1.stanowisko, p1.nazwisko, p1.placa
FROM     Pracownicy p1
WHERE p1.placa = (SELECT MAX(p2.placa) FROM Pracownicy p2 GROUP BY p2.stanowisko  HAVING MAX(p2.placa)=p1.placa);
}}}


-- === Zad. 5 ===
{{{
SELECT nazwisko, COUNT(nazwisko) 'licz_proj'
FROM Pracownicy
JOIN Realizacje ON id=idprac
WHERE stanowisko!='profesor'
GROUP BY nazwisko
HAVING COUNT(nazwisko)>1;
}}}


-- === Zad. 6 ===
{{{
SELECT P1.nazwisko, COUNT(P1.nazwisko) 'licz projektow'
FROM Pracownicy P1
JOIN Realizacje R1 ON P1.id=R1.idprac
GROUP BY P1.nazwisko
HAVING COUNT(P1.nazwisko) = ALL (SELECT COUNT(P2.nazwisko) 
		FROM Pracownicy P2
		JOIN Realizacje R2 ON P2.id=R2.idprac 
		GROUP BY P2.nazwisko 
		HAVING COUNT(P1.nazwisko)<COUNT(P2.nazwisko));
}}}


-- === Zad. 7 ===
{{{
SELECT nazwisko,placa
FROM Pracownicy
WHERE placa>(SELECT placa FROM Pracownicy p1 WHERE 3=
			(SELECT COUNT(DISTINCT placa) FROM Pracownicy p2 WHERE p2.placa>p1.placa));
}}}


-- === Zad. 8 ===
{{{
SELECT nazwisko,COUNT(nazwisko) AS 'liczba'
FROM Pracownicy
GROUP BY nazwisko
HAVING COUNT(nazwisko)>=2;
}}}


-- === Zad. 9 ===
{{{
SELECT nazwa, dataZakonczPlan AS DataZakonczenia, 'projekt trwa' AS 'Status'
FROM Projekty p1
WHERE dataZakonczFakt IS NULL 

UNION -- ALL

SELECT nazwa, dataZakonczPlan AS DataZakonczenia, 'projekt zakonczony' AS 'Status'
FROM Projekty p1
WHERE dataZakonczFakt IS NOT NULL

}}}


-- === Zad. 10 ===
{{{
SELECT nazwisko
FROM   Pracownicy

EXCEPT

SELECT nazwisko
FROM   Pracownicy
JOIN Projekty ON Pracownicy.id=Projekty.kierownik;
}}}


-- === Zad. 11 ===
{{{
SELECT   nazwisko, 
        (placa)/(SELECT AVG(placa) FROM Pracownicy)*100 AS 'procent sredniej'
FROM     Pracownicy
}}}


-- === Zad. 12 ===
{{{
SELECT nazwisko, SUM(t.zarobki) AS 'zarobki'
FROM(
SELECT nazwisko,SUM( (godzin)*stawka*datediff(ww,dataRozp,dataZakonczFakt)) AS 'zarobki'
FROM Realizacje  
LEFT JOIN Pracownicy ON Pracownicy.id=idPrac
LEFT JOIN Projekty ON Projekty.iD=idProJ
WHERE dataZakonczFakt IS NOT NULL
GROUP BY nazwisko
UNION
SELECT nazwisko,SUM( (godzin)*stawka*datediff(ww,dataRozp,GETDATE())) AS 'zarobki'
FROM Realizacje  
LEFT JOIN Pracownicy ON Pracownicy.id=idPrac
LEFT JOIN Projekty ON Projekty.iD=idProJ
WHERE dataZakonczFakt IS  NULL
GROUP BY nazwisko) AS t
GROUP BY nazwisko
ORDER BY zarobki DESC;
}}}

-- = Lab. 5 =
-- === Zad. 1 ===
{{{
CREATE VIEW AktualneProjekty(nazwa, kierownik, [licz pracownikow])
AS
(
SELECT n1.nazwa, n2.nazwisko, n3.[licz pracownikow]
FROM Projekty AS n1 
INNER JOIN Pracownicy AS n2 ON n1.kierownik = n2.id 
INNER JOIN (SELECT n4.idProj, COUNT(*) AS 'licz pracownikow' FROM Realizacje AS n4 GROUP BY n4.idProj) AS n3 ON n3.idProj=n1.id 
WHERE n1.dataZakonczFakt IS NULL
);
GO

SELECT * 
FROM   AktualneProjekty;

SELECT TOP 1 nazwa,  [licz pracownikow]
FROM [AktualneProjekty]
ORDER BY [licz pracownikow] DESC;
}}}

-- === Zad. 2 ===
{{{
SELECT 'Mediana' [rozwiazanie];
}}}

-- === Zad. 3 ===
{{{
WITH CTE_Podwladni(nazwisko, id, poziom)
AS
(
    SELECT nazwisko,id,0
    FROM   Pracownicy 
    WHERE  nazwisko='Mielcarz'

    UNION ALL                    

    SELECT P.nazwisko,P.id,T.poziom + 1
    FROM   Pracownicy P 
    JOIN CTE_Podwladni T ON P.szef = T.id
)
SELECT  nazwisko,poziom
FROM    CTE_Podwladni
WHERE poziom != '0';

}}}

-- === Zad. 4 ===
{{{
WITH CTE_Nadwladni ( nazwisko, szef, poziom)
        AS
        (
				SELECT  nazwisko, szef, 0
                FROM    pracownicy 
                WHERE   nazwisko='Andrzejewicz'  
				                         
				UNION ALL

                SELECT  p.nazwisko, p.szef, cte.poziom+1
                FROM    pracownicy P 
				JOIN CTE_Nadwladni CTE ON p.id = CTE.szef 
        )
        SELECT  nazwisko,  poziom
        FROM    CTE_Nadwladni
		WHERE poziom != '0';
}}}

-- = Lab. 6 =
-- === Zad. 1 ===
{{{

CREATE TABLE Uczestnicy
(
    PESEL		VARCHAR(30) PRIMARY KEY,
    nazwisko	VARCHAR(30) NOT NULL,
    miasto		VARCHAR(30) DEFAULT 'Pozna�'

);


CREATE TABLE Kursy
(
    Kod			INT IDENTITY(1,1) PRIMARY KEY,
    nazwa		VARCHAR(30) UNIQUE ,
    liczba_dni  INT, CONSTRAINT liczbaDni CHECK (liczba_dni>=1 and liczba_dni<=5),
	cena		AS liczba_dni * 1000

);

CREATE TABLE Udzial
(
    uczestnik	VARCHAR(30) REFERENCES Uczestnicy(PESEL),
    kurs		INT REFERENCES Kursy(Kod),
    data_od		DATE ,
	data_do		DATE, CONSTRAINT date_ck CHECK (data_do > data_od),
	[status]	VARCHAR(30) CHECK ([status] IN ('w trakcie', 'ukonczony', 'nie ukonczony'))
	
);
}}}


-- === Zad. 2 ===
{{{
/* -- */
}}}

-- === Zad. 3 ===
{{{
ALTER TABLE Uczestnicy
ADD CONSTRAINT og1  CHECK (PESEL LIKE REPLICATE('[0-9]', 11));

ALTER TABLE Udzial
ADD ID INT PRIMARY KEY;

ALTER TABLE Kursy
DROP CONSTRAINT liczbaDni;

}}}

-- === Zad. 4 ===
{{{
-- wersja z CREATE SEQUENCE

CREATE SEQUENCE generatorWartosci 
AS INT
START WITH 1  
INCREMENT BY 1; 


CREATE TABLE Kursy
(
    Kod			INT  DEFAULT (NEXT VALUE FOR generatorWartosci)  PRIMARY KEY,
    nazwa		VARCHAR(30) UNIQUE ,
    liczba_dni  INT, CONSTRAINT liczbaDni CHECK (liczba_dni>=1 and liczba_dni<=5),
	cena		AS liczba_dni * 1000

);

-- Przyk�adowa warto��:
-- INSERT INTO Kursy(nazwa,liczba_dni) VALUES('PINKU BIRADAR',1),('MONTY BIRADAR',2)

-- wersja z IDENTITY(i, k)

CREATE TABLE Kursy
(
    Kod			INT IDENTITY(1,1) PRIMARY KEY,
    nazwa		VARCHAR(30) UNIQUE ,
    liczba_dni  INT, CONSTRAINT liczbaDni CHECK (liczba_dni>=1 and liczba_dni<=5),
	cena		AS liczba_dni * 1000

);
}}}

-- === Zad. 5 ===
{{{
IF OBJECT_ID('Udzial', 'U') IS NOT NULL 
DROP TABLE Udzial;
	
IF OBJECT_ID('Kursy', 'U') IS NOT NULL 
DROP TABLE Kursy;
	
IF OBJECT_ID('Uczestnicy', 'U') IS NOT NULL 
DROP TABLE Uczestnicy;
}}}

-- = Lab. 8 =
-- === Zad. 1 ===
{{{
SELECT *
INTO   KursyKopia
FROM   Kursy;

--tabela KursyKopia zawiera r�wnie� kopie ogranicze�
}}}

-- === Zad. 2 ===
{{{
--A)
INSERT INTO Uczestnicy(PESEL,nazwisko,email) VALUES
	('97030405060', 'Bachorz','bachorz@bachorz.pl');
--B) 
--zaobserwowane
--C)	
INSERT INTO Udzial(uczestnik, kurs, data_od, data_do, [status]) 
	SELECT '97030405060' , Kod , '2017-10-20' , '2017-10-22' , 'ukonczony'  FROM Kursy WHERE nazwa='Analiza danych' ;


}}}

-- === Zad. 3 ===
{{{
UPDATE Kursy
SET 
liczba_dni=liczba_dni+1
WHERE nazwa LIKE '%MySQL%';

ALTER TABLE Uczestnicy
ADD Rok_urodzenia CHAR(4);

UPDATE Uczestnicy
SET
Uczestnicy.Rok_urodzenia = '19' + SUBSTRING(Uczestnicy.PESEL,1,2);

}}}

-- === Zad. 4 ===
{{{
UPDATE t1
SET t1.miasto=t2.forma_poprawna 
FROM 
Uczestnicy AS t1
INNER JOIN MapujMiasta AS t2 ON t1.miasto = t2.forma_niepoprawna
WHERE t2.forma_niepoprawna IS NOT NULL 


}}}

-- === Zad. 5 ===
{{{
--A)
DELETE FROM Udzial 
WHERE       uczestnik =(SELECT PESEL FROM Uczestnicy WHERE  nazwisko = 'Jakubowicz');

DELETE FROM Uczestnicy 
WHERE       nazwisko = 'Jakubowicz';


--B)
ALTER TABLE Udzial
ADD CONSTRAINT  mod1 
FOREIGN KEY (uczestnik)
REFERENCES Uczestnicy(pesel)
ON DELETE SET NULL;

DELETE FROM Uczestnicy 
WHERE       nazwisko = 'Jakubowicz';


--C)
ALTER TABLE Udzial
ADD CONSTRAINT  mod1 
FOREIGN KEY (uczestnik)
REFERENCES Uczestnicy(pesel)
ON DELETE CASCADE;

DELETE FROM Uczestnicy 
WHERE       nazwisko = 'Jakubowicz';

}}}

-- === Zad. 6 ===
{{{
MERGE Uczestnicy
USING UczestnicyAktualnie
ON    (Uczestnicy.PESEL = UczestnicyAktualnie.PESEL)
WHEN MATCHED THEN
      UPDATE SET Uczestnicy.nazwisko = UczestnicyAktualnie.nazwisko
WHEN NOT MATCHED THEN
     INSERT (PESEL, nazwisko, miasto, email)
     VALUES (UczestnicyAktualnie.PESEL, UczestnicyAktualnie.nazwisko, UczestnicyAktualnie.miasto, UczestnicyAktualnie.email)
OUTPUT deleted.*, inserted.*;

SELECT * FROM Uczestnicy;
}}}

-- = Lab. 9 =
-- === Zad. 1 ===

[[attachment:9.1]]<<BR>>
[[attachment:9.1+Autorzy]]
=== Zad. 2 ===
 kartka: 
[[attachment:9.2]]

=== Zad. 3 ===
RM: <<BR>>
[[attachment:9.2-Oracle]] <<BR>>
kod SQL:  <<BR>>
{{{


CREATE TABLE Grupy 
    (
     id_grupy INTEGER NOT NULL , 
     wyk�adowca VARCHAR (30) , 
     dzien_tygodnia VARCHAR , 
     godzina TIME , 
     Przedmioty_Id_przedmiotu INTEGER NOT NULL 
    )
    ON "default"
GO

ALTER TABLE Grupy ADD CONSTRAINT Grupy_PK PRIMARY KEY CLUSTERED (id_grupy)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    GO

CREATE TABLE Przedmioty 
    (
     Id_przedmiotu INTEGER NOT NULL , 
     punkty_ECTS INTEGER 
    )
    ON "default"
GO

ALTER TABLE Przedmioty ADD CONSTRAINT Przedmioty_PK PRIMARY KEY CLUSTERED (Id_przedmiotu)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    GO

CREATE TABLE Studenci 
    (
     indeks INTEGER NOT NULL , 
     imie VARCHAR (30) , 
     nazwisko VARCHAR (30) , 
     adres VARCHAR (30) 
    )
    ON "default"
GO

ALTER TABLE Studenci ADD CONSTRAINT Studenci_PK PRIMARY KEY CLUSTERED (indeks)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    GO

CREATE TABLE Wymagania 
    (
     Nr_wymagania INTEGER NOT NULL , 
     przedmiot_wymagany INTEGER , 
     Przedmioty_Id_przedmiotu INTEGER NOT NULL 
    )
    ON "default"
GO

ALTER TABLE Wymagania ADD CONSTRAINT Wymagania_PK PRIMARY KEY CLUSTERED (Nr_wymagania)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    GO

CREATE TABLE Zapisy 
    (
     Nr_zapisu INTEGER NOT NULL , 
     zatwierdzone VARCHAR (30) , 
     Studenci_indeks INTEGER NOT NULL , 
     Grupy_id_grupy INTEGER NOT NULL 
    )
    ON "default"
GO

ALTER TABLE Zapisy ADD CONSTRAINT Zapisy_PK PRIMARY KEY CLUSTERED (Nr_zapisu)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    GO

CREATE TABLE Zrealizowane_Przedmioty 
    (
     Nr_realizacji INTEGER NOT NULL , 
     Studenci_indeks INTEGER NOT NULL , 
     Przedmioty_Id_przedmiotu INTEGER NOT NULL 
    )
    ON "default"
GO

ALTER TABLE Zrealizowane_Przedmioty ADD CONSTRAINT Zrealizowane_Przedmioty_PK PRIMARY KEY CLUSTERED (Nr_realizacji)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    GO

ALTER TABLE Grupy 
    ADD CONSTRAINT Grupy_Przedmioty_FK FOREIGN KEY 
    ( 
     Przedmioty_Id_przedmiotu
    ) 
    REFERENCES Przedmioty 
    ( 
     Id_przedmiotu 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE Wymagania 
    ADD CONSTRAINT Wymagania_Przedmioty_FK FOREIGN KEY 
    ( 
     Przedmioty_Id_przedmiotu
    ) 
    REFERENCES Przedmioty 
    ( 
     Id_przedmiotu 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE Zapisy 
    ADD CONSTRAINT Zapisy_Grupy_FK FOREIGN KEY 
    ( 
     Grupy_id_grupy
    ) 
    REFERENCES Grupy 
    ( 
     id_grupy 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE Zapisy 
    ADD CONSTRAINT Zapisy_Studenci_FK FOREIGN KEY 
    ( 
     Studenci_indeks
    ) 
    REFERENCES Studenci 
    ( 
     indeks 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE Zrealizowane_Przedmioty 
    ADD CONSTRAINT Zrealizowane_Przedmioty_Przedmioty_FK FOREIGN KEY 
    ( 
     Przedmioty_Id_przedmiotu
    ) 
    REFERENCES Przedmioty 
    ( 
     Id_przedmiotu 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE Zrealizowane_Przedmioty 
    ADD CONSTRAINT Zrealizowane_Przedmioty_Studenci_FK FOREIGN KEY 
    ( 
     Studenci_indeks
    ) 
    REFERENCES Studenci 
    ( 
     indeks 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO


}}}
-- = Lab. 10 =
-- === Zad. 1 ===
{{{
DECLARE @zdanie VARCHAR(1000) = 'Ala ma  kota   i psa i                 mopsa';
DECLARE @separator CHAR(1) = ' ';
DECLARE @dlugosc INT;
DECLARE @dlugosc1 INT;
DECLARE @liczba INT=1;
DECLARE @odleglosc INT;
DECLARE @TabelaWyrazow TABLE
(
    wyrazy   VARCHAR(20)
);

SELECT @dlugosc=LEN(@zdanie)
SELECT @dlugosc1=LEN(@zdanie)

WHILE(@dlugosc1>@liczba)
BEGIN
		SELECT @dlugosc=LEN(@zdanie)
		IF((LEFT(@zdanie,	CHARINDEX ( @separator , @zdanie)))!=@separator)
		INSERT INTO @TabelaWyrazow 
		SELECT  LEFT(@zdanie,	CHARINDEX ( @separator , @zdanie))
		SELECT @zdanie = RIGHT(@zdanie,@dlugosc-CHARINDEX ( @separator , @zdanie))
		SELECT @liczba=@liczba+1
		IF ((CHARINDEX ( @separator , @zdanie))=0)
		SELECT @liczba=@dlugosc1;
END;

INSERT INTO @TabelaWyrazow 
SELECT  LEFT(@zdanie,@dlugosc)

SELECT * FROM @TabelaWyrazow;
}}}

-- === Zad. 2 ===
{{{
CREATE PROCEDURE Wstaw_pracownika (
        @nazwisko VARCHAR(20) ,
        @stanowisko VARCHAR(20) = 'doktorant' ,
        @szef VARCHAR(20) ,
        @placa MONEY,
        @zatrudniony DATETIME = NULL)
AS
       
        DECLARE @szef_id INT
        DECLARE @pracownik_id INT
		
        
        IF NOT EXISTS (SELECT * FROM Pracownicy WHERE nazwisko=@szef)
        BEGIN
                RAISERROR (N'Bledne nazwisko szefa: %s', 
                        16, 
                        1 
						
                        
                )
				RETURN 
        END

        
        IF NOT EXISTS (SELECT * FROM Pracownicy WHERE stanowisko=@stanowisko)
        BEGIN
                RAISERROR (N'Bledne numer oddzia�u: %d', 
                        16, 
                        1 
                       
					  
                )
				 RETURN 
        END

   
        SET @szef_id = (SELECT id FROM Pracownicy WHERE nazwisko = @szef)
        

        SET @pracownik_id = (SELECT ISNULL(MAX(ID),0)+10 FROM Pracownicy)
        

        IF @Zatrudniony IS NULL
                        SET @Zatrudniony = GETDATE()

        
        INSERT INTO Pracownicy (ID, Nazwisko, szef, placa, stanowisko, Zatrudniony)
        VALUES (@pracownik_id, @nazwisko, @szef_id, @placa,@stanowisko,@zatrudniony)
		GO

}}}

-- === Zad. 3 ===
{{{
CREATE PROCEDURE Podwyzka
        @proc  TINYINT = 10,
        @kwota MONEY OUTPUT
AS
BEGIN

	SELECT @kwota=0


SELECT @kwota = @kwota+placa / @proc
                FROM   Pracownicy 
				WHERE  placa<2000

	UPDATE Pracownicy
	SET placa = placa * @proc/100 + placa
                FROM   Pracownicy
                WHERE  placa<2000

END;

}}}

-- = Lab. 11 =
-- === Zad. 1 ===
{{{
CREATE FUNCTION LiczLata
(
    @jakasData DATETIME
)
    RETURNS INT
AS
BEGIN
    RETURN DATEDIFF(year,@jakasData,GETDATE())
END;


SELECT nazwisko, 
       dbo.LiczLata(zatrudniony) AS 'liczba lat, jaka min�a od jego zatrudnienia'
FROM   Pracownicy;


}}}

-- === Zad. 2 ===
{{{
CREATE FUNCTION StazPracy
(
    @lata INT
)
    RETURNS TABLE
AS

   RETURN SELECT nazwisko
           FROM   Pracownicy
           WHERE  dbo.LiczLata(zatrudniony)>@lata;



SELECT * 
FROM   StazPracy(36);
}}}

=== Zad. 3 ===
{{{
CREATE FUNCTION DzielZdanie
(
    @zdanie VARCHAR(1000),
	@separator VARCHAR(1)
)
    RETURNS @TabelaWyrazow TABLE(kursy VARCHAR(100))
	
AS
BEGIN

DECLARE @dlugosc INT
DECLARE @dlugosc1 INT
DECLARE @liczba INT=1
DECLARE @odleglosc INT
/*DECLARE @TabelaWyrazow TABLE
(
    wyrazy   VARCHAR(20)
);
*/
SELECT @dlugosc=LEN(@zdanie)
SELECT @dlugosc1=LEN(@zdanie)
--SELECT @liczba=1

IF ((CHARINDEX ( @separator , @zdanie)))=0
BEGIN

INSERT INTO @TabelaWyrazow 
SELECT  LEFT(@zdanie,@dlugosc)

SELECT @liczba=@dlugosc1+2
RETURN
END;



WHILE(@dlugosc1>=@liczba)
BEGIN
                SELECT @dlugosc=LEN(@zdanie)
                IF((LEFT(@zdanie,       CHARINDEX ( @separator , @zdanie)))!=@separator)
                INSERT INTO @TabelaWyrazow 
                SELECT  LEFT(@zdanie,   CHARINDEX ( @separator , @zdanie)-1)
                SELECT @zdanie = RIGHT(@zdanie,@dlugosc-CHARINDEX ( @separator , @zdanie)-1)
                SELECT @liczba=@liczba+1
                IF ((CHARINDEX ( @separator , @zdanie))=0)
                SELECT @liczba=@dlugosc1+1;
END;

INSERT INTO @TabelaWyrazow 
SELECT  LEFT(@zdanie,@dlugosc)
RETURN
END;



INSERT INTO Szkolenia_Znormalizowane
SELECT S.PESEL_uczestnika  , C.kursy
FROM Szkolenia_Nieznormalizowane S
     CROSS APPLY (SELECT *
                  FROM   DzielZdanie(kursy,',')) C;

SELECT *
FROM Szkolenia_Znormalizowane;	

}}}

-- = Lab. 12 =
-- === Zad. 1.a ===
{{{
CREATE TRIGGER zadanie1a ON Pracownicy
AFTER UPDATE
AS
IF ((SELECT placa FROM deleted)>(SELECT placa FROM inserted)) 
	BEGIN

      PRINT 'uzytkownik probowal obni�y� pensj� pracownikowi!';
			 SELECT * FROM deleted;
		ROLLBACK;
	 END
	  
GO
}}}
-- === Zad. 1.b ===
{{{
CREATE TRIGGER zadanie1b ON Pracownicy
AFTER UPDATE
AS
IF EXISTS (SELECT * FROM deleted w1 WHERE w1.placa >(SELECT w2.placa FROM inserted w2 WHERE w1.id=w2.id))
	BEGIN

      PRINT 'uzytkownik probowal obni�y� pensj� WSZYSTKIM pracownikom!';
			 SELECT * FROM deleted;
			 ROLLBACK
	 END
GO
}}}
-- === Zad. 2 z rekurencja ===
{{{
CREATE TRIGGER zadanie2b ON Pracownicy
AFTER UPDATE
AS
	BEGIN

	DECLARE @procent float
	SET @procent = 1-(SELECT placa FROM deleted p1 WHERE p1.placa != (SELECT p2.placa FROM inserted p2 WHERE p1.id=p2.id))/(SELECT placa FROM inserted p1 WHERE p1.placa != (SELECT p2.placa FROM deleted p2 WHERE p1.id=p2.id))

      PRINT 'uzytkownik probowal modyfikowa� pensj� pracownikom!' ;
	  
	
	 
	UPDATE Pracownicy
	SET placa = placa+placa*@procent
	FROM Pracownicy
	
	
			
	 END
GO
}}}
-- === Zad. 2 bez rekurencji ===
{{{
CREATE TRIGGER zadanie2a ON Pracownicy
AFTER UPDATE
AS
	BEGIN

	DECLARE @procent float
	SET @procent = 1-(SELECT placa FROM deleted p1 WHERE p1.placa != (SELECT p2.placa FROM inserted p2 WHERE p1.id=p2.id))/(SELECT placa FROM inserted p1 WHERE p1.placa != (SELECT p2.placa FROM deleted p2 WHERE p1.id=p2.id))

      PRINT 'uzytkownik probowal modyfikowa� pensj� pracownikom!' ;
	  
	
	 
	UPDATE Pracownicy
	SET placa = placa+placa*@procent
	FROM Pracownicy
	WHERE szef=(SELECT id FROM deleted p1 WHERE p1.placa != (SELECT p2.placa FROM inserted p2 WHERE p1.id=p2.id)) 

	
			
	 END
GO
}}}
-- === Zad. 2 odpowiedzi ===
{{{
-- 1. Zostanie uruchomiony trigger z zadania 1. Najpierw zadzia�a trigger z zadania 1, a nast�pnie z zadania 2.

-- 2. Je�eli nie zostanie to w �aden spos�b ograniczone to tak.

}}}
-- === Zad. 3 ===
{{{
CREATE TRIGGER zadanie3 ON Projekty
AFTER DELETE
AS
DECLARE @idd INT



SET @idd = (SELECT id FROM deleted)


      PRINT 'uzytkownik probowal usun�� projekt!';
          PRINT @idd;
                ROLLBACK;

                
          UPDATE Projekty
          SET status=0

        WHERE  id = @idd;

  
GO
}}}

-- = Lab. 13 =
-- === Zad. 1 ===
{{{
a)	Tak
b)	Klienci + Transakcje + Asortyment
c)	Na etapie Clustered Index Scan, na pocz�tku
d)	Przez sortowanie
e)	Wersja z podzapytaniem potrzebuje wi�cej czasu, pami�ci i CPU.
}}}
-- === Zad. 2 ===
{{{
-- 1)	Merge Join - poniewa� s� ��czone dwie posortowane kolumny klient i id_klienta

-- 2)	Hash Match - poniewa� mamy tudaj du�y nieposortowany zbi�r bez indeks�w; asortyment w Transakcje

-- 3)	Nested Loop - poniewa� ��czy ma�y i du�y zbi�r asortyment  i id_asortymentu

}}}
-- === Zad. 3 ===
{{{
Transakcje - nie ma
Asortyment - nie ma
Klienci - ma
}}}
-- === Zad. 4 ===
{{{
ALTER TABLE Transakcje_IDX 
ALTER COLUMN id_transakcji VARCHAR(13) NOT NULL

ALTER TABLE Transakcje_IDX
ADD CONSTRAINT pk_trans PRIMARY KEY NONCLUSTERED(id_transakcji)
}}}
-- === Zad. 5 ===
{{{
CREATE CLUSTERED INDEX idx_trans
ON Transakcje_IDX(data_transakcji)

-- tak

}}}
-- === Zad. 6 ===
{{{
--1
CREATE INDEX idx_kli_woj_1 ON Klienci_IDX(wojewodztwo)

--2
DROP INDEX idx_kli_woj_1 ON Klienci_IDX
CREATE INDEX idx_kli_woj_1 ON Klienci_IDX(wojewodztwo) INCLUDE (nazwisko, imie)
}}}
-- === Zad. 7 ===
{{{


(SELECT S.nazwa, 
       C.nazwisko, 
       C. placa
FROM   Stanowiska S
       CROSS APPLY (SELECT   *
                    FROM     Pracownicy P
                    WHERE    p.stanowisko = s.nazwa AND placa = all(SELECT pp.placa FROM Pracownicy pp WHERE pp.placa<=p.placa and p.stanowisko!=pp.stanowisko)
                   )  c 
)	
UNION
(

SELECT S.nazwa, 
       C.nazwisko, 
       C. placa
FROM   Stanowiska S
       CROSS APPLY (SELECT   *
                    FROM     Pracownicy P
                    WHERE    p.stanowisko = s.nazwa AND placa != some(SELECT pp.placa FROM Pracownicy pp WHERE pp.placa<p.placa and p.stanowisko=pp.stanowisko)
                   ) C


)
UNION	
	 (  SELECT S.nazwa, 
       C.nazwisko, 
       C. placa
FROM   Stanowiska S
       CROSS APPLY (SELECT   *
                    FROM     Pracownicy P
                    WHERE    p.stanowisko = s.nazwa AND placa = all(SELECT pp.placa FROM Pracownicy pp WHERE pp.placa>p.placa and p.stanowisko=pp.stanowisko)
                   ) C    
		); 	


}}}

-- = Lab. 14 =
-- === Zad. 1 ===
{{{
-- Scenariusz 1 - brudny odczyt
-- Scenariusz 2 - powt�rne czytanie
-- Scenariusz 3 - fantom
}}}

-- === Zad. 2 ===
{{{
-- -------------------------------READ UNCOMMITTED-------------------------------------- 

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

BEGIN TRAN T1;  --1
	
UPDATE Wycieczki SET cena = 2000 WHERE cel = 'Bangkok'; --3
	
UPDATE Wycieczki SET cena = 2000 WHERE cel = 'Ateny'; --5
	
COMMIT; --7


--  ------- 

BEGIN TRAN T2; --2

UPDATE Wycieczki SET cena = 2000 WHERE cel = 'Ateny'; --4

UPDATE Wycieczki SET cena = 2000 WHERE cel = 'Bangkok';  --6
 
COMMIT; --8




-- -------------------------------READ COMMITTED-------------------------------------- 
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

BEGIN TRAN T1; --1

UPDATE Bilety set cena = 2000 where klient = 'Kowalski'; --2

SELECT * FROM Bilety; --5

UPDATE Bilety set cena = 1000 where klient = 'Nowak'; --7

COMMIT; --8
--  ------- 

BEGIN TRAN T2;--3

UPDATE Bilety set cena = 3000 where klient = 'Nowak'; --4

COMMIT;--6


-- -------------------------------REPEATABLE READ-------------------------------------- 
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

BEGIN TRAN T1; --1

INSERT INTO Bilety VALUES('Wojtowicz', 'Ateny',   1200); --2

SELECT * FROM Bilety; --5

COMMIT; --7
/* ------- */

BEGIN TRAN T2; -- 3

DELETE FROM Bilety WHERE klient = 'Nowak'; --4

COMMIT;--6

-- -------------------------------SERIALIZABLE-------------------------------------- 
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

BEGIN TRAN T1; --1

INSERT INTO Bilety VALUES('Kruspe', 'Ateny',   6000); --2

UPDATE Bilety set cena = 1000 where klient = 'Kruspe';--6

COMMIT;--8
-- ------- 

BEGIN TRAN T2;--3

DELETE FROM Bilety WHERE klient = 'Kowalski';--4

DELETE FROM Bilety WHERE klient = 'Kruspe';--5

COMMIT; --7



--    Jak SQL Server reaguje na zakleszczenie?   

-- Pojawia si� b��d. Blokada jest zale�na od poziomu izolacji.

