package part_1_introduction.b_functional_data_structures.trees

trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](l: Tree[A], d: Tree[A]) extends Tree[A]


object TreeFunctions {

  /**
   * Exercise 3.25 Number of nodes of a tree
   *
   * @param tree
   * @tparam A
   * @return Int
   */
  def size[A](tree: Tree[A]): Int =
    tree match {
      case Leaf(_) => 1
      case Branch(l, d) => 1 + size(l) + size(d)
    }
}

object Run extends App {
  val tree: Tree[Int] =
    Branch(
      Branch(
        Leaf(1),
        Branch(
          Leaf(2),
          Leaf(3)
        )
      ),
      Branch(
        Branch(
          Leaf(4),
          Leaf(5)
        ),
        Leaf(6)
      )
    )

  val size = TreeFunctions.size(tree)
  println(
    size
  )
}