
# Przed rozpoczęciem pracy z programem zaleca się zapoznanie z instrukcjami (warto przeczytać też to co jest w celach (pod instrukcjami))
- aby działała serializacji należy ustawić "<property name="hibernate.hbm2ddl.auto" value="validate"/.>"
- aby działała deserializacji należy ustawić "<property name="hibernate.hbm2ddl.auto" value="create"/.>"
(usunać . )
- klasami, które można wywowłać są: Main, JacksonDeSerialization, JacksonSerializationZObiektyTest1, JacksonSerializationZObiektyZBazyDanych

- testy sprawdzają poprawność serializacji



## Instrukcje(czyli co warto widzieć, żeby wszystko działało):



## Cele do realizacji ( (+) oznacza zrealizowane, (-) oznacza niezrealizowane):


(+) utworzenie jakichś danych<br />
(+) dwie relacje OneToOne <br />
(+) dwie relacje OneToMany lub ManyToMany lub ManyToOne (tu ManyToOne)<br />
(+) 5 zapytań (każde jako odrębna metoda w klasie)<br />
(+) jedno stronnicowanie<br />
(+) pole do zapisu daty - najlepiej (tu DateTime)<br />
(+) czytać i zapisywać zamodelowane dane z i do plików typu XML<br />
(+) czytać i zapisywać zamodelowane dane z i do plików typu JSON <br />
(+) zapisywanie/odczytywanie z bazy danych XML<br />
(+) zapisywanie/odczytywanie z bazy danych JSON <br />
(+) stworzyć testy  odczyt danych z pliku XML / JSON i zapis do bazy, odczyt danych z bazy i zapis do plików XML / JSON. Należy stworzyć odpowiednie pliki XML / JSON dla ww testów.<br />


(+) dodany plik README.md<br />
(+) dodany .gitignore<br />
(+) testy w unit tests<br />
(+) dodać .travis.yml<br />




## Opis projektu:

Stworzenie programu, który będzie zapisywał i odcztywał określony model danych.

Kazda osoba musi obmyślić jakiego typu dane chce przechowywać w końcowej aplikacji (informacje o osobach, filmach, autach, piłkarzach, komputerach etc.) Należy zamodelować te dane poprzez określenie pól i zależności między nimi.

Dane musza zawierać co najmniej dwie relacje OneToOne i dwie relacje OneToMany (lub ManyToMany). Do danych należy stworzyć zapytania (najlepiej użyteczne w docelowym projekcie). Zapytań musi być co najmniej 5, każde zapytanie powinno być odrębną metodą w kodzie. Jedno z zapytań powinno być stronicowane.

Należy użyć w projecie pola do zapisu daty - najlepiej ZonedDateTime (jako, że może to przysporzyć problemu, w przypadku braku użycia daty max liczba punktó do zdobycia to 85%).

Docelowy projekt powinien czytać i zapisywać zamodelowane dane z i do plików typu XML i JSON (obu typów!) i zapisywać/odczytywać je z bazy danych.

Program powinno byś w stanie się uruchomić. Nie trzeba tworzyć żadnego menu do projektu, sprawdzenie poprawności może odbywać się poprzez zdefiniowanie odpowiednich testów dla scenariuszy: odczyt danych z pliku XML / JSON i zapis do bazy, odczyt danych z bazy i zapis do plików XML / JSON. Należy stworzyć odpowiednie pliki XML / JSON dla ww testów.

Uwaga! odczyt i zapis powinien się powodzić / niepowodzić w przypadku podania nieprawidłowych czy częściowych danych (pewne pola puste).

