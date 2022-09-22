package part_1_introduction.b_functional_data_structures

/**
  * Implement append in terms of either foldLeft or foldRight.
  */
object Exercise314_Append extends App {
  val ls = List.listInteger
  val rs = List.listIntegerOneElement
  println(ls)
  /*
  Execution of foldRight
  List.appendfr(ls,lr) =
  List.appendfr(List(1,2,3,4,5),List(67)) =
  List.foldRight(List(1,2,3,4,5),List(67))(Cons(_,_)) =
  1::List.foldRight(List(1,2,3,4),List(67))(Cons(_,_)) =
  1::2::List.foldRight(List(1,2,3),List(67))(Cons(_,_)) =
  1::2::3::List.foldRight(List(1,2),List(67))(Cons(_,_)) =
  1::2::3::4::List.foldRight(List(1),List(67))(Cons(_,_)) =
  1::2::3::4::5::List.foldRight(List(),List(67))(Cons(_,_)) =
  1::2::3::4::5::67
   */
  println(
    List.appendfr(ls,rs)
  )
  /*
  Execution of foldLeft
  List.appendfl(ls,lr) =
  List.appendfl(List(1,2,3,4,5),List(67)) =
  List.foldLeft(reverse(List(1,2,3,4,5)),List(67))((acc,h) => Cons(h,acc)) =
  List.foldLeft(List(5,4,3,2,1),List(67))((acc,h) => Cons(h,acc)) =
  1::List.foldLeft(List(5,4,3,2),List(67))((acc,h) => Cons(h,acc)) =
  1::2::List.foldLeft(List(5,4,3),List(67))((acc,h) => Cons(h,acc)) =
  1::2::3::List.foldLeft(List(5,4),List(67))((acc,h) => Cons(h,acc)) =
  1::2::3::4::List.foldLeft(List(5),List(67))((acc,h) => Cons(h,acc)) =
  1::2::3::4::5::List.foldLeft(List(),List(67))((acc,h) => Cons(h,acc)) =
  1::2::3::4::5::67
   */
  println(
    List.appendfl(ls,rs)
  )
}
