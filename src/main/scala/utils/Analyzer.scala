package utils

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Analyzer {
  private val dfa = DFA

  /**
    * @param value  position in tokenTableOfStrings of that string
    * @param ofType position in tokenTypes of the type of the tokken
    */
  case class Token(value: Int, ofType: Int)

  val tokenTypes: List[String] = List("error", "identifier", "operator", "keyword", "delimitator", "punctuation mark",
    "number constant", "string constatnt", "char constant", "space", "comment", "special character")
  val ERROR: Int = 0
  val IDENTIFIER: Int = 1
  val OPERATOR: Int = 2
  val KEYWORD: Int = 3
  val DELIMITATOR: Int = 4
  val PUNCTATION_MARK: Int = 5
  val NUMBER_CONSTANT: Int = 6
  val STRING_CONSTANT: Int = 7
  val CHAR_CONSTANTS: Int = 8
  val SPACES: Int = 9
  val COMMENT: Int = 10
  val SPECIAL_CHARACTER: Int = 11

  val tokenTableOfStrings: ListBuffer[String] = ListBuffer() // add all words that were created

  val nextAction: List[String] = List("continue", "blocked", "success", "error")
  val CONTINUE_IDX = 0
  val BLOCKED_IDX = 1
  val SUCCESS_IDX = 2
  val ERROR_IDX = 3

  case class StateChar(state: Int, char: Char)

  def createString(stateChars: List[StateChar]): String = {
    stateChars.map {
      chars => chars.char
    }.mkString
  }

  @tailrec
  def generate(remainingCode: String, action: String, visitedStates: List[StateChar], tokens: List[Token])
  : List[Token] = {
    remainingCode match {
      case "" => tokens ++ getTokken(createString(visitedStates), visitedStates.last.state)
      case string: String =>
        val nextState: Int = dfa.delta(visitedStates.last.state, string.head).getOrElse(-1)

        action match {
          case "continue" =>
            val nexttAction: String = nextState match {
              case -1 => nextAction(BLOCKED_IDX)
              case _ => nextAction(CONTINUE_IDX)
            }

            val nextString = if (nexttAction == nextAction(BLOCKED_IDX)) string else string.tail
            val nextVisitedState: List[StateChar] =
              if (nexttAction == nextAction(BLOCKED_IDX)) visitedStates
              else visitedStates ++ List(StateChar(nextState, string.head))
            generate(nextString, nexttAction, nextVisitedState, tokens)
          case "blocked" =>
            generate(string, nextAction(CONTINUE_IDX), List(StateChar(0, ' ')),
              tokens ++ getTokken(createString(visitedStates), visitedStates.last.state))

        }
    }
  }

  //Map of token(string) and type which is determined by final state
  def getTokken(input: String, finalState: Int): Option[Token] = {
    val indexOfInput: Int = tokenTableOfStrings.indexOf(input) match {
      case -1 =>
        tokenTableOfStrings += input.replace("\n", "\\n")
        tokenTableOfStrings.length - 1
      case index: Int => index
    }

    var ofType: Int = finalState match {
      case 1 => IDENTIFIER
      case 2 => NUMBER_CONSTANT
      case 3 => OPERATOR
      case 4 => OPERATOR
      case 5 => SPACES
      case 6 => DELIMITATOR
      case 10 => COMMENT
      case 11 => STRING_CONSTANT
      case 13 => COMMENT
      case 14 => OPERATOR
      case 17 => CHAR_CONSTANTS
      case 19 => NUMBER_CONSTANT
      case 20 => NUMBER_CONSTANT
      case 21 => NUMBER_CONSTANT
      case 23 => NUMBER_CONSTANT
      case 24 => OPERATOR
      case _ => ERROR //not a final state = fails
    }

    if (ofType == IDENTIFIER)
      if (Language.keyWords.contains(input.tail))
        ofType = KEYWORD
    if (ofType == OPERATOR)
      if (tokenTableOfStrings.contains(" \\n") && input.contains("\n"))
        ofType = SPECIAL_CHARACTER

    Some(Token(indexOfInput, ofType))
  }

  def printTokens(tokens: List[Token]): String = {
    val returnString = tokens.filter(token => token.ofType != SPACES /* && token.ofType != COMMENT*/).map {
      token =>
        tokenTableOfStrings(token.value) + " - " + tokenTypes(token.ofType) + '\n'
    }.mkString

    if(returnString.contains("error"))
      returnString.split("error")(0) + "error"
    else returnString
  }

}
