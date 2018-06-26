package redBook.part1introduction.bFunctionalDataStructures

/**
  * Not everything works out so nicely. Implement a function, init, that returns a List
  * consisting of all but the last element of a List. So, given List(1,2,3,4), init will
  * return List(1,2,3). Why can’t this function be implemented in constant time like
  * tail?
  */
class Init {
  def init[A](ls: List[A]): List[A] = {
    ls match {
      case Cons(_,Nil) => Nil
      case Nil => Nil
      case Cons(a,tail) => Cons(a,init(tail))
    }
  }
}

object Init extends App {
  val init : Init = new Init
  val ls = List.listInteger
  println(ls)
  println(
    init.init(ls)
  )
  println(
    init.init(Nil)
  )
  println(
    init.init(List.listIntegerOneElement)
  )
}
