package redBook.part1introduction.bFunctionalDataStructures

trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  // Exercise 3.2
  def tail[A](xs : List[A]) : List[A] = {
    xs match {
      case Cons(_,tail) => tail
      case _ => Nil
    }
  }

  // Exercise 3.3
  def setHead[A](x : A, xs : List[A]) : List[A] = {
    xs match {
      case Cons(a,tail) => Cons(x,tail)
      case Cons(a,Nil) => Cons(x,Nil)
      case _ => Nil
    }
  }

  // Exercise 3.4
  def drop[A](ls: List[A], n: Int): List[A] = {
    if (n<=0)
      ls
    else
      ls match {
        case Cons(_,tail) => drop(tail,n-1)
        case Nil => Nil
      }
  }

  // Exercise 3.5
  def dropWhile[A](ls: List[A], f: A => Boolean): List[A] = {
    ls match {
      case Cons(a,tail) =>
        if(f(a))
          dropWhile(tail,f)
        else
          Cons(a,dropWhile(tail,f))
      case Nil => Nil
    }
  }

  // Exercise 3.6
  def init[A](ls: List[A]): List[A] = {
    ls match {
      case Cons(_,Nil) => Nil
      case Nil => Nil
      case Cons(a,tail) => Cons(a,init(tail))
    }
  }

  // Exercise 3.9
  def length[A](as: List[A]): Int = List.foldRight(as,0)((a,acc) => acc + 1)

  // Exercise 3.10
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(h,tail) => foldLeft(tail,f(z,h))(f)
  }

  // Exercise 3.11
  def sumfl(ls: List[Int]) : Int = foldLeft(ls,0)(_+_)
  def productfl(ls: List[Int]) : Int = foldLeft(ls,1)(_*_)
  def lengthfl(ls: List[Int]) : Int = foldLeft(ls,0)((h,acc) => acc+1)

  // Exercise 3.12
  def reverse[A](ls: List[A]) : List[A] = foldLeft(ls,List[A]())((acc,h) => Cons(h,acc))

  // Exercise 3.13
  def foldRightViaFoldLeft[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(reverse(as),z)((b,a) => f(a,b))

  def appendfrl[A](xs: List[A], ys: List[A]): List[A] = foldRightViaFoldLeft(xs,ys)(Cons(_,_))

  // Exercise 3.14
  def appendfl[A](xs: List[A], ys: List[A]): List[A] = foldLeft(reverse(xs),ys)((acc,h) => Cons(h,acc))

  def appendfr[A](xs: List[A], ys: List[A]): List[A] = foldRight(xs,ys)(Cons(_,_))

  // Exercise 3.15
  def concatListOfListAndFlat[A](ls: List[List[A]]) = foldRight(ls,List[A]())(appendfr)

  // Exercise 3.16
  def add1(ls: List[Int]): List[Int] = {
    ls match {
      case Nil => Nil
      case Cons(h,tail) => Cons(1+h,add1(tail))
    }
  }

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  val listInteger = List(1,2,3,4,5)
  val listIntegerOneElement = List(67)
  val listIntegerTwoElement = List(99,43)
  val listString = List("a","b","c","d")
  val listOfListInteger = List(List(1,2,3,4,5),List(6,7,8,9,10),List(11,12,13,14,15))
}
