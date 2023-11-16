# Utgivelse 3.0.0

## Endringer og forbedringer i denne utgivelsen

- Overgang til en dato-orientert bookingprosess og fjernet overflødig Hotel.java.
- Rom kan nå bookes flere ganger, noe som øker fleksibiliteten i bookingen.
- Opprettet User.java og Booking.java for bedre håndtering av brukerdata og bookingdata.
- Implementert en ny innloggingsside og en ny registreringsside sammen med en LogInController og RegisterController. Bookingprosessen er nå knyttet til en spesifikk bruker.
- Opprettet REST API-er for både brukere og rom, noe som tillater mer strømlinjeformet håndtering av data.
- Gjort betydelige endringer i brukergrensesnittet på alle sider, inkludert nye oppsett, endringer i skrifttyper og tillegg av bilder.
- Opprettet egendefinerte serializer/deserializer-klasser for bedre JSON-håndtering for Booking, Room og User objekter.
- Oppdatert og klargjort fil- og kontrollernavn for bedre lesbarhet.

## Testing

- Oppdaterte tester for å reflektere endringen i bookingprosessen.
- Lagt til nye tester for de nye kontrollerne og REST API-ene.
- Forbedret testdekning i samsvar med JaCoCo-rapporten. Modulene `core` og `fxui` har nå 100% testdekning, og `springboot.restserver` har nesten 100% testdekning.

## Feilrettinger

- Rettet feil i che devfile som gjorde det umulig å kjøre prosjektet selv og dets GUI i che.
- Håndtert problemer med pipeline og Eclipse Che for å sikre jevnere drift.
- Håndtert diverse mindre problemer, inkludert justeringer i UI, tilfredsstillelse av checkstyle og små feilrettinger.

## Dokumentasjon

- Oppdatert Javadocs over hele prosjektet for bedre dokumentasjon.
- Oppdatert README.md for å reflektere endringene i prosjektet.
- Lagt til Javadocs-plugin slik at vi kan generere Javadocs-nettsider.

## Kjente problemer

- Ingen kjente problemer på dette tidspunktet.

For flere detaljer og spesifikke implementeringsendringer, vennligst se gjennom commit-historikken og utgivelsesdokumentasjonen.
