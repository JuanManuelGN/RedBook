package part_1_introduction.b_functional_data_structures.lists

/**
 * Use flatMap to implement filter
 */
object Exercise321_FlatMap_as_Filter extends App {
  /**
   * With Integers
   */
  val lsi = List.listInteger
  println(lsi)

  def filter: Int => Boolean = _ > 3

  println(
    List.filterViaFlatMap(lsi)(filter)
  )

  /**
   * With characters
   */
  val ls = List.listString
  println(ls)

  def filterS: String => Boolean = _.length > 1

  println(
    List.filterViaFlatMap(ls)(filterS)
  )
}
