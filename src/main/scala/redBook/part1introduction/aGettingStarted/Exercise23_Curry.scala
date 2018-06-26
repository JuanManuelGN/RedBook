package redBook.part1introduction.aGettingStarted

/**
  * Let’s look at another example, currying,9 which converts a function f of two arguments
  * into a function of one argument that partially applies f. Here again there’s only one
  * implementation that compiles. Write this implementation.
  */
class Curry {
  def curry[A,B,C](f: (A, B) => C): A => (B => C) =
    a => b => f(a,b)
}
object Curry extends App {
  val curry = new Curry
  println(
    curry.curry[Int,Int,String]((x,y) => (x+y).toString)(2)(3)
  )
}
