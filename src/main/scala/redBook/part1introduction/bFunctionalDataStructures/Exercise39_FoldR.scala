package redBook.part1introduction.bFunctionalDataStructures

/**
  * Not everything works out so nicely. Implement a function, init, that returns a List
  * consisting of all but the last element of a List. So, given List(1,2,3,4), init will
  * return List(1,2,3). Why can’t this function be implemented in constant time like
  * tail?
  */
class FoldR {
  def length[A](as: List[A]): Int = List.foldRight(as,0)((a,acc) => acc + 1)
}

object FoldR extends App {
  val foldR : FoldR = new FoldR
  val ls = List.listInteger
  println(ls)
  println(
    foldR.length(ls)
  )
  println(
    foldR.length(Nil)
  )
}
