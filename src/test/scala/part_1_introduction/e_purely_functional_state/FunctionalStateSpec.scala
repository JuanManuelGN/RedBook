package part_1_introduction.e_purely_functional_state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class FunctionalStateSpec extends AnyFlatSpec with should.Matchers {

  "Non negative random Int" should "Random number between 0 and Int.MaxValue" in {

    val (i,_) = RNG.Simple(4).nextInt
    i should be >= 0
    i should be < Int.MaxValue
  }

}
