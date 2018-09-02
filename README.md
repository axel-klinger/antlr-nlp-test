# Natural Language Parser

Ein einfacher Parser für die deutsche Sprache (und EN, ES, ...) auf der Basis von ANTLR4. Es wird zunächst auf weitere Bibliotheken verzichtet. Tokenizer und andere Tools können bei Bedarf einfach durch stärkere, externe Tools ersetzt werden.

* Sprache: Java 8+

## Arbeitspakete

Die nächsten Schritte ...

### Wörterbuch DE, EN, ES, ...
Ein einfaches Wörterbuch für eine möglichst große Abbildung der Sprache wird zunächst manuell erstellt. Nachhaltig wird das Wörterbuch mit Inhalten des Wiktionary in mehreren Sprachen gefüllt.
* Wöerterbuch als HSQL
* SpringBoot | Micronaut für Aufbau der DB
* Export WF -> flat file
* [=> Import Wiktionary to DB]

```
// alt. DB: Graphen -> Graph NN
// W:  Graph NN, M, TypID (TID) oder ConceptID (CID)
// WF: Graph S_N -> NN Graph
// WF: Graphen S_G, S_D, S_A, P_N, P_G, P_D, P_A -> NN Graph
// => Wortformen generieren (s.o.)
```

### Sentencer DE, EN, ES, ...
Zerlegung eines Textes in Sätze. Hier wird zunächst von einer einfachen Zerlegung nach PUNKT, AUSRUFEZEICHEN und FRAGEZEICHEN ausgegangen. Punkte in Abkürzungen werden zunächst nicht berücksichtigt. Dies kann über die Einbindung externer Bibliotheken, wie OpenNLP erweitert werden.

### Tokenizer DE, EN, ES, ...
Zerlegung eines Satzes in Wörter (und Trennzeichen, wie '-', ',' und ';'). Hier wird zunächst von einer einfachen Zerlegung nach LEERZEICHEN, KOMMA und GEDANKENSTRICH ausgegangen. weitere Merkmale werden zunächst nicht berücksichtigt. Dies kann über die Einbindung externer Bibliotheken, wie OpenNLP erweitert werden.

### Parser DE, EN, ES, ... // mit ANTLR (und Predicates oder TreeWalker/Listener)

