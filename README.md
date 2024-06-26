Progetto sviluppato per gli esami di Ingegneria del Software e Progettazione di Applicazioni Web e Mobili.

## Specifiche progetto
Il progetto Loyalty Platform mira a supportare le attività commerciali nel migliorare la fedeltà dei clienti, sia quelli già esistenti che quelli potenzialmente nuovi. È cruciale coinvolgere sia gli utenti online che quelli fisici che visitano i punti vendita.
Secondo diverse statistiche, le probabilità di vendere a un cliente esistente sono significativamente più alte, comprese tra il 60% e il 70%, rispetto alle possibilità di vendita a un nuovo cliente, che variano solo dal 5% al 20%.
Il progetto si concentra principalmente sulle attività locali con almeno due sedi e ha come obiettivi principali l'incremento del fatturato, il miglioramento della ritenzione dei clienti attraverso strategie di fidelizzazione, l'acquisizione di nuovi clienti tramite quelli esistenti e la creazione di un database qualificato per futuri marketing mirati.

## Progettazione lato Backend
Nel progetto è stato utilizzato <br>Spring Boot</br> per implementare il backend dell'applicazione. Spring Boot ha fornito una struttura chiara e modulare, permettendo di organizzare il codice in cartelle dedicate come Component, Repository, Service e Record. Questa organizzazione ha favorito una gestione efficiente delle diverse parti dell'applicazione, migliorando la manutenibilità del codice e rendendolo più leggibile. Per quanto riguarda l'autenticazionei invece, è stato implementato un sistema basato su <br>JSON Web Token (JWT)</br>. Ciò è stato fatto per garantire che solo gli utenti autorizzati potessero accedere alle risorse dell'applicazione. Tuttavia, l'implementazione di JWT è stata un processo complesso a causa della necessità di una conoscenza dettagliata dei meccanismi di autenticazione e autorizzazione offerti da JWT.
Durante il login, gli utenti precedentemente registrati vengono autenticati e viene generato un nuovo JWT token. Questo token include anche una data di scadenza (expiration date, impostata a 24 ore), per evitare la permanenza di token non validi nel sistema. Il token e le relative informazioni sono salvati nel Local Storage del browser dell'utente, in modo tale che un utente già loggato, non debba autenticarsi nuovamente quando chiude, ad esempio, la pagina web.

## Progettazione lato Frontend
Per la realizzazione del frontend, è stato utilizzato <br>Angular</br>. Il progetto è stato organizzato in diverse cartelle, tra cui component e service, per gestire l'interfaccia utente in modo modulare e manutenibile.
Angular è stato integrato con il sistema di autenticazione basato su JWT implementato nel backend. Ogni volta che l'utente tenta di accedere a pagine diverse dalla login o dalla registrazione, il frontend verifica la presenza di un utente autenticato e la validità del JWT token, il quale deve iniziare con "Bearer". Questo approccio assicura che solo gli utenti autenticati possano navigare liberamente all'interno dell'applicazione, garantendo allo stesso tempo la sicurezza dei dati e delle operazioni eseguite.

## Database H2
Per il popolamentod dei dati è stato utilizzato il database H2. Il database relazionale si è dimostrato estremamente utile durante lo sviluppo in quanto può essere configurato come un database in-memory, il che implica che i dati non vengono salvati in modo permanente su disco.

## Considerazioni finali

