package part_1_introduction.e_purely_functional_state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import part_1_introduction.e_purely_functional_state.RNG.double

class FunctionalStateSpec extends AnyFlatSpec with should.Matchers {

  "Non negative random Int" should "Random number between 0 and Int.MaxValue" in {

    val (i,_) = RNG.Simple(4).nextInt
    i should be >= 0
    i should be < Int.MaxValue
  }

  "double" should "Random number between 0 and 1, not including 1" in {
    val (i, _) = double(RNG.Simple(4))
    i should be >= 0.0
    i should be < 1.0
  }

}
