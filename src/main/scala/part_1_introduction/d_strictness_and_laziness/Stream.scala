package part_1_introduction.d_strictness_and_laziness

import part_1_introduction.d_strictness_and_laziness.Stream.{cons, empty, unfold}

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
   *
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
   *
   * @return
   */
  def headOptionFR: Option[A] =
    foldRight(None: Option[A])((h, _) => Some(h))

  /**
   * Exercise 5.7 map using foldRight
   *
   * @param f
   * @tparam B
   * @return
   */
  def mapFR[B](f: A => B): Stream[B] =
    foldRight(empty[B])((h, t) => cons(f(h), t))

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
   *
   * @param xs
   * @tparam B
   * @return
   */
  def append[B >: A](xs: => Stream[B]): Stream[B] =
    foldRight(xs)((h, t) => cons(h, t))

  /**
   * Exercise 5.7 flatMap using foldRight
   *
   * @param f
   * @tparam B
   * @return
   */
  def flatMapFR[B](f: A => Stream[B]): Stream[B] =
    foldRight(empty[B])((h, t) => f(h).append(t))

  /**
   * Exercise 5.13
   * @param f
   * @tparam B
   * @return
   */
  def mapUnFold[B](f: A => B): Stream[B] =
    Stream.unfold(this) {
      case Cons(h, t) => Some((f(h()), t()))
      case _ => None
    }

  def takeUnFold(n: Int): Stream[A] =
    Stream.unfold((this, n)) {
      case (Cons(h, _),1)=> Some((h(), (empty, 0)))
      case (Cons(h, t),n) if n > 1 => Some((h(), (t(), n-1)))
      case _ => None
    }

  def takeWhileUnFold(f: A => Boolean): Stream[A] =
    Stream.unfold(this) {
      case Cons(h, t) if f(h()) => Some((h(), t()))
      case _ => None
    }

  def zipWith[B,C](s2: Stream[B])(f: (A,B) => C): Stream[C] =
    unfold((this, s2)) {
      case (Empty, _) => None
      case (_, Empty) => None
      case(Cons(h1, t1), Cons(h2, t2)) => Some(f(h1(), h2()), (t1(), t2()))
    }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A],Option[B])] =
    unfold((this, s2)) {
      case (Empty, Empty) => None
      case (Empty, Cons(h,t)) => Some((None, Some(h())), (Empty, t()))
      case (Cons(h,t), Empty) => Some((Some(h()), None), (t(), Empty))
      case (Cons(h1, t1), Cons(h2, t2)) => Some((Some(h1()), Some(h2())), (t1(), t2()))
    }

  /**
   * Exercise 5.15
   * Implement tails using unfold. For a given Stream, tails returns the Stream of suf-
   * fixes of the input sequence, starting with the original Stream. For example, given
   * Stream(1,2,3), it would return Stream(Stream(1,2,3), Stream(2,3), Stream(3),
   * Stream()).
   *
   * @return
   */
  def tails: Stream[Stream[A]] =
    unfold(this) {
      case Empty => None
      case stream => Some((stream, stream.drop(1)))
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
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  /**
   * Exercise 5.8
   * Generalize ones slightly to the function constant, which returns an infinite Stream of
   * a given value.
   */
  def constant[A](a: A): Stream[A] = Stream.cons(a, constant(a))

  /**
   * Exercise 5.9
   * Write a function that generates an infinite stream of integers, starting from n, then
   * n + 1, n + 2, and so on.
   */
  def from(n: Int): Stream[Int] = Stream.cons(n, from(n + 1))

  def fibs(): Stream[Int] = fibs(0, 1)

  private def fibs(n: Int, m: Int): Stream[Int] = Stream.cons(n, fibs(m, n + m))

  /**
   * Exercise 5.11
   * Write a more general stream-building function called unfold. It takes an initial state,
   * and a function for producing both the next state and the next value in the generated
   * stream.
   *
   * @param z
   * @param f
   * @tparam A
   * @tparam S
   * @return
   */
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case None => Empty
    case Some((a, s)) => cons(a, unfold(s)(f))
  }

  /**
   * Exercise 5.12
   * Ones, constant, from and fibs via unFold
   *
   * @return
   */
  def onesUnFold(): Stream[Int] = unfold(1)(_ => Some(1, 1))

  def constantUnFold[A](a: A): Stream[A] = unfold(a)(_ => Some(a, a))

  def fromUnFold(n: Int): Stream[Int] = unfold(n)(a => Some(a, a + 1))

  def fibsUnFold: Stream[Int] = unfold((0, 1)) { case (x, y) => Some((x, (y, x + y))) }
}
