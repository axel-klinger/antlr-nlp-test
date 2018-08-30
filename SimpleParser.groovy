
// read input file
def text = new File('input.md').text
def dictionary = loadDictionary('de')
println "0. INPUT"
println text

// sentenceize
println "1. SENTENCIZED"
println sentenceize(text)
// def sentences = text.split('\\.')
// sentences.each { s ->
//   s = s.trim()
//   println s
// }

// each sentence tokenize
println "2. TOKENIZED"
sentences.each { s ->
  // def words = s.trim().split(' ')
  println tokenize(s) //.join(' | ')
}

// tag each sentence
println "3. TAGGED => TODO: prepare word form dictionary (wiktionary)"
sentences.each { s ->
  def words = s.trim().split(' ')
  // println words.join(' | ')
}
println "Ein [ART_S_M_N, ART_S_M_A, ...] Graph [NN_S_M_N] ...\n"

println "4. CREATE ALL POSSIBLE SENTENCES"

println "5. PARSE ALL POSSIBLE COMINATIONS AND PRINT ALL VALID SENTENCSES"
println "Ein [ARTUB] Graph [NN] ist [VAFIN] ein [ARTUB] ...\n"

println "6. OUTPUT"
println "(TEXT (SENT (NP) ...) (SENT (NP) ...))\n"

// DEBUGGING => done by TreeListener impls
// * color text in HTML with found tags
// * color text in HTML with unique parse results

def sentenceize(text) {
  return text.split('\\.')
}

def tokenize(sentence) {
  return sentence.trim().split(' ')
}

def tag(token) {
  token.each { t ->
    token += dictionary.get(t)
  }
  return token
}

def parse(tokenStream) {
  return
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
