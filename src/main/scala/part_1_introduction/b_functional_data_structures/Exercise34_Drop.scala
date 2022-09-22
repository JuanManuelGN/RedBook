package part_1_introduction.b_functional_data_structures

/**
  * Generalize tail to the function drop, which removes the first n elements from a list.
  * Note that this function takes time proportional only to the number of elements being
  * dropped—we don’t need to make a copy of the entire List.
  */
object Exercise34_Drop extends App {
  println(List.listString)
  println(
    List.drop(List.listString,6)
  )
  println(
    List.drop(List.listString,3)
  )
  println(
    List.drop(List.listString,-3)
  )
  println(
    List.drop(List.listString,0)
  )
}
