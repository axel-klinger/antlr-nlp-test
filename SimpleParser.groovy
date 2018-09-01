
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.*

// read input file
def text = new File('input.md').text
def dictionary = loadDictionary('de')
println "0. INPUT"
println text

// sentenceize
println "1. SENTENCIZED"
def sentences = sentenceize(text)
println sentences

sentences.each { s ->
  println "a) TOKENIZE"
  def words = tokenize(s) //.join(' | ')
  println words

  println "b) TAG"
  def altSentences = tag(s,dictionary)

  println ""
  println "c) PARSE"
  altSentences.each { a ->
    println a
    parse(a + '.')    // DUMMY (.) !!!
    println ""
  }

  println "c) PARSE ALL POSSIBLE COMINATIONS AND PRINT ALL VALID SENTENCSES"
  // def parsedSentences = parse(altSentences)
  // parsedSentences.each { s ->
  //   println s

}

// DEBUGGING => done by TreeListener impls
// * color text in HTML with found tags
// * color text in HTML with unique parse results

def sentenceize(text) {
  def sentences = text.split('\\.')
  def newSentences = [] as List
  sentences.each { s ->
    def sent = s.trim()
    if (sent.size() > 0) {
      newSentences.add(sent)
    }
  }
  return newSentences
}

def tokenize(sentence) {
  return sentence.trim().split(' ')
}

def tag(sentence, dictionary) {
  def altSentences = [] as List
  def words = tokenize(sentence)
  def taggedWords = []
  words.each { w ->
    def wfs = dictionary.get(w).split(',')  // [ART,ADV]
    def taggedWord = []
    wfs.each { wf ->
      taggedWord.add(w + '_' + wf.split(':')[0])
    }
    taggedWords.add(taggedWord)
  }
  // ... satz erzeugen ...
  def counterForAllWords = new Integer[taggedWords.size()]
  taggedWords.eachWithIndex { t,i ->
    counterForAllWords[i] = 0
  }

  def newSentence = ''
  def counter = 0
  nextWord(taggedWords[counter], counter, taggedWords, altSentences, newSentence)
  println taggedWords

  return altSentences // als Strings
}

def nextWord(taggedWord, counter, taggedWords, altSentences, newSentence) {
  counter++
  def originalNewSentence = newSentence
  taggedWord.each { tag ->
    newSentence += ' ' + tag
    if (counter<taggedWords.size()) {
      nextWord(taggedWords[counter], counter, taggedWords, altSentences, newSentence)
    } else {
      newSentence = newSentence.trim()
      altSentences.add(newSentence)
      println newSentence
      newSentence = ''
    }
    newSentence = originalNewSentence
  }
}

def parse(sentence) {
  ANTLRInputStream input = new ANTLRInputStream(sentence)
  SimpleGermanLexer lexer = new SimpleGermanLexer(input)
  CommonTokenStream tokens = new CommonTokenStream(lexer)
  SimpleGermanParser parser = new SimpleGermanParser(tokens)
  ParseTree tree = parser.satz()
  println tree.toStringTree(parser)
  return sentence
}

def loadDictionary(language) {
  def dictionaryText = new File('dictionary-' + language + '.csv').text
  def dictionary = [:]
  dictionaryText.eachLine { l ->
    def entry = l.split(';')
    if (entry.size() > 1) {
      dictionary.put(entry[0], entry[1])
    }
  }
  return dictionary
}
