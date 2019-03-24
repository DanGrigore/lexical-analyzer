import scala.collection.mutable.ListBuffer

val x = ('1' to '9').toSet

val operators: List[Char] = List(',', '<', '>', '.', '_', '(', ')', ';', '$', ':', '%', '[', ']', '#',
  '?', '\'', '&', '{', '}', '"', '^', '!', '*', '/', '|', '-', '\\', '~', '+', '\n', '\t')
operators.size
operators.count(_ != '*')
val delimitator: List[Char] = List(';')

val transitions: Map[(Int, Char), Int] =
  operators.map(x => (0, x) -> 4).toMap ++ delimitator.map(x => (0, x) -> 6) ++ Map()

val tokenTableOfStrings: ListBuffer[String] = ListBuffer("Da", "NN", "tt") // add all words that were created

tokenTableOfStrings += "ttsd"
tokenTableOfStrings.length

val string: String = "ana are mere"

string.head
string.tail

val action: List[String] = List("continue", "blocked", "success", "error")
val CONTINUE_IDX: Int = 0
val BLOCKED_IDX = 1
val SUCCESS_IDX = 2
val ERROR_IDX = 3
action(BLOCKED_IDX).toString


val x1 = None.getOrElse(-1)

val list = List(1, 2, 3)
list ++ List(4)

list.last

case class rht(x: Int, y: Int)

val list2: List[rht] = List(rht(1, 1), rht(2, 2), rht(3, 3))


list2.map {
  x => x.y
}.mkString



val list3 = List(rht(1, 2), rht(1, 2))

list3(list3.length - 2)
val nn = "do {tiparire(\"suma=%d\\n\",s += f( i++ ));\n}while (i<10);"
if (nn.contains("\n") )println("ds")

val keyWords: List[String] = List("auto", "break", "case", "char", "const", "continue", "default", "do", "double",
  "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "'signed",
  "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while")

keyWords.contains("break2")

val string2 =" \\\\n"
string2.tail
val listOfChars:List[Char] = List('\n', '\t')
val stringlist =( listOfChars mkString) contains(string2.tail)