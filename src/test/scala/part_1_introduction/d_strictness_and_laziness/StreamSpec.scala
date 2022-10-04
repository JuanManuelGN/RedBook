package part_1_introduction.d_strictness_and_laziness

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class StreamSpec extends AnyFlatSpec with should.Matchers {

  "Stream" should "transform to List" in {
    val stream: Stream[Int] =
      Stream.cons(1,
        Stream.cons(2,
          Stream.cons(3, Stream.empty)))

    val response = stream.toList
    val expected = List(1, 2, 3)

    response should be(expected)
  }

}
