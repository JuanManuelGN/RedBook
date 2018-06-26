package redBook.part1introduction.bFunctionalDataStructures

/**
  * Write sum, product, and a function to compute the length of a list using foldLeft.
  */
class ListOp {
  def sum(ls: List[Int]) : Int = FoldL.foldLeft(ls,0)(_+_)
  def product(ls: List[Int]) : Int = FoldL.foldLeft(ls,1)(_*_)
  def length(ls: List[Int]) : Int = FoldL.foldLeft(ls,0)((h,acc) => acc+1)
}

object ListOp extends App {
  val listOp : ListOp = new ListOp
  val ls = List.listInteger
  println(ls)
  println(
    listOp.sum(ls)
  )
  println(
    listOp.product(ls)
  )
  println(
    listOp.length(ls)
  )
}
