package Utils

import scala.annotation.tailrec
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
  val DELIMITATOR: Int = 3
  val PUNCTATION_MARK: Int = 4
  val NUMBER_CONSTANTS: Int = 5
  val STRING_CONSTANNTS: Int = 6
  val CHAR_CONSTANTS: Int = 7

  val tokenTableOfStrings: ListBuffer[String] = ListBuffer() // add all words that were created

  val action: List[String] = List("continue", "blocked", "success", "error")
  val CONTINUE_IDX = 0
  val BLOCKED_IDX = 1
  val SUCCESS_IDX = 2
  val ERROR_IDX = 3

  @tailrec
  def generate(remainingCode: String, action: String, visitedStates: List[Int], tokens: List[Token]): List[Token] = {
    remainingCode match {
      case "" => tokens
      case string: String =>
    }
    //TODO: cand dau getTokken, ofer inputString-ul si starea finala in care s-a oprit pentru a afla ce fel de tokken este
    // cand creem mergem in stare si daca nu mai avem continuare = blocked = identificator nou
  }

  //Map of token(string) and type which is determined by final state
  def getTokken(input: String): Option[Token] = {
    val indexOfInput = tokenTableOfStrings.indexOf(input) match {
      case -1 =>
        tokenTableOfStrings += input
        tokenTableOfStrings.length - 1
      case index: Int => index
    }

    //TODO: search  which type of input is this and return it in token

    Some(Token(indexOfInput, 0))
  }

}
