# Natural Language Parser

Ein einfacher Parser für die deutsche Sprache (und EN, ES, ...) auf der Basis von ANTLR4. Es wird zunächst auf weitere Bibliotheken verzichtet. Tokenizer und andere Tools können bei Bedarf einfach durch stärkere, externe Tools ersetzt werden.

* Sprache: Java 8+

## Arbeitspakete

### Wörterbuch DE, EN, ES, ...
Ein einfaches Wörterbuch für eine möglichst große Abbildung der Sprache wird zunächst manuell erstellt. Nachhaltig wird das Wörterbuch mit Inhalten des Wiktionary in mehreren Sprachen gefüllt.
* Wöerterbuch als HSQL
* SpringBoot | Micronaut für Aufbau der DB
* Export WF -> flat file
* [=> Import Wiktionary to DB]

### Sentencer DE, EN, ES, ...
Zerlegung eines Textes in Sätze. Hier wird zunächst von einer einfachen Zerlegung nach PUNKT, AUSRUFEZEICHEN und FRAGEZEICHEN ausgegangen. Punkte in Abkürzungen werden zunächst nicht berücksichtigt. Dies kann über die Einbindung externer Bibliotheken, wie OpenNLP erweitert werden.

### Tokenizer DE, EN, ES, ...
Zerlegung eines Satzes in Wörter (und Trennzeichen, wie '-', ',' und ';'). Hier wird zunächst von einer einfachen Zerlegung nach LEERZEICHEN, KOMMA und GEDANKENSTRICH ausgegangen. weitere Merkmale werden zunächst nicht berücksichtigt. Dies kann über die Einbindung externer Bibliotheken, wie OpenNLP erweitert werden.

### Parser DE, EN, ES, ... // mit ANTLR (und Predicates oder TreeWalker/Listener)
* mit Wortarten und Satzteilen
  * N // Objekt
  * NP // Objekt Phrase
  * NN // Klasse (TypID)
  * NE // Instanz
  * ART // bestimmter Artikel
  * ARTUB // unbestimmter Artikel
  * ADJ // Adjektiv
  * ADV // Adverb
  * ADVLOC // Adverbiale Bestimmung des Ortes
  * ADVTIM // Adverbiale Bestimmung der Zeit
  * ADVKIN // Adverbiale Bestimmung der Art und Weise
  * V // Verb
  * VP // Verbal Phrase
  * VPART //  Partizip (X?)
  * CON // Konjunktion
  * PR // Pronomen
  * PP // Pronominal Phrase
  * PRPER // Personalpronomen
  * PRIND // Indefinitpronomen
  * PRPOS // Possessivpronomen
  * PRREF // Reflexivpronomen
  * PRDEM // Demonstrativpronomen
  * PRINT // Interrogativpronomen
  * PRREL // Relativpronomen
  * PRREZ // Reziprogpronomen
  * [PREP // Repräsentativpronomen] ???
* Regeln: ... // Satz-Umstellungen berücksichtigen SUB P DAT | DAT P SUB
