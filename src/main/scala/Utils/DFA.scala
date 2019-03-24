package Utils

object DFA {
  val initialState = 0
  val states: List[Int] = (0 to 19).toList
  val finalStates = List(1, 2, 3, 4, 5, 6, 10, 11, 13, 14, 17, 19)
  val transitions: Map[(Int, Char), Int] =
    Language.operators.map(x => (0, x) -> 4).toMap ++
      Language.delimitator.map(x => (0, x) -> 6) ++
      Language.notstar.map(x => (8, x) -> 8) ++
      Language.notslash.map(x => (9, x) -> 8) ++
      Language.numbers.map(x => (0, x) -> 2) ++
      Language.numbers.map(x => (2, x) -> 2) ++
      Language.numbers.map(x => (18, x) -> 19) ++
      Language.numbers.map(x => (19, x) -> 19) ++
      Language.characters.map(x => (0, x) -> 1) ++
      Language.identifiers.map(x => (1, x) -> 1) ++
      Language.allLanguage.map(x => (7, x) -> 7) ++
      Language.allLanguage.map(x => (12, x) -> 12) ++
      Language.allLanguage.map(x => (15, x) -> 16) ++
      Language.doubleOperators.map(x => (4, x) -> 14) ++
      Map(
        (0, Language.quotation_mark) -> 7,
        (7, Language.quotation_mark) -> 11,
        (0, Language.space) -> 5,
        (5, Language.space) -> 5,
        (0, Language.slash) -> 3,
        (3, Language.slash) -> 12,
        (3, Language.star) -> 8,
        (8, Language.star) -> 9,
        (9, Language.slash) -> 10,
        (12, Language.endLine) -> 13,
        (0, Language.apostrophe) -> 15,
        (16, Language.apostrophe) -> 17,
        (2, Language.point) -> 18
      )

  def delta(state: Int, symbol: Char): Option[Int] = {
    transitions.get(state, symbol)
  }

}
