package redBook.part1introduction.bFunctionalDataStructures

/**
  * Implement append in terms of either foldLeft or foldRight.
  */
object Exercise315_ConcatenateListOfLists extends App {
  val ls = List.listOfListInteger
  println(ls)
  println(
    List.concatListOfListAndFlat(ls)
  )
}
