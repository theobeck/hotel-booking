# Hotellbooking App

[Open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2313/gr2313?new)

I eclipse che, etter at du har bygget og kjørt prosjektet, må du se på panelet som heter `endpoints` (finnes nederst til venstre), og trykke på public port 6080, kopiere denne, og lim den inn i en ny fane for å få opp GUIen.

Dette kodingsprosjektet inneholder en hotellbookingapplikasjon som lar brukere søke etter og bestille hotellrom. Her er en oversikt over appen og hvordan du kan bruke det.

## Bygging og kjøring av appen

Appen bruker **maven** til bygging og kjøring.

Her er hvordan man bygger prosjektet:

- Først, kjør `mvn clean install -PskipUITests` fra rot mappen `booking/`. Dette vil bygge appen. Her er hvordan det gjøres på **Mac** og **Linux**:
  ```
  cd booking
  mvn clean install -PskipUITests
  ```
- Deretter, lag en ny terminal fane, og kjør `mvn spring-boot:run` fra `booking/springboot/restserver/`. Dette vil starte opp serveren på port 8080. Her er hvordan det gjøres på **Mac** og **Linux**:
  ```
  cd booking/springboot/restserver/
  mvn spring-boot:run
  ```
- Så går du tilbake til den forrige terminal fanen, og kjører `mvn clean install`. Dette vil bygge appen på nytt, men denne gangen kan den også kjøre tester på GUIet. Her er hvordan det gjøres på **Mac** og **Linux**:
  ```
  mvn clean install
  ```
- Til slutt, navigerer du til `booking/fxui/`, og kjører `mvn javafx:run`. Dette vil åpne GUIet. Her er hvordan det gjøres på **Mac** og **Linux**:
  ```
  cd booking/fxui/
  mvn javafx:run
  ```

## Appens Struktur

Denne appen er organisert som følger:

- `docs/` - Her ligger gruppekontrakten, i tillegg til dokumentasjon fra hver release.
- `booking/` - Dette er selve kodelageret for prosjektet.
  - `core/src/`
    - `main/java/booking/core`
      - Her ligger hovedlogikken for appen.
    - `test/java/booking/core`
      - Her ligger testene for hovedlogikken av appen.
  - `fxui/src`
    - `main/`
      - `java/booking/ui`
        - `internal/`
          - Her ligger serializer og deserializer filer for alle tre hovudlogikk filene våre. I tillegg, er har vi filen som håndterer tilgang til REST Serveren vår, og fungerer som en type klient til Serveren.
        - Her ligger kontroller filene for GUIen.
      - `resources/booking/ui`
        - Her ligger fxml filene som tilsvarer hver side i appen.
    - `test/java/booking/ui`
      - `internal/`
        - Her ligger testene for serializer, deserializer, og klient filene våre.
      - Her ligger hoved GUI testen vår.
  - `springboot/restserver/src`
    - `main`
      - `java/booking/springboot/restserver/`
        - Her ligger filene for REST API-et og serveren vår.
    - `resources/booking/springboot/restserver/`
      - Her ligger 'databasen' vår, som er to json filer. En for rom, og en for brukere.
    - `test/java/booking/springboot/restserver/`
      - Her ligger testene for REST API-et og serveren vår.

![Prosjektets struktur vha PlantUML](../../img/architecture.png)

## Nødvendigheter for kjøring av prosjektet

Maven versjon - 3.9.4

Java versjon - 20.0.2
