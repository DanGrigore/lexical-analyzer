
val x = ('1' to '9').toSet

val operators: List[Char] = List(',', '<', '>', '.', '_', '(', ')', ';', '$', ':', '%', '[', ']', '#',
  '?', '\'', '&', '{', '}', '"', '^', '!', '*', '/', '|', '-', '\\', '~', '+', '\n', '\t')
operators.size
operators.count(_ != '*')
val delimitator: List[Char] = List(';')



val transitions: Map[(Int, Char), Int] =
  operators.map(x => (0, x) -> 4).toMap ++ delimitator.map(x => (0, x) -> 6) ++ Map()