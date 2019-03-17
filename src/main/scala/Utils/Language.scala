package Utils

object Language {
  val keyWords: List[String] = List("auto", "break", "case", "char", "const", "continue", "default", "do", "double",
    "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "'signed",
    "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while")

  val operators: List[Char] = List(',', '<', '>', '.', '_', '(', ')', '$', ':', '%', '[', ']', '#',
    '?', '\'', '&', '{', '}', '"', '^', '!', '*', '/', '|', '-', '\\', '~', '+', '\n', '\t')

  val characters: Set[Char] = ('a' to 'z').toSet ++ ('A' to 'Z')
  val numbers: Set[Char] = ('1' to '9').toSet

  val delimitator: List[Char] = List(';')
  val star: Char = '*'
  val slash: Char = '/'
  val space: Char = ' '
  val identifiers: Set[Char] = characters ++ numbers
  val notstar: Set[Char] = identifiers ++ operators.filter(_ != star)
  val notslash: Set[Char] = identifiers ++ operators.filter(_ != slash)

}
