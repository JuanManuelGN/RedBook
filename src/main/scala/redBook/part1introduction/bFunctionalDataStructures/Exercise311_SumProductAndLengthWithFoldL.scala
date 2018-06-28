package redBook.part1introduction.bFunctionalDataStructures

/**
  * Write sum, product, and a function to compute the length of a list using foldLeft.
  */
object Exercise311_SumProductAndLengthWithFoldL extends App {
  val ls = List.listInteger
  println(ls)
  println(
    List.sumfl(ls)
  )
  println(
    List.productfl(ls)
  )
  println(
    List.lengthfl(ls)
  )
}
