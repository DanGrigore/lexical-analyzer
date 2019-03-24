import Utils.{Analyzer, Scanner}

object Boot extends App {
  val scanner = new Scanner
  val inputCode = scanner.lines
  val tokens = Analyzer.generate(inputCode, Analyzer.nextAction(Analyzer.CONTINUE_IDX), List(Analyzer.StateChar(0, ' ')), Nil)

  //println(tokens)
  //println(Analyzer.tokenTableOfStrings)
  Analyzer.printTokens(tokens)
  scanner.close()
}
