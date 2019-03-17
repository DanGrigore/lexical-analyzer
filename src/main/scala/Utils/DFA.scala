package Utils

object DFA {
  val initialState = 0
  val states: List[Int] = (0 to 10).toList
  val finalStates = List(1, 2, 3)
  val transitions: Map[(Int, Char), Int] =
    Language.operators.map(x => (0, x) -> 4).toMap ++
      Language.delimitator.map(x => (0, x) -> 6) ++
      Language.notstar.map(x => (8, x) -> 8) ++
      Language.notslash.map(x => (9, x) -> 8) ++
      Language.numbers.map(x => (0, x) -> 2) ++
      Language.numbers.map(x => (2, x) -> 2) ++
      Language.characters.map(x => (0, x) -> 1) ++
      Language.identifiers.map(x => (1, x) -> 1) ++
      Map(
        (0, Language.space) -> 5,
        (5, Language.space) -> 5,
        (0, Language.slash) -> 3,
        (3, Language.star) -> 8,
        (8, Language.star) -> 9,
        (9, Language.slash) -> 10
      )


  private def delta(state: Int, symbol: Char): Either[Boolean, Int] = {
    transitions.get(state, symbol) match {
      case Some(nextState) => Right(nextState)
      case None => Left(false)
    }
  }

 /* def checkInput(input: String) = {
    input.map(x => delta(initialState, x))
  }*/

}
