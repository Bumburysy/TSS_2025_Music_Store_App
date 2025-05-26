# 🎵 Music Store App – Dokumentacja Techniczna

Aplikacja webowa do zarządzania albumami i stanem magazynowym sklepu muzycznego. Wspiera operacje CRUD na albumach, monitorowanie stanu magazynowego z wykresami, a także zarządzanie pracownikami. 

## 📚 Opis funkcjonalny i technologiczny

### 1. Architektura MVC
Aplikacja opiera się na wzorcu architektonicznym **Model-View-Controller (MVC)**:
- **Model**: Klasy encji (`Album`, `User`), zmapowane na dokumenty w **MongoDB**.
- **View**: Szablony **Thymeleaf** generujące dynamiczne strony HTML5 z obsługą danych serwera.
- **Controller**: Klasy kontrolerów (`AlbumRestController`, `InventoryRestController`, `InventoryWebSocketController`, `UserController`, `MainController`) obsługujące żądania HTTP, przygotowujące dane i przekazujące je do widoków.
- **Service**: Warstwa usług (`CustomUserDetailsService`) zawiera logikę biznesową oddzieloną od kontrolerów.
- **Repository**: Interfejsy repozytoriów (`AlbumRepository`, `UserRepository`) do komunikacji z bazą danych (MongoRepository).

### 2. Budowa stron JSP/Thymeleaf
- **Thymeleaf** jest używany do renderowania dynamicznych treści.
- Szablony używają atrybutów `th:href`, `th:text`, `th:if`, `th:each` do wstawiania danych, obsługi pętli i warunków.
- Dedykowane szablony odpowiadają za widoki list albumów, magazynu, zarządzania użytkownikami, formularzy CRUD oraz customowy ekran logowania.

### 3. Mechanizmy sesji
- Mechanizm sesji śledzi:
  - Login użytkownika (`#request.remoteUser`),
  - Czas zalogowania (`HttpSession`),
  - Liczbę odwiedzin w danej sesji.
- Dane sesyjne są wyświetlane w nagłówku strony (np. `Zalogowany jako`, `Czas trwania sesji`).

### 4. Konfiguracja uprawnień, logowania, ról
- Klasa **SecurityConfig** konfiguruje:
  - Główna witryna dostępna bez wymagania logowania.
  - Uprawnienia dostępu do ścieżek (`antMatchers`): `/albums`, `/users`, `/stock`, `/appInfo`.
  - Role `USER`, `ADMIN` – kontrolujące dostęp do zasobów.
  - Uwierzytelnianie przy pomocy własnego `UserDetailsService` i `DaoAuthenticationProvider`.
  - Formularz logowania i customowy ekran logowania.
  - Obsługa HTTPS i wymuszanie szyfrowanego połączenia.

### 5. CRUD i prezentacja danych
- Operacje CRUD na kolekcjach `albums` i `users` realizowane poprzez kontrolery i repozytoria **MongoDB**.
- Dane wyświetlane w tabelach w widokach, z użyciem **Thymeleaf**.
- Aktualizacja danych magazynowych (`refresh`) modyfikuje losowo ilości albumów.

### 6. Odczyt zmiennych środowiskowych i Actuator
- Wykorzystano **Spring Boot Actuator** do prezentacji informacji o aplikacji i środowisku.
- Dane z `/actuator/info`, `/actuator/env` wyświetlane w widokach aplikacji (`appInfo`).

### 7. Bezpieczeństwo transmisji
- Konfiguracja HTTPS z wymuszeniem szyfrowanego kanału (`requiresSecure()`).
- Własny certyfikat SSL.
- Dodatkowo zabezpieczony formularz logowania i sesje.

### 8. Usługa RESTful
- Endpoint `/api/albums` zwraca dane o albumach w formacie **JSON**.
- Umożliwia integrację z innymi aplikacjami lub frontendami (np. React, Angular).
- Przykładowe użycie REST API w kliencie JS (`restAlbums.js`).

### 9. WebSocket – przesyłanie danych w czasie rzeczywistym
- Serwer: Konfiguracja **WebSocket** i **STOMP** do przesyłania informacji o stanie magazynowym.
- Klient: JavaScript z **SockJS** i **Stomp.js** subskrybuje temat `/topic/inventory`.
- Dane aktualizujące wykres stanu magazynu są przesyłane w czasie rzeczywistym i prezentowane za pomocą **Chart.js**.

### 🚀 Technologie
- **Java 17**, **Spring Boot**, **Spring Security**, **MongoDB**, **Thymeleaf**, **WebSocket**, **Chart.js**, **SockJS**, **STOMP**, **Actuator**, **HTML5**, **CSS3**.

________________________________________

Wymagania projektu:

  1. Architektura MVC – komponenty aplikacji kontrolery, serwisy, widoki, logika itd.
  2. Budowa stron JSP/Thymeleaf (użycie elementów dedykowanych wybranej technologii)
  3. Mechanizmy sesji – wykorzystanie mechanizmów sesji do zapisu danych aplikacji
  4. Konfiguracja podstawowych mechanizmów serwera (uprawnienia, logowanie, role) – klasy konfiguracyjne/pojedyncza baza lub dodatkowa baza z danymi autoryzacyjnymi
  5. Modyfikacja danych w bazie (CRUD) za pomocą ORM, prezentacja danych (tabele w widokach aplikacji) i własna baza danych
  6. Odczyt zmiennych środowiskowych, danych projektu i prezentacja w widokach ( Actuator )
  7. Bezpieczeństwo transmisji – konfiguracja i wykorzystanie HTTS, własny certyfikat
  8. Usługa RESTful - przykład przesyłania i prezentacji danych w formacie JSON – implementacja części serwerowej – dane z usługi / dane z bazy
  9. WebSocket – przykład przesyłania i prezentacji danych – implementacja części serwerowej i klienckiej w JS

  - widoki JSP/Thymeleaf
  - ekran logowania i błędu
  - ekrany aplikacji
  - ekran z edycją danych w postaci tabelarycznej (CRUD)
  - sterowanie aplikacją za pomocą servletu kontrolującego (w Spring @Controller) działanie aplikacji (komponent startowy aplikacji)
  - komponenty logiki aplikacji i modelu do których są oddelegowane zadania z serwletu sterującego (w Spring @Service)
  - operacje/polecenia CRUD poprzez serwlet sterujący są przekazywane do logiki biznesowej
  - logika biznesowa korzystając z komponentów DAO - wykonuje operacje na modelu (w Spring @Repository)
  - źródło danych aplikacji deklarowane na poziomie serwera -baza MSSQL-tomcat (udostępniona lub własna we własnym projekcie)
  - usługi sieciowe typu RESTful - tylko dane zwracane w postaci JSON (w Spring @RestController)
  - technologię WebSocket i komunikację z gniazdami sieciowymi - prezentacja w prostej formie graficznej
  - zabezpieczenie zasobów aplikacji za pomocą mechanizmów serwera Tomcat (Spring Secutiry)
  - mechanizm (logowanie/wylogowanie)
  - servlety, widoki - zabezpieczenie HTTPS
  - punkty końcowe RESTful - zabezpieczenie HTTPS
  - WebSocket - zabezpieczenie protokołem WSS (czyli WS na SSL)
  - dane autoryzacyjne/uwierzytelniające w bazie danych - baza MSSQL - tomcatAuth (lub własnej we własnym projekcie)
  - szyfrowanie transmisji za pomocą SSL