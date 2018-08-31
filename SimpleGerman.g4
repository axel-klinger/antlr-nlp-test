/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar SimpleGerman;

text: satz+;
satz: frage | aussage | aufforderung;
frage: vp np FRAGEZEICHEN;
aussage: np vp PUNKT;
aufforderung: vp AUSRUFEZEICHEN;

pp: PRP np;
adj: ADJ (',' ADJ)*;
np: n (',' n)* (CON n)?;
n: ART? adj? NN pp? | NE;
vp: VV np?;
// np: ART NN | NE; // object = subject

fragment WORD : ([a-z] | [A-Z])+;

PR: WORD '_PRPERS' | WORD '_PRDEM';

NE: WORD '_NE';
NN: WORD '_NN';
ART: WORD '_ART';
ADJ: WORD '_ADJ';
ADV: WORD '_ADV';
VV: WORD '_VV' | WORD '_VA';
CON: WORD '_CON';
PRP: WORD '_PRP';

PUNKT: '.';
FRAGEZEICHEN: '?';
AUSRUFEZEICHEN: '!';
WS : [ \t\r\n]+ -> skip ;

/*
ADJA:   'große' | 'kleine'; // [das] große [Haus]	// attributives Adjektiv
ADJD:   'schnell'; //	[er fährt] schnell, [er ist] schnell	// adverbiales oder prädikatives Adjektiv

ADV:	  'schon' | 'bald' | 'doch';	// Adverb

APPR:	  'in' | 'ohne'; // in [der Stadt], ohne [mich]	// Präposition; Zirkumposition links
APPRART:'im' | 'zur'; // im [Haus], zur [Sache]	// Präposition mit Artikel
APPO:   'zufolge' | 'wegen'; //	[ihm] zufolge, [der Sache] wegen	// Postposition
// APZR:	  'an'; // [von jetzt] an	// Zirkumposition rechts

ART:	  'der' | 'die' | 'das' | 'ein' | 'eine';	// bestimmter oder unbestimmter Artikel

CARD:	  'eins' | 'zwei'; // zwei [Männer], [im Jahre] 1994	// Kardinalzahl

// FM	[Er hat das mit ``] A big fish ['' übersetzt]	// Fremdsprachliches Material

ITJ:	  'mhm' | 'ach' | 'tja';	// Interjektion

KOUI:	  'um' | 'anstatt'; // um [zu leben], anstatt [zu fragen]	// unterordnende Konjunktion mit ``zu'' und Infinitiv
KOUS:	  'weil' | 'dass' | 'damit' | 'wenn' | 'ob';	// unterordnende Konjunktion mit Satz
KON:	  'und' | 'oder' | 'aber';	// nebenordnende Konjunktion
KOKOM:	'als' | 'wie';	// Vergleichskonjunktion

NN:	    'Haus' | 'Auto' | 'Hund' | 'Katze' | 'Tisch' | 'Herr' | 'Reisen'; // [das] Reisen	// normales Nomen
NE:	    'Hans' | 'Hamburg' | 'HSV';	// Eigennamen

// PDS:	  'dieser' | 'jener';	// substituierendes Demonstrativpronomen
// PDAT:	  'jener'; // jener [Mensch]	// attribuierendes Demonstrativpronomen

PIS:	  'keiner' | 'viele' | 'man' | 'niemand';	// substituierendes Indefinitpronomen
PIAT:	  'kein' | 'irgendein'; // kein [Mensch], irgendein [Glas]	// attribuierendes Indefinitpronomen ohne Determiner
PIDAT:  'wenig' | 'beiden'; //	[ein] wenig [Wasser], [die] beiden [Brüder]	// attribuierendes Indefinitpronomen mit Determiner

PPER:	  'ich' | 'er' | 'ihm' | 'mich' | 'dir';	// irreflexives Personalpronomen

PPOSS:	'meins' | 'deiner';	// substituierendes Possessivpronomen
PPOSAT: 'mein' | 'deine'; //	mein [Buch], deine [Mutter]	// attribuierendes Possessivpronomen

// PRELS:  'der'; //	[der Hund ,] der	// substituierendes Relativpronomen
PRELAT: 'dessen'; //	[der Mann ,] dessen [Hund]	// attribuierendes Relativpronomen

PRF:	  'sich' | 'einander' | 'dich' | 'mir';	// reflexives Personalpronomen

PWS:	  'wer' | 'was';	// substituierendes Interrogativpronomen
PWAT:	  'welche' | 'wessen'; // welche[Farbe], wessen [Hut]	// attribuierendes Interrogativpronomen
PWAV:	  'warum' | 'wo' | 'wann' | 'worüber' | 'wobei';	// adverbiales Interrogativ- oder Relativpronomen

PAV:	  'dafür' | 'dabei' | 'deswegen' | 'trotzdem';	// Pronominaladverb

// PTKZU:	'zu'; // zu [gehen]	// ``zu'' vor Infinitiv
PTKNEG:	'nicht';	// Negationspartikel
PTKVZ:  'an' | 'rad'; //	[er kommt] an, [er fährt] rad	// abgetrennter Verbzusatz
PTKANT:	'ja' | 'nein' | 'danke' | 'bitte';	// Antwortpartikel
// PTKA:	  'am' | 'zu'; // am [schönsten], zu [schnell]	// Partikel bei Adjektiv oder Adverb

TRUNC:	'An-'; // An- [und Abreise]	// Kompositions-Erstglied

VVFIN:  'gehst' | 'kommen' | 'geht' | 'steht' | 'frisst'; //	[du] gehst, [wir] kommen [an]	// finites Verb, voll
VVIMP:	'komm';  // [!]	// Imperativ, voll
VVINF:	'gehen' | 'ankommen';	// Infinitiv, voll
VVIZU:	'anzukommen' | 'loszulassen';	// Infinitiv mit ``zu'', voll
VVPP:	  'gegangen' | 'angekommen';	// Partizip Perfekt, voll
// VAFIN:  'bist' |'werden'; //	[du] bist, [wir] werden	// finites Verb, aux
VAIMP:	'sei'; // [ruhig !]	// Imperativ, aux
// VAINF:	'werden' | 'sein';	// Infinitiv, aux
VAPP:	  'gewesen';	// Partizip Perfekt, aux
VMFIN:	'dürfen';	// finites Verb, modal
VMINF:	'wollen';	// Infinitiv, modal
VMPP:	  'gekonnt';  //, [er hat gehen] können	// Partizip Perfekt, modal
		//
XY:	'3:7' | 'H2O' | 'D2XW3';	// Nichtwort, Sonderzeichen enthaltend */
		//
/* $,	,	// Komma
$.	. ? ! ; :	// Satzbeendende Interpunktion
$(	- [,]()	// sonstige Satzzeichen; satzintern */
