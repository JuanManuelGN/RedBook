package redBook.part1introduction.bFunctionalDataStructures

/**
  * Compute the length of a list using foldRight.
  */
object Exercise39_FoldR extends App {
  val ls = List.listInteger
  println(ls)
  println(
    List.length(ls)
  )
  println(
    List.length(Nil)
  )
}
