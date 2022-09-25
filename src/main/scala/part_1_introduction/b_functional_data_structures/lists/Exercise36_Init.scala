package part_1_introduction.b_functional_data_structures.lists

/**
 * Not everything works out so nicely. Implement a function, init, that returns a List
 * consisting of all but the last element of a List. So, given List(1,2,3,4), init will
 * return List(1,2,3). Why can’t this function be implemented in constant time like
 * tail?
 */
object Exercise36_Init extends App {
  val ls = List.listInteger
  println(ls)
  println(
    List.init(ls)
  )
  println(
    List.init(Nil)
  )
  println(
    List.init(List.listIntegerOneElement)
  )
}
