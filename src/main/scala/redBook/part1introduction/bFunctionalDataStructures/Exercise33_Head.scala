package redBook.part1introduction.bFunctionalDataStructures

/**
  * Using the same idea, implement the function setHead for replacing the first element
  * of a List with a different value.
  */
class Head {
  def setHead[A](x : A, xs : List[A]) : List[A] = {
    xs match {
      case Cons(a,tail) => Cons(x,tail)
      case Cons(a,Nil) => Cons(x,Nil)
      case _ => Nil
    }
  }
}

object Head extends App {
  val head : Head = new Head
  println(
    head.setHead(5, List.listInteger)
  )
  println(
    head.setHead("z", List.listInteger)
  )
}
