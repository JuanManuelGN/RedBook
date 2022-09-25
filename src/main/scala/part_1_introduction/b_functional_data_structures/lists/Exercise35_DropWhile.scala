package part_1_introduction.b_functional_data_structures.lists

/**
 * Generalize tail to the function drop, which removes the first n elements from a list.
 * Note that this function takes time proportional only to the number of elements being
 * dropped—we don’t need to make a copy of the entire List.
 */
object Exercise35_DropWhile extends App {
  println(List.listInteger)
  println(
    List.dropWhile(List.listInteger, (x: Int) => x % 2 == 0)
  )
  println(
    List.dropWhile(List.listInteger, (x: Int) => x > 10)
  )
  println(
    List.dropWhile(List.listInteger, (x: Int) => x < 10)
  )
}
