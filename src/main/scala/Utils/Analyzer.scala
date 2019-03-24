package Utils

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/**
  * ignore white spaces and comments
  */
object Analyzer {
  private val dfa = DFA

  /**
    *
    * @param value  position in tokenTableOfStrings of that string
    * @param ofType position in tokenTypes of the type of the tokken
    */
  case class Token(value: Int, ofType: Int)

  //TODO: fa float + baga stare in dfa
  val tokenTypes: List[String] = List("identifier", "operator", "keyword", "delimitator", "punctuation mark",
    "number constants", "string constatnts", "char constants", "space", "comment", "special character", "float constant")
  val IDENTIFIER: Int = 0
  val OPERATOR: Int = 1
  val KEYWORD: Int = 2
  val DELIMITATOR: Int = 3
  val PUNCTATION_MARK: Int = 4
  val NUMBER_CONSTANTS: Int = 5
  val STRING_CONSTANNTS: Int = 6
  val CHAR_CONSTANTS: Int = 7
  val SPACES: Int = 8
  val COMMENT: Int = 9
  val SPECIAL_CHARACTER: Int = 10
  val FLOAT_CONSTANT: Int = 11

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
          case "succes" => tokens
          case "error" => tokens
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
      case 2 => NUMBER_CONSTANTS
      case 3 => OPERATOR
      case 4 => OPERATOR
      case 5 => SPACES
      case 6 => DELIMITATOR
      case 10 => COMMENT
      case 11 => STRING_CONSTANNTS
      case 13 => COMMENT
      case 14 => OPERATOR
      case 17 => CHAR_CONSTANTS
      case 19 => FLOAT_CONSTANT
      case _ => -1 //fails
    }

    //TODO: see what you do when the final state is -1 //fails
    if (ofType == IDENTIFIER)
      if (Language.keyWords.contains(input.tail))
        ofType = KEYWORD
    if (ofType == OPERATOR)
      if (tokenTableOfStrings.contains(" \\n") && input.contains("\n"))
        ofType = SPECIAL_CHARACTER

    Some(Token(indexOfInput, ofType))
  }

  def printTokens(tokens: List[Token]): Unit = {
    tokens.foreach {
      token =>
        if (token.ofType != SPACES /* && token.ofType != COMMENT */ )
          println(tokenTableOfStrings(token.value) + " - " + tokenTypes(token.ofType))
    }
  }

}
