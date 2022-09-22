package part_1_introduction.a_getting_started

/**
  * Implement the higher-order function that composes two functions.
  */
class Compose {
  def compose[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))
}

object Compose extends App {
  val comp = new Compose
  println(
    comp.compose[Int,Int,Int](x => 3*x,y => y*y)(2)
  )
}
