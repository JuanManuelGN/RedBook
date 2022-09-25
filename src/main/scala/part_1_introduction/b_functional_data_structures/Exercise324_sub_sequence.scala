package part_1_introduction.b_functional_data_structures

/**
 * Use flatMap to implement filter
 */
object Exercise324_sub_sequence extends App {

  // Oficial repo implementation
  @annotation.tailrec
  def startsWith[A](l: List[A], prefix: List[A]): Boolean = (l, prefix) match {
    case (_, Nil) => true
    case (Cons(h, t), Cons(h2, t2)) if h == h2 => startsWith(t, t2)
    case _ => false
  }

  @annotation.tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Nil => sub == Nil
    case _ if startsWith(sup, sub) => true
    case Cons(h, t) => hasSubsequence(t, sub)
  }

  println(
    hasSubsequence(List(1, 2, 3, 4), List(2, 3))
  )


}
