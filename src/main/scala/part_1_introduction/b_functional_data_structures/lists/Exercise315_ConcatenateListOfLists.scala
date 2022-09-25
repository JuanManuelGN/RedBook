package part_1_introduction.b_functional_data_structures.lists

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
