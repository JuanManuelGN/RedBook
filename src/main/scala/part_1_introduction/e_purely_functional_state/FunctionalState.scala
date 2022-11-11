package part_1_introduction.e_purely_functional_state

trait RNG {
  def nextInt: (Int, RNG) // Should generate a random `Int`. We'll later define other functions in terms of `nextInt`.
}

object RNG {
  case class Simple(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
      val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
      val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
      (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
    }
  }

  /**
   * Exercise 6.1
   * Write a function that uses RNG.nextInt to generate a random integer between 0 and
   * Int.maxValue (inclusive). Make sure to handle the corner case when nextInt returns
   * Int.MinValue, which doesn’t have a non-negative counterpart.
   *
   * @param rng
   * @return
   */
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (i, r) = rng.nextInt
    if (i < 0) {
      (-(i + 1), r)
    } else {
      (i, r)
    }
  }

  /**
   * Exercise 6.2
   * Write a function to generate a Double between 0 and 1, not including 1. Note: You can
   * use Int.MaxValue to obtain the maximum positive integer value, and you can use
   * x.toDouble to convert an x: Int to a Double.
   *
   * @param rng
   * @return
   */
  def double(rng: RNG): (Double, RNG) = {
    val (i, r) = nonNegativeInt(rng)
    (i / (Int.MaxValue.toDouble + 1), r)
  }

  /**
   * Exercise 6.3
   * Write functions to generate an (Int, Double) pair, a (Double, Int) pair, and a
   * (Double, Double, Double) 3-tuple. You should be able to reuse the functions you’ve
   * already written.
   */

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val i = rng.nextInt._1
    val d = double(rng)._1
    ((i, d), rng)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val ((i, d), r) = intDouble(rng)
    ((d, i), r)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, r1) = double(rng)
    val (d2, r2) = double(r1)
    val (d3, r3) = double(r2)
    ((d1, d2, d3), r3)
  }

  /**
   * Exercise 6.4
   * Write a function to generate a list of random integers.
   *
   * @param count
   * @param rng
   * @return
   */
  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {

    if (count == 0)
      (List(), rng)
    else {
      val (i, r) = rng.nextInt
      val (xs, rr) = ints(count - 1)(r)
      (i :: xs, rr)
    }
  }

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  /**
   * Exercise 6.5
   * Use map to reimplement double in a more elegant way. See exercise 6.2.
   */
  val _double: Rand[Double] =
    map(nonNegativeInt)(i => i / (Int.MaxValue.toDouble + 1))

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
    rng => {
      val (a, rng1) = ra(rng)
      val (b, rng2) = rb(rng1)
      (f(a, b), rng2)
    }

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] =
    map2(ra, rb)((_, _))

  val randIntDouble: Rand[(Int, Double)] =
    both(int, double)

  val randDoubleInt: Rand[(Double, Int)] =
    both(double, int)

  /**
   * Exercise 6.7
   * If you can combine two RNG transitions, you should be able to combine a whole
   * list of them. Implement sequence for combining a List of transitions into a single
   * transition. Use it to reimplement the ints function you wrote before. For the latter,
   * you can use the standard library function List.fill(n)(x) to make a list with x
   * repeated n times.
   *
   * @param fs
   * @tparam A
   * @return
   */
  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =
    fs.foldRight(unit(Nil: List[A]))((c, acc) => map2(c, acc)(_ :: _))

  def nonNegativeLessThan(n: Int): Rand[Int] = { rng =>
    val (i, rng2) = nonNegativeInt(rng)
    val mod = i % n
    if (i + (n - 1) - mod >= 0)
      (mod, rng2)
    else nonNegativeLessThan(n)(rng)
  }

  /**
   * Exercise 6.8
   * Implement flatMap, and then use it to implement nonNegativeLessThan.
   * @param f
   * @param g
   * @tparam A
   * @tparam B
   * @return
   */
  def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
    rng => {
      val (a, rng2) = f(rng)
      g(a)(rng2)
    }

  def nonNegativeLessThanFlatMap(n: Int): Rand[Int] =
    flatMap(nonNegativeInt) {
      x =>{
        val mod = x % n
        if (x + (n-1) - mod >= 0)
          unit(mod)
        else
          nonNegativeLessThanFlatMap(n)
      }
    }

  def mapFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] =
    flatMap(s) {
      x => unit(f(x))
    }

  def map2FlatMap[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
    flatMap(ra) {
      a => map(rb)(b => f(a,b))
    }
}
