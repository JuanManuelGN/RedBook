package part_1_introduction.e_purely_functional_state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import part_1_introduction.e_purely_functional_state.RNG.{Rand, Simple, _double, both, double, doubleInt, intDouble, ints, nonNegativeInt, nonNegativeLessThan, nonNegativeLessThanFlatMap, sequence}

class FunctionalStateSpec extends AnyFlatSpec with should.Matchers {

  val s = RNG.Simple(4)

  "Non negative random Int" should "Random number between 0 and Int.MaxValue" in {

    val (i,_) = s.nextInt
    i should be >= 0
    i should be < Int.MaxValue
  }

  "double" should "Random number between 0 and 1, not including 1" in {
    val (i, _) = double(s)
    i should be >= 0.0
    i should be < 1.0
  }

  "_double" should "Random number between 0 and 1, not including 1" in {
    val (i, _) = _double(s)
    i should be >= 0.0
    i should be < 1.0
  }

  "intDouble" should "couple of random, one Int and another Double" in {
    val ((i, d),_) = intDouble(s)
    i should be(1538995)
    d should be(7.166503928601742E-4)
  }

  "doubleInt" should "couple of random, one Int and another Double" in {
    val ((d, i), _) = doubleInt(s)
    i should be(1538995)
    d should be(7.166503928601742E-4)
  }

  "ints" should "list of ints randoms" in {
    val (l, r) = ints(4)(s)
    l.size should be(4)
    l.head should be(1538995)
    l.tail.head should not be(1538995)
  }

  "both" should "two randoms" in {
    val r1: Rand[Int] = nonNegativeInt
    val r2: Rand[Int] = nonNegativeInt
    val r = both[Int, Int](r1,r2)
    r(s)._1 should be((1538995,322738769))
  }

  "sequence" should "two randoms" in {
    val rands: List[Rand[Int]] = List.fill(5)(nonNegativeInt)
    val response: Rand[List[Int]] = sequence(rands)

    val ints = response.apply(s)._1
    ints.size should be(5)
    ints.distinct.size should be(5)
  }

  "Non negative using flatMap" should "Int > 0" in {
    val nnn: Rand[Int] = nonNegativeLessThan(100)
    val nnnfm: Rand[Int] = nonNegativeLessThanFlatMap(100)

    println(
      s"Non Negatives ${nnn(s)} ${nnnfm(s)}"
    )
    nnn(s) should be(nnnfm(s))
  }

}
