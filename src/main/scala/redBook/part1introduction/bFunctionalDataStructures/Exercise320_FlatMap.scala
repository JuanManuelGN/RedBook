package redBook.part1introduction.bFunctionalDataStructures

/**
  * Write a function flatMap that works like map except that the function given will return
  * a list instead of a single result, and that list should be inserted into the final resulting
  * list. Here is its signature:
  * def flatMap[A,B](as: List[A])(f: A => List[B]): List[B]
  * For instance, flatMap(List(1,2,3))(i => List(i,i)) should result in
  * List(1,1,2,2,3,3).
  */
object Exercise320_FlatMap extends App {
  val lsi = List.listInteger
  // Lista original
  println(lsi)
  // Lista después de computar el map con la función i => List(i,i)
  println(List.map(lsi)(i => List(i,i)))
  
  println(List.flatMap(lsi)(i => List(i,i)))
}
