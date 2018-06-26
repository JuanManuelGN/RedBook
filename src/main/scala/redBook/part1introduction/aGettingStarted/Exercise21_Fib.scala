package redBook.part1introduction.aGettingStarted

/**
  * Write a recursive function to get the nth Fibonacci number (http://mng.bz/C29s).
  * The first two Fibonacci numbers are 0 and 1. The nth number is always the sum of the
  * previous two—the sequence begins 0, 1, 1, 2, 3, 5. Your definition should use a
  * local tail-recursive function.
  */
class Fib {
  def fib(n: Int) : Long = fibTailRec(n)
  @annotation.tailrec
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
  println(fibonacci.fib(1000))
}
