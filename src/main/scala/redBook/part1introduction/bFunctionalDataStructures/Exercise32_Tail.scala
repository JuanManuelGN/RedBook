package redBook.part1introduction.bFunctionalDataStructures

/**
  * Implement the function tail for removing the first element of a List. Note that the
  * function takes constant time. What are different choices you could make in your
  * implementation if the List is Nil? We’ll return to this question in the next chapter.
  */
class Tail {
  def tail(xs : List[Int]) : List[Int] = {
    xs match {
      case Cons(_,tail) => tail
      case _ => Nil
    }
  }
}

object Tail extends App {
  val tail : Tail = new Tail
  println(
    tail.tail(List.listExample)
  )
}
