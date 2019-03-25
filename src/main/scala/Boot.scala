import utils.{Analyzer, Scanner, Writter}

object Boot extends App {
  val scanner = new Scanner
  val writter = new Writter

  val inputCode = scanner.getLines
  val tokens = Analyzer.generate(inputCode, Analyzer.nextAction(Analyzer.CONTINUE_IDX), List(Analyzer.StateChar(0, ' ')), Nil)

  val output = Analyzer.printTokens(tokens)

  println(output)
  writter.printInFile(output)

  writter.close()
  scanner.close()
}
