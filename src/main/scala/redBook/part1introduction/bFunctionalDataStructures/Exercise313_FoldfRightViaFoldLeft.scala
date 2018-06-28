package redBook.part1introduction.bFunctionalDataStructures

/**
  * Write a function that returns the reverse of a list (given List(1,2,3) it returns
  * List(3,2,1)). See if you can write it using a fold.
  */
object Exercise313_FoldfRightViaFoldLeft extends App {
  val ls = List.listInteger
  val rs = List.listIntegerTwoElement
  println(ls)
  println(rs)
  println(
    List.appendfrl(ls,rs)
  )
}
