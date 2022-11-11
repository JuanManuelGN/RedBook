package part_1_introduction.e_purely_functional_state

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class StateSpec extends AnyFlatSpec with should.Matchers {

  private val state: State[List[Int], Int] =
    State {
      case Nil => (0, Nil)
      case head :: tail => (head + 1, tail)
    }

  "state map" should "  " in {
    val (a, s) = state.map(_ * 3).run(List(1, 2, 3, 4))

    a should be(6)
    s should be(List(2, 3, 4))
  }

  "state flatMap" should "  " in {
    val (a, s) = state.flatMap(aa => State.unit(aa * 3)).run(List(1, 2, 3, 4))

    a should be(6)
    s should be(List(2, 3, 4))
  }

  "sequence" should "" in {
    val stateLs = (1 to 5).map(_ => state).toList
    val response = State.sequence(stateLs).run(List(1,2,3,4,5))

    response._1 should be(List(1,2,3,4,5).map(_+1))
    response._2 should be(Nil)
  }
}
