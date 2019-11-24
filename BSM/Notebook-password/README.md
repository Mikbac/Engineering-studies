# Notatnik

## Zabezpieczenia i wykorzystane rozwiązania:
- [x] Nazwa użytwkonika, hasło i notatka są zapisane w bazie danych LiteSQL.

- [x] Hasło i notatka są zaszyfrowane przy pomocy AES (AES/GCM/NoPadding).

- [x] Klucz do szyfru jest generowany losowo.

- [x] Klucz do szyfru zostaje umieszczony w keystore.

- [ ] Po podaniu 3 razy błędnego hasła zostanie zablokowana możliwość kolejnego podania hasła na 30 sekund.
    Data blokady zostaje zapisana do bazy danych.
    Konto zostaje ustawione jako zablokowane. (zabezpieczenie przed włączeniem i wyłączeniem aplikacji)
    
- [ ] Hasło musi mieć długość przynajmniej 8 znaków i składać sie przynajmniej z jedngo/jednej:
    - dużej litery
    - małej litery
    - cyfry
    - znaku specjalnego

- [ ] Wyczyszczenie pola hasła po wylogowaniu.

- [ ] Porównywanie zaszyfrowanych zamiast odszyfrowanych.

## Instrukcje:
* Jeśli użytkownik poda nazwę użytkownika, która nie istnieje to zostanie utworzone nowe konto z podaną nazwą użytkownika i podanym hasłem.
* Jeśli użytkownik poda nazwę użytkownika, która istnieje ale poda złe hasło to logowanie zostanie odrzucone.
* Aby skorzystać z przykładowego użytkownika należy podać:
   ```
   login: "Pankracy"
   hasło: "sekurak12345678"
   ```
* Aby zapisać notatkę należy wybrać przycisk ```save```.
* Aby zmienić hasło należy wybrać przycisk ```change your password```.
* Przy zmianie hasła należy podać stare hasło i dwa razy nowe hasło, przy czym nowe hasło musi zawierać powyżej 5 znaków.
* Aby wyjść należy wybrać przycisk ```exit```.
    
    
## Ciekawe linki:
    https://blog.bcaster.com/use-android-keystore-for-securely-storing-and-retrieving-the-data/
