package redBook.part1introduction.aGettingStarted

class Fib {
  def fib(n: Int) : Long = fibTailRec(n)
  private def fibTailRec(n: Int, a: Long = 0, b: Long = 1): Long = {
    n match {
      case 0 => a
      case 1 => b
      case n => fibTailRec(n-1,b,a+b)
    }
  }
}

object FibTest extends App {
  val fibonacci : Fib = new Fib
  println(fibonacci.fib(100))

}
