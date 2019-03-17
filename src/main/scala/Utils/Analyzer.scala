package Utils

import scala.collection.mutable.ListBuffer

/**
  * ignore white spaces and comments
  */
object Analyzer {
  private val dfa = DFA
  private val language = Language
  private val scanner = new Scanner

  /**
    *
    * @param value  position in tokenTableOfStrings of that string
    * @param ofType position in tokenTypes of the type of the tokken
    */
  case class Token(value: Int, ofType: Int)

  val tokenTypes: List[String] = List("identifier", "operator", "keyword", "delimitator", "punctuation mark", "number constants",
    "string constatnts", "char constants")
  val IDENTIFIER: Int = 0
  val OPERATOR: Int = 1
  val KEY_WORD: Int = 2
  val DELIMITATOR: Int= 3
  val PUNCTATION_MARK: Int = 4
  val NUMBER_CONSTANTS: Int = 5
  val STRING_CONSTANNTS: Int = 6
  val CHAR_CONSTANTS: Int = 7

  val tokenTableOfStrings: ListBuffer[String] = ListBuffer() // add all words that were created


  //Map of token(string) and type which is determined by final state
  def getTokken: Option[Token] = {

    Some(Token(0, 0))
  }

}
