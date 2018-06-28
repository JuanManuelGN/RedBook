package redBook.part1introduction.bFunctionalDataStructures

/**
  * Write sum, product, and a function to compute the length of a list using foldLeft.
  */
class ListOpReverse {
  val tail : Tail = new Tail
  def reverse[A](ls: List[A]) : List[A] = FoldL.foldLeft(ls,List[A]())((acc,h) => Cons(h,acc))
}

object ListOpReverse extends App {
  val listOpR : ListOpReverse = new ListOpReverse
  val ls = List.listInteger
  println(ls)
  println(
    listOpR.reverse(ls)
  )
}
