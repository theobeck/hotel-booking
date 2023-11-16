# Utgivelse 3.0.0

## Endringer og forbedringer i denne utgivelsen

- Gikk over til en dato-orientert bookingprosess og fjernet ubrukt Hotel.java.
- Rom kan nå bookes flere ganger, noe som øker fleksibiliteten i bookingen.
- Opprettet User.java og Booking.java for bedre håndtering av brukerdata og bookingdata.
- Implementert en ny innloggingsside og en ny registreringsside sammen med en LogInController og RegisterController. Bookingprosessen er nå knyttet til en spesifikk bruker.
- Opprettet REST API-er for både brukere og rom, noe som tillater mer smidig håndtering av data.
- Gjort betydelige endringer i brukergrensesnittet på alle sider, inkludert nytt oppsett, endringer i skrifttyper og la til noen bilder.
- Opprettet egendefinerte serializer/deserializer-klasser for bedre JSON-håndtering av Booking, Room og User objekter.
- Oppdatert nye og tydeliggjort gamle fil- og kontrollernavn for bedre leselighet.
- Endret filnavn for mer samsvar innad i filstrukturen.

## Testing

- Oppdaterte tester for å reflektere endringen i bookingprosessen.
- Opprettet nye tester for de nye kontrollerne og REST API-ene.
- Forbedret testdekning i samsvar med JaCoCo-rapporten. Modulene `core` og `fxui` har nå 100% testdekning, og `springboot.restserver` har nesten 100% testdekning.

## Bug Fixes

- Rettet feil i che devfile som gjorde det umulig å kjøre prosjektet og prosjektets GUI i che.
- Håndtert problemer med pipeline og Eclipse Che for å sikre jevnere drift.
- Håndtert diverse mindre problemer, inkludert justeringer i UI, tilfredsstillelse av checkstyle og små feilrettinger.

## Dokumentasjon

- Oppdatert Javadocs over hele prosjektet for bedre dokumentasjon.
- Oppdatert README.md for å reflektere endringene i prosjektet.
- Lagt til Javadocs-plugin slik at vi kan generere Javadocs-nettsider.

## Kjente problemer

- Ingen kjente problemer på dette tidspunktet.

For flere detaljer og spesifikke implementeringsendringer, vennligst se gjennom commit-historikken og utgivelsesdokumentasjonen.
