package redBook.part1introduction.bFunctionalDataStructures

/**
  * Implement append in terms of either foldLeft or foldRight.
  */
object Exercise314_Append extends App {
  val ls = List.listInteger
  val rs = List.listIntegerOneElement
  println(ls)
  println(
    List.appendfr(ls,rs)
  )
  println(
    List.appendfl(ls,rs)
  )
}
