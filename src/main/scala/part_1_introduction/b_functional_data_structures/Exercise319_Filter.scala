package part_1_introduction.b_functional_data_structures

/**
  * Write a function map that generalizes modifying each element in a list while maintaining
  * the structure of the list.
  */
object Exercise319_Filter extends App {
  val lsi = List.listInteger
  println(lsi)
  println(
    List.filter(lsi)(_%2==0)
  )
  println(
    List.filterfl(lsi)(_%2==0)
  )
}
