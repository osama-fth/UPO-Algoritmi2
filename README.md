# 📊 Implementazione di Grafi non orientati con Matrice di Incidenza

![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-3.9-blue)
![JUnit](https://img.shields.io/badge/JUnit-5.0-green)

Un'implementazione Java di grafi non orientati e grafi pesati non orientati utilizzando una matrice di incidenza, seguendo le specifiche dell'interfaccia `Algoritmi2LabVC`.

## 📝 Descrizione

Questo progetto offre un'implementazione completa di grafi non orientati utilizzando la struttura dati della matrice di incidenza. Include anche un'estensione per grafi pesati che supporta archi con pesi personalizzabili.

## 🏗️ Strutture Dati Utilizzate

- 📊 **Matrice di Incidenza**: Una matrice booleana dove le righe rappresentano i vertici e le colonne rappresentano gli archi
- 🔄 **Mappe di Indirizzamento**: Per gestire la corrispondenza tra ID dei vertici e indici della matrice
- 📋 **Lista degli Archi**: Per mantenere tutti gli archi presenti nel grafo

## ✨ Funzionalità Implementate

### 🔷 Per il Grafo non orientato (`Graph20054809`)

- ➕ Aggiunta e rimozione di vertici
- 🔗 Aggiunta e rimozione di archi
- 🔍 Controllo dell'esistenza di vertici e archi
- 🚶 Visita BFS (Breadth-First Search)
- 🧭 Visita DFS (Depth-First Search)
- ⭕ Rilevamento di cicli
- 🧩 Calcolo delle componenti connesse

### 🔶 Per il Grafo Pesato (`WeightedGraph20054809`)

- 🧰 Tutte le funzionalità del grafo non orientato
- ⚖️ Aggiunta di archi con pesi personalizzati
- 📏 Recupero e modifica dei pesi degli archi

## 📂 Struttura del Progetto

```
Foutih-Osama-20054809/
├── src/
│   ├── main/java/upo/algoritmi2/impl/
│   │   ├── Graph20054809.java
│   │   └── WeightedGraph20054809.java
│   └── test/java/upo/algoritmi2/impl/
│       ├── Graph20054809Test.java
│       └── WeightedGraph20054809Test.java
├── lib/
│   ├── Algoritmi2LabVC-1.0-SNAPSHOT.jar
│   ├── Algoritmi2LabVC-1.0-SNAPSHOT-javadoc.jar
│   └── pom.xml
├── pom.xml
└── README.md
```

## 🔧 Requisiti

- Java Development Kit (JDK) 21 o superiore
- Apache Maven

## 🚀 Configurazione dell'Ambiente

### 1. 📥 Creare un nuovo progetto Maven
   - File -> New -> Project
   - Selezionare Java e come Build System Maven
   - Configurare il progetto con le informazioni desiderate

### 2. 📦 Scompattare e posizionare la cartella lib
   - Scompattare la cartella `lib.zip` fornita con le istruzioni
   - Posizionare la cartella lib alla radice del progetto (allo stesso livello di src)

### 3. ⚙️ Configurare l'importazione delle librerie
   - Run -> Edit Configurations
   - Cliccare sul simbolo "+" e selezionare "Maven"
   - Assegnare un nome alla configurazione (es. "Import Interfaces")
   - Nel campo "Command line" inserire:
     ```
     install:install-file -Dfile=lib/Algoritmi2LabVC-1.0-SNAPSHOT.jar -DpomFile=lib/pom.xml -Djavadoc=lib/Algoritmi2LabVC-1.0-SNAPSHOT-javadoc.jar
     ```

### 4. ▶️ Eseguire il comando di importazione
   - Selezionare la configurazione appena creata
   - Cliccare su "Run" (simbolo play verde)

### 5. 📄 Aggiungere la dipendenza al file pom.xml
   - Aprire il file pom.xml del progetto
   - Aggiungere la seguente dipendenza all'interno del tag `<project>`:
     ```xml
     <dependencies>
         <dependency>
             <groupId>it.uniupo.algoritmi2</groupId>
             <artifactId>Algoritmi2LabVC</artifactId>
             <version>1.0-SNAPSHOT</version>
         </dependency>
     </dependencies>
     ```

### 6. 🔄 Ricaricare il progetto Maven
   - Nel menu Maven, cliccare sul simbolo con due frecce circolari ("Reload All Maven Projects")

### 7. 🏗️ Installare il progetto
   - Menu Maven -> Lifecycle -> install

⭐ **Nota**: Questo progetto è stato sviluppato come parte del corso di Algoritmi 2 all'Università del Piemonte Orientale.
