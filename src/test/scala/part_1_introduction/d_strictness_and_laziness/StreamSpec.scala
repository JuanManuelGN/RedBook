package part_1_introduction.d_strictness_and_laziness

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class StreamSpec extends AnyFlatSpec with should.Matchers {

  val stream: Stream[Int] =
    Stream.cons(1,
      Stream.cons(2,
        Stream.cons(3, Stream.empty)))

  "Stream" should "transform to List" in {
    val response = stream.toList
    val expected = List(1, 2, 3)

    response should be(expected)
  }

  "Take 2" should "return a stream with 5 elements if the original stream has 2 or more elements" in {
    val response: Stream[Int] = stream.take(2)
    val expected: Stream[Int] =
      Stream.cons(1,
          Stream.cons(2, Stream.empty))

    response.toList should be(expected.toList)
  }

  "Take 5" should "return a stream with 3 because the original stream has 3" in {
    val response: Stream[Int] = stream.take(5)
    val expected: Stream[Int] = stream

    response.toList should be(expected.toList)
  }

  "Drop 3" should "delete the whole stream" in {
    val response = stream.drop(3)
    val expected = Stream.empty

    response.toList should be(expected.toList)
  }

  "Drop 2" should "return a stream with one element" in {
    val response = stream.drop(2)
    val expected = Stream.cons(3, Stream.empty)

    response.toList should be(expected.toList)
  }

  "Take while elements are odd" should "Stream with element 1" in {
    val response = stream.takeWhile(_ % 2 == 1)
    val expected = Stream.cons(1, Stream.empty)

    response.toList should be(expected.toList)
  }

}
