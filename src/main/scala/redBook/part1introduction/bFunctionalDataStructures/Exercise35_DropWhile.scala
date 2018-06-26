package redBook.part1introduction.bFunctionalDataStructures

/**
  * Generalize tail to the function drop, which removes the first n elements from a list.
  * Note that this function takes time proportional only to the number of elements being
  * dropped—we don’t need to make a copy of the entire List.
  */
class DropWhile {
  def dropWhile[A](ls: List[A], f: A => Boolean): List[A] = {
    ls match {
      case Cons(a,tail) =>
        if(f(a))
          dropWhile(tail,f)
        else
          Cons(a,dropWhile(tail,f))
      case Nil => Nil
    }
  }
}

object DropWhile extends App {
  val droper : DropWhile = new DropWhile
  println(List.listInteger)
  println(
    droper.dropWhile(List.listInteger, (x:Int) => x%2==0)
  )
  println(
    droper.dropWhile(List.listInteger, (x:Int) => x>10)
  )
  println(
    droper.dropWhile(List.listInteger, (x:Int) => x<10)
  )
}
