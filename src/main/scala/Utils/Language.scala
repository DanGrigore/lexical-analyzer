package Utils

object Language {
  val keyWords: List[String] = List("auto", "break", "case", "char", "const", "continue", "default", "do", "double",
    "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "'signed",
    "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while", "import")

  val operators: List[Char] = List(',', '.', '(', ')', ':', '%', '[', ']', '#',
    '?', '^', '!', '/', '|', '~', '\n', '\t')

  val characters: Set[Char] = ('a' to 'z').toSet ++ ('A' to 'Z') ++ Set('_')
  val hexChars: Set[Char] = ('a' to 'f').toSet ++ ('A' to 'F')
  val numbers: Set[Char] = ('0' to '9').toSet

  val delimitator: List[Char] = List(';', '{', '}')
  val specialCharacters: List[Char] = List('\n', '\t', '\b', '\f', '\r', '\\', '\'', '\"')
  val quotation_mark: Char = '"'
  val endLine: Char = '\n'
  val star: Char = '*'
  val slash: Char = '/'
  val space: Char = ' '
  val apostrophe: Char = '\''
  val point: Char = '.'
  val identifiers: Set[Char] = characters ++ numbers
  val doubleOperators: Set[Char] = Set('&', '|', '<', '>', '+', '-', '=', '*')
  val notstar: Set[Char] = identifiers ++ operators.filter(_ != star) ++ List(space, quotation_mark, apostrophe) ++
    delimitator ++ specialCharacters ++ doubleOperators
  val notslash: Set[Char] = identifiers ++ operators.filter(_ != slash) ++ List(space, quotation_mark, apostrophe) ++
    delimitator ++ specialCharacters ++ doubleOperators
  val hexNumbers: Set[Char] = numbers ++ hexChars

  val allLanguage: Set[Char] = identifiers ++ delimitator ++ List(star, slash, space, quotation_mark, apostrophe) ++
    operators ++ specialCharacters ++ doubleOperators

}
