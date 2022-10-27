package part_1_introduction.e_purely_functional_state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import part_1_introduction.e_purely_functional_state.RNG.{double, doubleInt, intDouble}

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

}
