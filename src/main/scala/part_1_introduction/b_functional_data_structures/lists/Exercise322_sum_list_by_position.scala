package part_1_introduction.b_functional_data_structures.lists

/**
 * Use flatMap to implement filter
 */
object Exercise322_sum_list_by_position extends App {
  /**
   * With Integers
   */
  val lsi1 = List.listInteger
  val lsi2 = List.listInteger2
  println(s"list: \n $lsi1 \n $lsi2")

  println(
    List.sumList(lsi1, lsi2)
  )
}
