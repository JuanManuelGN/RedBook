package part_1_introduction.d_strictness_and_laziness

import part_1_introduction.d_strictness_and_laziness.Stream.{cons, empty}

sealed trait Stream[+A] {

  /**
   * Exercise 5.1
   *
   * @return
   */
  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(head, tail) => head() :: tail().toList
  }

  /**
   * Exercise 5.2
   *
   * @param n
   * @return
   */
  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 0 => Stream.cons(h(), t().take(n - 1))
    case Cons(h, t) if n == 0 => empty
    case _ => empty
  }

  /**
   * Exercise 5.2 Skipping the first n elements
   *
   * @param n
   * @return
   */
  def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  /**
   * Exercise 5.3 returning all starting elements of a Stream that
   * match the given predicate.
   *
   * @param f
   * @return
   */
  def takeWhile(f: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if f(h()) => cons(h(), t().takeWhile(f))
    case _ => empty
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B =
    this match {
      case Cons(h, t) => f(h(), t().foldRight(z)(f))
      case _ => z
    }

  /**
   * Exercise 5.4
   * Implement forAll, which checks that all elements in the Stream match a given predi-
   * cate. Your implementation should terminate the traversal as soon as it encounters a
   * nonmatching value.
   *
   * @param p
   * @return
   */
  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a, b) => p(a) && b)

  /**
   * Exercise 5.5
   * @param f
   * @return
   */
  def takeWhileFR(f: A => Boolean): Stream[A] =
    foldRight(empty[A])((a, acc) => if (f(a)) cons(a, acc) else empty)

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  /**
   * Exercise 5.6
   * @return
   */
  def headOptionFR: Option[A] =
    foldRight(None: Option[A])((h,_) => Some(h))

  /**
   * Exercise 5.7 map using foldRight
   * @param f
   * @tparam B
   * @return
   */
  def mapFR[B](f: A => B): Stream[B] =
    foldRight(empty[B])((h,t) => cons(f(h), t))

  /**
   * Exercise 5.7 filter using foldRight
   *
   * @param f
   * @tparam B
   * @return
   */
  def filterFR(p: A => Boolean): Stream[A] =
    foldRight(empty[A])((h, t) => if (p(h)) {
      cons(h, t)
    } else {
      t
    })

  /**
   * Exercise 5.7 append using foldRight
   * @param xs
   * @tparam B
   * @return
   */
  def append[B>:A](xs: => Stream[B]): Stream[B] =
    foldRight(xs)((h, t) => cons(h, t))

  /**
   * Exercise 5.7 flatMap using foldRight
   * @param f
   * @tparam B
   * @return
   */
  def flatMapFR[B](f: A => Stream[B]): Stream[B] =
    foldRight(empty[B])((h, t) => f(h).append(t))
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
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
