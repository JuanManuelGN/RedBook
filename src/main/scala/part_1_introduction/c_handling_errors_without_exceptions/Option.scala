package part_1_introduction.c_handling_errors_without_exceptions

import scala.util.Try
import scala.{Either => _, Option => _}

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(a) => Some(f(a))
  }

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    a.flatMap(aa => b.map(bb => f(aa, bb)))

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case None => None
    case Some(a) => if (f(a)) this else None
  }

  /**
   * List(Option(2),None,Option(5)) = None
   * List(Option(1),Option(2),Option(3)) = Option(List(1,2,3))
   *
   * @param xs
   * @tparam A
   * @return
   */
  def sequence[A](xs: List[Option[A]]): Option[List[A]] =
    xs.foldRight[Option[List[A]]](Some(Nil))((a, b) => map2(a, b)(_ :: _))

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => Some(Nil)
    case x :: xs => map2(f(x), traverse(xs)(f))((optX, optY) => optX :: optY)
  }

  def traverseFR[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a.foldRight[Option[List[B]]](Some(Nil))((x, y) => map2(f(x), y)(_ :: _))
}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

object Option {

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty)
      None
    else
      Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] = {
    mean(xs).flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))
  }
}

object Variance extends App {
  val ls = Seq(1.0, 2.0, 3.0, 4.0)
  val result = Option.variance(ls)
  println(
    result
  )
}

object Map2 extends Option[String] with App {
  val optA = Some("hola")
  val optB = Some("Pepe")

  def f: (String, String) => String = (a, b) => a.+(b)

  val result = map2(optA, optB)(f)
  println(
    result
  )
}

object Sequence extends Option[Int] with App {
  val input = List(Some(1), Some(2), Some(3))
  val result = sequence(input)
  println(
    result
  )

  val inputError = List(Some(1), None, Some(3))
  val resultError = sequence(inputError)
  println(
    resultError
  )
}

object Traverse extends Option[String] with App {
  val input = List("1", "2", "3")
  val result =
    traverseFR[String, Int](input)(x => Try(x.toInt)
      .fold(
        _ => None,
        v => Some(v)
      )
    )
  println(
    result
  )

  val inputFail = List("1", "boom", "3")
  val resultFail =
    traverseFR[String, Int](inputFail)(x => Try(x.toInt)
      .fold(
        _ => None,
        v => Some(v)
      )
    )
  println(
    resultFail
  )

  val resultT =
    traverse[String, Int](input)(x => Try(x.toInt)
      .fold(
        _ => None,
        v => Some(v)
      )
    )
  println(
    resultT
  )

  val resultFailT =
    traverse[String, Int](inputFail)(x => Try(x.toInt)
      .fold(
        _ => None,
        v => Some(v)
      )
    )
  println(
    resultFailT
  )
}