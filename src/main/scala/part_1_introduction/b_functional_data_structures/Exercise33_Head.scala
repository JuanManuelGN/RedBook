package part_1_introduction.b_functional_data_structures

/**
  * Using the same idea, implement the function setHead for replacing the first element
  * of a List with a different value.
  */
object Exercise33_Head extends App {
  println(
    List.setHead(5, List.listInteger)
  )
  println(
    List.setHead("z", List.listInteger)
  )
}
