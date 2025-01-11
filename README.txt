# Java-SMS Project

## Opis projektu

Java-SMS to prosty projekt zarządzania studentami (SMS - Student Management System) napisany w Javie. Projekt wykorzystuje SQLite jako bazę danych, a Maven jako narzędzie do zarządzania zależnościami.

## Wymagania systemowe

- Java 17 lub nowsza (używany OpenJDK 23 w projekcie)
- Maven 3.6 lub nowszy
- SQLite JDBC Driver (w projekcie używana wersja `3.42.0.0`)

## Instrukcja instalacji

1. **Sklonuj repozytorium**:

   Skopiuj projekt na swój komputer, używając następującego polecenia w terminalu:
   ```bash
   git clone <adres_repozytorium>
   ```

2. **Wejdź do katalogu projektu**:
   ```bash
   cd java-SMS
   ```

3. **Zbuduj projekt za pomocą Mavena**:
   ```bash
   mvn clean install
   ```
   Komenda ta zainstaluje wszystkie wymagane zależności i zbuduje projekt.

4. **Sprawdź konfigurację bazy danych**:
   Upewnij się, że plik bazy danych `mydatabase.db` znajduje się w katalogu `src/main/resources`. Możesz także edytować plik, jeśli chcesz używać innej bazy danych SQLite.

## Instrukcja uruchomienia

1. **Uruchom klasę główną**:
   Główna klasa projektu to `Main` znajdująca się w pakiecie `com.smsproject`.
   Możesz uruchomić ją za pomocą IDE (np. IntelliJ IDEA lub VSCode) lub używając poniższego polecenia Maven:
   ```bash
   mvn exec:java -Dexec.mainClass="com.smsproject.Main"
   ```

2. **Interakcja z aplikacją**:
   Po uruchomieniu programu będziesz mógł zarządzać danymi studentów (np. dodawać, usuwać, czy wyświetlać dane).

## Struktura projektu

- `src/main/java/com/smsproject` - Kod źródłowy projektu
  - `Main` - Klasa główna aplikacji
  - `Student` - Klasa reprezentująca obiekt studenta
  - `StudentManager` - Interfejs do zarządzania studentami
  - `StudentManagerImpl` - Implementacja zarządzania studentami
- `src/main/resources/mydatabase.db` - Plik bazy danych SQLite

## Zależności Maven

Najważniejsze zależności projektu to:
```xml
<dependencies>
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.42.0.0</version>
    </dependency>
</dependencies>
```
