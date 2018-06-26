package redBook.part1introduction.bFunctionalDataStructures

/**
  * Generalize tail to the function drop, which removes the first n elements from a list.
  * Note that this function takes time proportional only to the number of elements being
  * dropped—we don’t need to make a copy of the entire List.
  */
class Drop {
  def drop[A](ls: List[A], n: Int): List[A] = {
    if (n<=0)
      ls
    else
      ls match {
        case Cons(_,tail) => drop(tail,n-1)
        case Nil => Nil
      }
  }
}

object Drop extends App {
  val droper : Drop = new Drop
  println(List.ListString)
  println(
    droper.drop(List.ListString,6)
  )
  println(
    droper.drop(List.ListString,3)
  )
  println(
    droper.drop(List.ListString,-3)
  )
  println(
    droper.drop(List.ListString,0)
  )
}
