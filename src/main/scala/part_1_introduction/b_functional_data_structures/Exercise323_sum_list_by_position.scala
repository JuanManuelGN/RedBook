package part_1_introduction.b_functional_data_structures

/**
  * Use flatMap to implement filter
  */
object Exercise323_zipWith extends App {
  /**
   * With Integers
   */
  val lsi1 = List.listInteger
  val lsi2 = List.listInteger2
  val lsS = List.listString
  println(s"lists: \n $lsi1 \n $lsi2 \n $lsS \n")

  println(
    List.zipWith(lsi1, lsi2)(_ - _)
  )
  println(
    List.zipWith(lsi1, lsi2)(_ + _)
  )
  println(
    List.zipWith(lsS, lsS)(_ + _)
  )
}
