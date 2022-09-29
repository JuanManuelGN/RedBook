package part_1_introduction.c_handling_errors_without_exceptions

import scala.{Option => _, Either => _, _}

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