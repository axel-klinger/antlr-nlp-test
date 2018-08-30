
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
  altSentences.each { x ->
    println x
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

// "ich bin" -> "[ich_PRPERS bin_VA, ich_XXX bin_VA]"
// dict + sent -> [sa, sb, sc]
def tag(sentence, dictionary) {
  def altSentences = [] as List
  def words = tokenize(sentence)
  def taggedWords = []
  // für jedes Wort x Worte mit Form erzeugen
  words.each { w ->
    // println w
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

  // 18x (n1 x n2 x n3 x ... x nX) Satz aufbauen ...
  def newSentence = ''

/*
  sx
  w1.each form w1x
    wordindex ++
    sx += w1x + rest(w wordIndex)
    wordindex --

  rest(nextTaggedWord) {
    if (wordindex = taggedwords.size()) {
      return
    }
    ...
  }

*/




  // feedSentence
    // fill and add new sentence!
  println taggedWords


  // for (int i = 0; i<words.size(), i++) {
  //   taggedWords[i].each { t ->
  //
  //   }
  //
  // }


  return altSentences // als Strings
}

def parse(altSentences) {
  return altSentences
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
