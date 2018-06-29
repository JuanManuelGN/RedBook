package redBook.part1introduction.bFunctionalDataStructures

/**
  * Write a function map that generalizes modifying each element in a list while maintaining
  * the structure of the list.
  */
object Exercise318_Map extends App {
  val lsd = List.listDouble
  val lsi = List.listInteger
  println(lsd)
  println(
    List.map(lsd)(_.toString)
  )
  println(lsi)
  println(
    List.map(lsi)(_+1)
  )
}
