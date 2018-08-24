/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar test;

text: satz+;
satz: frage | aussage | aufforderung;
frage: praedikat subjekt FRAGEZEICHEN;
aussage: subjekt praedikat PUNKT;
aufforderung: praedikat AUSRUFEZEICHEN;

subjekt: ARTIKEL SUBSTANTIV | SUBSTANTIV;
praedikat: VERB objekt?;
objekt: ARTIKEL SUBSTANTIV | SUBSTANTIV;

ARTIKEL: 'der' | 'die' | 'das' | 'ein' | 'eine';
SUBSTANTIV: 'Haus' | 'Auto' | 'Hund' | 'Katze';
VERB: 'geht' | 'steht' | 'frisst';
PUNKT: '.';
FRAGEZEICHEN: '?';
AUSRUFEZEICHEN: '!';
WS : [ \t\r\n]+ -> skip ;
