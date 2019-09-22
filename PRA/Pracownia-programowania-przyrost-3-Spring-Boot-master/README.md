[![Build Status](https://travis-ci.org/Mikbac/Pracownia-programowania-przyrost-3-Spring-Boot.svg?branch=master)](https://travis-ci.org/Mikbac/Pracownia-programowania-przyrost-3-Spring-Boot)

# Pracownia-programowania-przyrost-3-Spring-Boot


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

* [Spring Boot](http://spring.io/projects/spring-boot) is Spring's convention-over-configuration solution for creating stand-alone, production-grade Spring-based Applications that you can "just run".

* [HSQLDB](http://hsqldb.org/) is the leading SQL relational database software.


## Aplikacja serwerowa:

Aplikacja serwerowa udostępnia utworzony wcześniej model danych w zakresie : dodawania, usuwania, edytowania, wyszukiwania (wylistowania) obiektów z bazy danych.
Dodatkowo aplikacja posiada endpointy udostępniające dla każdej tabeli jedną prostą statystykę.
Aplikacja serwerowa korzysta z silnika bazy danych in-memory HSQL.
Komunikacja pomiędzy serwerem, a frontendem odbywa się poprzez REST API.

## Końcowy projekt wymagania:

Stworzenie aplikacji serwerowej oraz niezależnego klienta wykorzystujących REST API.

Aplikacja serwerowa powinna udostępniać utworzony wcześniej model danych w zakresie : dodawania, usuwania, edytowania, wyszukiwania (wylistowania) obiektów z bazy danych. Wszelkie założenia co do modelu danych opisanych dla przyrostu II obowiazują nadal.

Dodatkowo aplikacja powinna posiadać endpointy udostępniające dla każdej tabeli jedną prostą statystykę : liczbę elementów, zbiór kategorii (jeżeli ma sens), średnią (jeżeli ma sens), najlepszy zespół (jeżeli ma sens) etc.

Aplikacja serwerowa musi korzystać z silnika bazy danych takiego jak PostgreSQL, MySQL, MSSQL czy podobne. Komunikacja pomiędzy serwerem a frontendem musi odbywać się poprzez REST API.

## SpringBootWebApplication

http://localhost:8080/swagger-ui.html#/
