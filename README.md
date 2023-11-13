# Hotellbooking App

[Open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2313/gr2313?new)

I eclipse che, etter at du har bygget og kjørt prosjektet, må du se på panelet som heter `endpoints`, og trykke på public port 6080, kopiere denne, og sette den i en ny fane for å få opp GUIen.

Dette kodingsprosjektet inneholder en hotellbookingapplikasjon som lar brukere søke etter og bestille hotellrom. Her er en oversikt over prosjektet og hvordan du kan bruke det.

## Bygging og kjøring av prosjektet

Prosjektet bruker maven til bygging og kjøring.

For å bygge, kjør `mvn install` fra rot-prosjektet `booking/`. Dette vil kjøre alle tester og kvalitetssjekker.

For å kjøre prosjektet, må man først starte opp Springboot serveren. Dette gjores ved å kjøre `mvn spring-boot:run` fra springboot mappa `booking/springboot/restserver`. Dette vil starte opp serveren på port 8080.

Etter det, åpner du en ny terminal, og navigerer til `booking/fxui`. Herfra kjorer du `mvn javafx:run`. Dette vil starte opp GUIen.

## Prosjektets Struktur

Dette prosjektet er organisert som følger:

- `docs/` - Her ligger gruppekontrakten, i tillegg til release-spesifikk dokumentasjon.
- `booking/` - Dette er selve kodelageret for prosjektet.
  - `core/` - Her ligger hovedlogikken for prosjektet og de tilhørende testene. Innad i `core/`, er loggiken delt opp enda litt til, med at hoved logikk filene og filene som kontrollerer skriving/lesing til/fra JSON filer ligger i sine egne mapper.
  - `fxui/` - Her ligger filene for GUIen, altså fxml filer, de tilhørende kontrollerne, og tester for disse.

![Prosjektets struktur vha PlantUML](img/architecture.png)

## Nødvendigheter for kjøring av prosjektet

Maven versjon - 3.9.4

Java versjon - 20.0.2
