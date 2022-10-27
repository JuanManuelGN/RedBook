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
    if (i < 0){
      (-(i  + 1),r)
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

  def doubleInt(rng: RNG): ((Double, Int), RNG) = ???

  def double3(rng: RNG): ((Double, Double, Double), RNG) = ???
}
