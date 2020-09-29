Kristian By, s344058, s344058@oslomet.no

# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: Løste ved å lupe gjenom alle objekter i innparameter og opprette noder med disse objectene som verdi i
 tilsvarende rekkefølge.
 
* Oppgave 2: Løste a ved å lupe gjennom alle noder fra starten til neste for alle doner og legge till verdien til
 StringBuilder for to string. Startet på hale og gikk til forrige for omvendt string. Løste b ved å opprette en ny node
 med hale som forigje så sette hele sin neste lik den nye noden så definere halen som den nye noden. untakk når listen
 er tom blir det oppretet en ny hode og hale node.
 
* Oppgave 3: Løste a ved å finne posisjonen til midterste Node. Om indeksen er større lupes det gjennom fra hallen til
 man ender opp på riktig node. om indeksen er mindre lupes det fra hodet til man ender opp på riktig node. Løste b ved 
 å opprette en ny DobbeltLenketListe. deretter finne noden som ligger på indeks fra. så lupe gjennom og legge til
 nodenes verdi i den nye DobbeltLenketListe og hoppe til neste node til - fra ganger.
 
* Oppgave 4: Løste  indeksTil(T verdi) ved å lupe gjennom alle nodene samtidig som indeks blir telt. om nodens verdi passer ed inn parameter
 returneres indeks. Om den ikke blir funnet returneres -1. Løste inneholder(T verdi) ved bruk av indeksTil(T verdi)
 hvor det blir testet om om indeksen ikke er lik -1.
 
* Oppgave 5: Løste ved først å kontrollere om inn parametere er lovlige. deretter testes det om listen er tom og hvis
 dette er tilfelle blir det opprettet ny hode og hale node. Hvis indeks er null blir det opprettet en ny hale med
 forrige hale som neste. Om indeks er lik antall elementer blir det opprettet ny hale med tidligere hale som forrige.
 ellers blir noden på indeksen funnet og det blir opprettet en ny node med denne som neste samtidig som nabo nodene 
 får riktig nabo noder.
 
* Oppgave 6: Løste ved å først lokalisere node som skal fjernes. Hode eller hale blir redefinert avhenge av om de blir
 fjernet. Nabonodene før definert nye nabonoder. Om det skal returneres verdi blir den lagret som en variable og
 returnert ut. om det skal returneres boolean blir det sendt ut false hvis verdien ikke finnes eller blir de returnert
 true.
  
* Oppgave 8: Løste a ved å teste om endringer stemmer og om har neste. om detter er oppfylt blir denne flyttet til neste
 node og verdi returnert. Løste b ved å returnere en ny DobbeltLenketListeIterator(). Løste c ved å sette denne like
 noden på indeksen ved bruk av finnNode(int indesk). Løste d ved å utføre en indeks kontroll å så returnere en ny 
 DobbeltLenketListeIterator(int indeks) med indeksen som indeks.
 
* Oppgave7?: Etter som testen for oppgave 8 kaller på nullstill() metoden har den fat en simpel implementering. 
 metoden tar kun ok nullstiller hode og halle, setter antall lik 0 og øker antall endringer med 1.






