# Hotellbooking App

Dette kodingsprosjektet inneholder en hotellbookingapplikasjon som lar brukere søke etter og bestille hotellrom. Her er en oversikt over hvordan prosjektet fungerer.

## Om Hotellbooking Appen

Hotellbookingappen er utviklet for å gjøre det enkelt for brukere å søke etter ledige hotellrom og foreta en booking. Applikasjonen har følgende funksjonalitet:

### Logg inn

![Login Screen](../img/login.png)

På landingsiden enten velge å logge inn med en eksisterende bruker, eller registrere en ny bruker.

### Registrere ny bruker

![Register Screen](../img/register.png)

Her kan brukere registrere en ny bruker ved å fylle inn ønsket brukernavn og passord, fornavnet sitt, etternavnet sitt og kjønnet sitt.

### Hovedsiden

![Home Screen](../img/mainMenu.png)

På hovedsiden har brukere to valg. De kan enten søke etter ledige rom, eller se en liste over bookingene sine.

### Søk for ledige rom

![Search Screen](../img/searchForRooms.png)

På søkesiden kan brukere velge innsjekkings- og utsjekkingsdatoer. Når brukere trykker på "Search" vil de få opp en liste over ledige rom som passer kriteriene deres.

### Booke et rom

![Book Room Screen](../img/availableRooms.png)

Her kan brukeren se en liste over ledige rom som passer kriteriene deres. Brukere kan booke et rom ved å trykke på "Book" knappen.

### Brukeren sine bookings

![User Bookings Screen](../img/userBookings.png)

Her kan brukere se en liste over bookingene sine. De kan også kansellere en bookin om de angrer ved å trykke på "Cancel booking" knappen.

## Brukerhistorier

Her er to brukerhistorie som representerer noen av de grunnleggende funksjonene i appen:

### Brukerhistorie 1: Utforske ledige rom (US-1)

Som en potensiell gjest ønsker jeg å kunne se tilgjengelige hotellrom på bestemte datoer, slik at jeg kan velge og booke et rom for oppholdet mitt.
Viktig å kunne se

En landingsside med knappen "Book Room" som tar meg til bookingsiden.
En bookingside som viser en liste over tilgjengelige rom for de valgte datoene.
En oversikt over datoene jeg har valgt for oppholdet mitt.

**Viktig å kunne gjøre**

- Velge innsjekkings- og utsjekkingsdatoer.
- Se en liste over tilgjengelige rom som passer mine kriterier.
- Velge et rom og gå videre til bookingprosessen.

### Brukerhistorie 2: Se mine bookinger (US-2)

Som en gjest ønsker jeg å kunne se en liste over mine tidligere bookinger, slik at jeg kan holde oversikt over mine kommende og tidligere opphold.
Viktig å kunne se

En landingsside med knappen "Show Bookings" som tar meg til bookingshistorikksiden.
En bookingshistorikkside som viser en liste over mine tidligere og kommende bookinger.
Informasjon om hvert opphold, inkludert innsjekkings- og utsjekkingsdatoer, romtype og pris.

**Viktig å kunne gjøre**

- Klikke på en bestilling for å se flere detaljer om oppholdet.
- Få tilgang til kvitteringer og bekreftelser for hver bestilling.
- Eventuelt kansellere en fremtidig booking (hvis avbestillingsregler tillater det).

**Brukerhistorie 3: Avbestille og Rebestille (US-3)**

Som en gjest som har feilaktig booket feil dato, ønsker jeg å kunne avbestille min nåværende booking og deretter enkelt kunne bestille på nytt med de riktige datoene.
Viktig å kunne se

- En landingsside med alternativet "Mine Bookinger" som tar meg til bookingshistorikksiden.
- En oversikt over mine eksisterende bookinger med detaljer om innsjekkings- og utsjekkingsdatoer.
- En knapp som gir mulighet for avbestilling.

**Viktig å kunne gjøre**

- Velge en bestilling som jeg vil avbestille.
- Umiddelbart etter avbestilling, få muligheten til å bestille på nytt med korrekte datoer.

**Scenario:**
1. Jeg logger inn på profilen min og navigerer til "Mine Bookinger".
2. Jeg identifiserer feil booking og velger alternativet for avbestilling.
3. Etter avbestilling blir jeg ledet tilbake til bookingsiden, der jeg enkelt kan starte en ny booking med riktige datoer.

Disse brukerhistoriene gir en oversikt over de grunnleggende funksjonalitetene i hotellbookingappen din. Du kan utvide og tilpasse dem etter behov, avhengig av prosjektets omfang og krav.
