import Utils.{Analyzer, Scanner}

object Boot extends App {
  val scanner = new Scanner
  val inputCode = scanner.lines
  Analyzer.generate(inputCode, Analyzer.action(Analyzer.CONTINUE_IDX), List(0), Nil)
  scanner.close()
}
