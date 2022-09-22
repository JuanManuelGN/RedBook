package part_1_introduction.b_functional_data_structures

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
