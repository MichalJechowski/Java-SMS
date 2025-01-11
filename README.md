# Java-SMS

Projekt Java-SMS to system zarządzania studentami (Student Management System) napisany w języku Java z wykorzystaniem SQLite.

---

## Wymagania wstępne

1. **Java Development Kit (JDK)** w wersji 17 lub nowszej (np. OpenJDK 23.0.1, jak w projekcie).
2. **Apache Maven** - do zarządzania zależnościami i budowania projektu. (Dodaj zmienną środowiskową)
3. **SQLite JDBC Driver** - już zawarty w konfiguracji projektu w pliku `pom.xml`.

---

## Instalacja

1. **Pobierz projekt:**
    - Jeśli używasz systemu Git:
      ```bash
      git clone <URL_REPOZYTORIUM>
      cd java-SMS
      ```
    - Możesz też pobrać pliki projektu ręcznie i otworzyć w swoim IDE.

2. **Konfiguracja IDE:**
    - Otwórz projekt w IntelliJ IDEA (lub innym IDE obsługującym Maven).
    - Upewnij się, że JDK jest poprawnie skonfigurowane.

3. **Instalacja zależności Maven:**
    - W katalogu głównym projektu uruchom:
      ```bash
      mvn clean install
      ```
    - Maven automatycznie pobierze wymagane zależności (SQLite JDBC itp.).

---

## Budowanie i kompilacja

1. **Budowanie projektu:**
    - Wykorzystaj Maven do stworzenia plików `.jar`:
      ```bash
      mvn package
      ```
    - Wynik budowania znajdziesz w katalogu `target/`, gdzie wygenerowane zostaną:
        - `project-sms-1.0-SNAPSHOT.jar`
        - `project-sms-1.0-SNAPSHOT-jar-with-dependencies.jar` (zalecany, ponieważ zawiera wszystkie zależności).

---

## Uruchamianie projektu

1. **Sprawdź plik bazy danych:**
    - Upewnij się, że baza danych SQLite (`mydatabase.db`) znajduje się w katalogu `src/main/resources`.

2. **Uruchomienie projektu:**
    - Uruchom aplikację za pomocą pliku `.jar`:
      ```bash
      java -jar target/project-sms-1.0-SNAPSHOT-jar-with-dependencies.jar
      ```
    - Alternatywnie możesz uruchomić klasę `Main` bezpośrednio w swoim IDE.

---

## Struktura projektu

- **Kod Java (`src/main/java/com/smsproject`)**:
    - `Main` - główny punkt startowy programu.
    - `Student` - klasa reprezentująca dane studenta.
    - `StudentManager` - interfejs do operacji CRUD na studentach.
    - `StudentManagerImpl` - implementacja logiki zarządzania studentami.

- **Zasoby (`src/main/resources`)**:
    - `mydatabase.db` - plik bazy danych SQLite.

- **Wyniki budowania (`target/`)**:
    - Pliki `.jar` gotowe do uruchomienia.

---
## Dokumentacja Projektu i Skompilowana Aplikacja
- **Dostępna w katalogu Dokumentacja Projektu i Aplikacja**
