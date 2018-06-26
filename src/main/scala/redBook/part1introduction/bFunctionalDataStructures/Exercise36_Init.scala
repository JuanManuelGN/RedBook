package redBook.part1introduction.bFunctionalDataStructures

/**
  * Generalize tail to the function drop, which removes the first n elements from a list.
  * Note that this function takes time proportional only to the number of elements being
  * dropped—we don’t need to make a copy of the entire List.
  */
class Init {
  def init[A](ls: List[A]): List[A] = {
    ls match {
      case Cons(a,Nil) => Nil
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
}
