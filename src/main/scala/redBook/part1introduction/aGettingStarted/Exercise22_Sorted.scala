package redBook.part1introduction.aGettingStarted

/**
  * Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:
  */
class Sorted {
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean) : Boolean = {
    as.length match {
      case 1 => true
      case 2 => ordered(as(0),as(1))
      case _ => ordered(as(0),as(1)) && isSorted(as.tail,ordered)
    }
  }
}

object Sorted extends App {
  val sort : Sorted = new Sorted
  val array : Array[Int] = new Array[Int](5)
  array(0) = 1
  array(1) = 2
  array(2) = 3
  array(3) = 4
  array(4) = 5
  println("Array [1,2,3,4,5] is sorted > " + sort.isSorted(array,(x:Int,y:Int) => x>y))
  println("Array [1,2,3,4,5] is sorted < " + sort.isSorted(array,(x:Int,y:Int) => x<y))
}
