package part_1_introduction.c_handling_errors_without_exceptions

import scala.{Either => _, Option => _}

sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Left(e) => Left(e)
    case Right(v) => Right(f(v))
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Left(e) => Left(e)
    case Right(v) => f(v)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(_) => b
    case Right(v) => this
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    this.flatMap(v => b.map(bv => f(v, bv)))
}

case class Left[+E](value: E) extends Either[E, Nothing]

case class Right[+A](value: A) extends Either[Nothing, A]

object EitherT extends App {
  val eitherOk: Either[Exception, Int] = Right(5)
  val eitherKo: Either[Exception, Int] = Left(new Exception("error"))

  val mapOk = eitherOk.map(_ * 4)
  val mapKo = eitherKo.map(_ * 4)

  println(
    mapOk
  )
  println(
    mapKo
  )

  val eitherFlatMapKo = eitherKo.flatMap(x => Right(x.toString))
  val eitherFlatMapOk = eitherOk.flatMap(x => Right(x.toString))

  println(
    eitherFlatMapOk
  )
  println(
    eitherFlatMapKo
  )

  val eitherOrElseOk = eitherOk.orElse(Right(100))
  val eitherOrElseKo = eitherKo.orElse(Right(100))

  println(
    eitherOrElseOk
  )
  println(
    eitherOrElseKo
  )

  val map2Ok = eitherOk.map2(Right(4))(_ * _)
  val map2Ko = eitherKo.map2(Right(4))(_ * _)
  val map2KoOtherReason = eitherOk.map2(eitherKo)(_ * _)
  println(
    map2Ok
  )
  println(
    map2Ko
  )
  println(
    map2KoOtherReason
  )
}
