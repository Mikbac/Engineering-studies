[![Build Status](https://travis-ci.org/Mikbac/Pracownia-programowania-przyrost-1-Quartz.svg?branch=master)](https://travis-ci.org/Mikbac/Pracownia-programowania-przyrost-1-Quartz)
# Pracownia-programowania-przyrost-1-Quartz

## Instrukcja:
Aby program działał poprawnie należy podać w osobnej linijce numer zadania, zaakceptować a następnie wpisać polecenie. Program umożliwia nadpisywanie odanych poleceń. Dopuszczalne jest podawanie zadań w innej kolejności, jednak wymagane jest, aby zostały podane wszystkie polecenia (np. 4,2,1,3 - jest dobrze; 4,1,3 - jest źle). <br /> 
Prykładowe polecenia, które zostaną zaakceptowane: <br />
3 <br />
test:  SELECT * FROM * WHERE * ORDER BY ** <br />
2 <br />
SELECT *123 FROM *123 123 123 13 WHERE *123 ORDER BY ** <br />
1 <br />
SELECT *123 FROM *123 123 123 13 WHERE *123 ORDER BY ** <br />

Prykładowe polecenie, które nie zostanie zaakceptowane(nie ma SELECT): <br />
1 <br />
zadanie3213 zadanie2 *123 FROM *123 123 123 13 WHERE *123 ORDER BY nazwiska <br /> 

Program dodatkowo posiada wbudowane testy i spełnia oniższe wymagania podane w opisanie zadania. <br />


## Opis zadania:
Użytkownik podaje numer zadania i w kolejnej linii zapytanie SQL (takie jak odpowiedź na zadanie z Baz Danych). Zakładamy, ze użytkownik zawsze wpisze poprawny numer jednak zapytanie SQL powinno być w prostu sposób parsowane (poprzez wyszukanie słów kluczowych SELECT, FROM, WHERE, ORDER BY i określenie czy podane były w danej kolejności, czy w złym porządku). W przypadku złego porządku odpowiedź nie jest zapisywana a użytkownik otrzymuje komunikat o błędzie.

Każde uruchomienie tworzy nowy plik odpowiedzi (program nie odczytuje żadnych danych).

W przypadku podania dwa razy tego samego numeru zadania odpowiedź jest nadpisywana.

co 30 sekund licząc od pełnych minut wpisane odpowiedzi zostają automatycznie zapisane do pliku odp.txt. Odpowiedzi w pliku mają być posortowane względem numerów zadań. (Uwaga! Użytkownik może w programie wpisywać odpowiedzi w dowolnej kolejności).

co pełną minutę od Pn-PT w godzinach 8.15-18.45 będzie pokazywać na ekranie komunikat "X minut do końca zajęć/przeryw".Godziny zajęć i przerw jak na WMI UAM.

Istnieją testy:  metody sprawdzającej poprawność zapytania SQL, oraz metody sprawdzającej czy dany czas jest przerwą czy zajęciami.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

* [Quartz](http://www.quartz-scheduler.org/) is a job scheduling library