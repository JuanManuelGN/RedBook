package part_1_introduction.b_functional_data_structures

/**
  * Write a function that turns each value in a List[Double] into a String. You can use
  * the expression d.toString to convert some d: Double to a String.
  */
object Exercise317_ListDoubleToListString extends App {
  val ls = List.listDouble
  println(ls)
  println(
    List.listDoubleToString(ls)
  )
}
