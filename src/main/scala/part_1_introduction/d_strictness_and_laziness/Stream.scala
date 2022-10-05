package part_1_introduction.d_strictness_and_laziness

import part_1_introduction.d_strictness_and_laziness.Stream.empty

sealed trait Stream[+A] {

  /**
   * Exercise 5.1
   * @return
   */
  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(head, tail) => head() :: tail().toList
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 0 => Stream.cons(h(), t().take(n-1))
    case Cons(h, t) if n == 0 => empty
    case _ => empty
  }
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }
  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if(as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
