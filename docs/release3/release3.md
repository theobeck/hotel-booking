# Utgivelse 3.0.0

## Endringer og forbedringer i denne utgivelsen

- Gikk over til en dato-orientert bookingprosess og fjernet ubrukt Hotel.java.
- Rom kan nå bookes flere ganger, noe som tilbyr økt fleksibilitet i bookingen.
- Bookingprosessen er nå knyttet til en spesifikk bruker.
- Opprettet [User.java](../../booking/core/src/main/java/booking/core/User.java) og [Booking.java](../../booking/core/src/main/java/booking/core/Booking.java) for bedre håndtering av brukerdata og bookingdata.
- Implementert en ny innloggingsside og en ny registreringsside med tilhørende kontrollere.
- Opprettet REST API for både brukere og rom, noe som tillater mer fleksibilitet i hvor dataene kan hentes.
- Gjort betydelige endringer i brukergrensesnittet på alle sider, inkludert nytt oppsett, endringer i skrifttyper og generelt utseende.
- Opprettet egendefinerte serializer/deserializer-klasser for bedre JSON-håndtering av Booking, Room og User objekter.
- Oppdatert nye og tydeliggjort gamle fil- og kontrollernavn for bedre leselighet.
- Endret filnavn for mer samsvar innad i filstrukturen.

## Testing

- Oppdaterte tester for å reflektere endringen i bookingprosessen.
- Opprettet tester for de nye kontrollerne og REST API.
- Forbedret testdekning i samsvar med JaCoCo-rapporten. Modulene `core` og `fxui` har nå 100% testdekning, og `springboot.restserver` har nær 100% testdekning.

## Bug Fixes

- Rettet feil i [devfile.yaml](../../devfile.yaml) som gjorde det umulig å kjøre prosjektet og prosjektets GUI i Eclipse Che.
- Håndtert problemer med pipeline og Eclipse Che for å sikre jevnere drift.
- Håndtert diverse mindre problemer, inkludert justeringer i UI, tilfredsstillelse av checkstyle og små feilrettinger.

## Dokumentasjon

- Oppdatert Javadocs gjennom hele prosjektet.
- Oppdatert READMEs.
- Lagt til Javadocs-plugin slik at vi kan generere Javadocs-nettsider.

## Kjente problemer

- Ingen kjente problemer på dette tidspunktet.

For flere detaljer og spesifikke implementeringsendringer, vennligst se gjennom commit-historikken og READMEs.
