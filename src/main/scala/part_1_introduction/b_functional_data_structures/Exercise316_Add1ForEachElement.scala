package part_1_introduction.b_functional_data_structures

/**
  * Implement append in terms of either foldLeft or foldRight.
  */
object Exercise316_Add1ForEachElement extends App {
  val ls = List.listInteger
  println(ls)
  println(
    List.add1(ls)
  )
}
