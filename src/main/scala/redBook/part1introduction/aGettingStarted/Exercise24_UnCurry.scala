package redBook.part1introduction.aGettingStarted

/**
  * Implement uncurry, which reverses the transformation of curry. Note that since =>
  * associates to the right, A => (B => C) can be written as A => B => C.
  */
class UnCurry {
  def uncurry[A,B,C](f: A => B => C): (A,B) => C =
    (a,b) => f(a)(b)
}

object UnCurry extends App {
  val unCurry = new UnCurry
  println(
    unCurry.uncurry[Int,Int,String](x => y => (x+y).toString)(2,3)
  )
}
