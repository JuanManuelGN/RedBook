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

  /**
   * Exercise 3.26 Max of a tree
   *
   * @param tree
   * @return Int
   */
  def maximum(tree: Tree[Int]): Int = tree match {
    case Branch(l, d) => maximum(l).max(maximum(d))
    case Leaf(value) => value
  }

  /**
   * exercise 3.27 Maximum path length
   *
   * @param tree
   * @return
   */
  def depth(tree: Tree[Int]): Int = tree match {
    case Branch(l, d) => 1 + depth(l).max(depth(d))
    case Leaf(v) => 1
  }

  /**
   * Exercise 3.28
   *
   * @param tree
   * @param f
   * @tparam A
   * @tparam B
   * @return
   */
  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
    case Leaf(v) => Leaf(f(v))
    case Branch(l, d) => Branch(map(l)(f), map(d)(f))
  }

  def fold[A, B](tree: Tree[A])(f: A => B)(g: (B, B) => B): B =
    tree match {
      case Leaf(v) => f(v)
      case Branch(l, d) => g(fold(l)(f)(g), fold(d)(f)(g))
    }

  def sizeFold[A](tree: Tree[A]): Int =
    fold(tree)(_ => 1)(1 + _ + _)

  def maximumFold(tree: Tree[Int]): Int =
    fold(tree)(leaf => leaf)(_.max(_))

  def depthFold[A](tree: Tree[A]): Int =
    fold(tree)(_ => 1)(1 + _.max(_))
}

object Run extends App {
  val tree: Tree[Int] =
    Branch(
      Branch(
        Leaf(1),
        Branch(
          Leaf(-2),
          Leaf(3)
        )
      ),
      Branch(
        Branch(
          Leaf(6),
          Leaf(5)
        ),
        Leaf(5)
      )
    )

  val size = TreeFunctions.size(tree)
  println(
    size
  )

  val max = TreeFunctions.maximum(tree)
  println(
    max
  )

  val depth = TreeFunctions.depth(tree)
  println(
    s"depth = $depth"
  )

  val treeMapped = TreeFunctions.map(tree)(x => x + 1)
  println(
    treeMapped
  )

  val sizeF = TreeFunctions.sizeFold(tree)
  println(
    s"size using fold = $sizeF"
  )

  val maximumFold = TreeFunctions.maximumFold(tree)
  println(
    s"maximum using fold = $maximumFold"
  )

  val depthFold = TreeFunctions.depthFold(tree)
  println(
    s"depth using fold = $depthFold"
  )
}