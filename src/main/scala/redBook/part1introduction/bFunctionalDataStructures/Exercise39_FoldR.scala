package redBook.part1introduction.bFunctionalDataStructures

/**
  * Compute the length of a list using foldRight.
  */
class FoldR {
  def length[A](as: List[A]): Int = List.foldRight(as,0)((a,acc) => acc + 1)
}

object FoldR extends App {
  val foldR : FoldR = new FoldR
  val ls = List.listInteger
  println(ls)
  println(
    foldR.length(ls)
  )
  println(
    foldR.length(Nil)
  )
}
