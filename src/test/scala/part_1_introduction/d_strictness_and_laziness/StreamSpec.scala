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

  "Take while elements are odd using FR" should "Stream with element 1" in {
    val response = stream.takeWhileFR(_ % 2 == 1)
    val expected = Stream.cons(1, Stream.empty)

    response.toList should be(expected.toList)
  }

  "head of stream in a not empty stream" should "Option with element 1" in {
    val response = stream.headOption
    val expected = Some(1)

    response should be(expected)
  }

  "head of stream in an empty stream" should "None" in {
    val response = Stream.empty.headOption
    val expected = None

    response should be(expected)
  }

  "head of stream in a not empty stream using FR" should "Option with element 1" in {
    val response = stream.headOptionFR
    val expected = Some(1)

    response should be(expected)
  }

  "head of stream in an empty stream using FR" should "None" in {
    val response = Stream.empty.headOptionFR
    val expected = None

    response should be(expected)
  }

  "map * 10" should "Stream with each element is _*10" in {
    val response = stream.mapFR(_ * 10)
    val expected =
      Stream.cons(10,
        Stream.cons(20,
          Stream.cons(30, Stream.empty)))

    response.toList should be(expected.toList)
  }

  "filter odd components" should "Stream with pair elements" in {
    val response = stream.filterFR(_ % 2 == 1)
    val expected =
      Stream.cons(1,
        Stream.cons(3, Stream.empty))

    response.toList should be(expected.toList)
  }

  "append two stream" should "two stream joined" in {
    val streamToAppend: Stream[Int] =
      Stream.cons(6,
        Stream.cons(7,
          Stream.cons(8, Stream.empty)))
    val response = stream.append(streamToAppend)
    val expected =
      Stream.cons(1,
        Stream.cons(2,
          Stream.cons(3,
            Stream.cons(6,
              Stream.cons(7,
                Stream.cons(8, Stream.empty))))))

    response.toList should be(expected.toList)
  }

  "Create an infinity stream and take 2 elements" should "Stream with 2 elements" in {
    val response = Stream.constant(1).take(2)
    val expected =
      Stream.cons(1,
        Stream.cons(1, Stream.empty))

    response.toList should be(expected.toList)
  }

  "Create an infinity stream from a given number and take 3 elements" should "Stream with 3 elements, these elements must be consecutive" in {
    val response = Stream.from(5).take(3)
    val expected =
      Stream.cons(5,
        Stream.cons(6,
          Stream.cons(7, Stream.empty)))

    response.toList should be(expected.toList)
  }

  "Fibonacci" should "Stream with 5 fibonacci elements" in {
    val response = Stream.fibs.take(5)
    val expected =
      Stream.cons(0,
        Stream.cons(1,
          Stream.cons(1,
            Stream.cons(2,
              Stream.cons(3, Stream.empty)))))

    response.toList should be(expected.toList)
  }

  "Ones is a streams that only contains 1" should "Stream with infinity 1" in {
    val response = Stream.onesUnFold().take(3)
    val expected =
      Stream.cons(1,
        Stream.cons(1,
          Stream.cons(1, Stream.empty)))

    response.toList should be(expected.toList)
  }

  "constant" should "Stream with infinity hola" in {
    val response = Stream.constantUnFold("hola").take(3)
    val expected =
      Stream.cons("hola",
        Stream.cons("hola",
          Stream.cons("hola", Stream.empty)))

    response.toList should be(expected.toList)
  }

  "Create an infinity stream from a given number and take 3 elements usin fromUnFold" should "Stream with 3 elements, these elements must be consecutive" in {
    val response = Stream.fromUnFold(5).take(3)
    val expected =
      Stream.cons(5,
        Stream.cons(6,
          Stream.cons(7, Stream.empty)))

    response.toList should be(expected.toList)
  }

  "Fibonacci using fibsUnFold" should "Stream with 5 fibonacci elements" in {
    val response = Stream.fibsUnFold.take(5)
    val expected =
      Stream.cons(0,
        Stream.cons(1,
          Stream.cons(1,
            Stream.cons(2,
              Stream.cons(3, Stream.empty)))))

    response.toList should be(expected.toList)
  }

  "map * 10 via unFold" should "Stream with each element is _*10" in {
    val response = stream.mapUnFold(_ * 10)
    val expected =
      Stream.cons(10,
        Stream.cons(20,
          Stream.cons(30, Stream.empty)))

    response.toList should be(expected.toList)
  }

  "Take 2 using unFold" should "return a stream with 5 elements if the original stream has 2 or more elements" in {
    val response: Stream[Int] = stream.takeUnFold(2)
    val expected: Stream[Int] =
      Stream.cons(1,
        Stream.cons(2, Stream.empty))

    response.toList should be(expected.toList)
  }

  "Take while using unFold elements are odd using FR" should "Stream with element 1" in {
    val response = stream.takeWhileUnFold(_ % 2 == 1)
    val expected = Stream.cons(1, Stream.empty)

    response.toList should be(expected.toList)
  }

  "ZipWith using unFold" should "Stream with element 3, each element is the sum of both" in {
    val stream2: Stream[Int] =
      Stream.cons(2,
        Stream.cons(3, Stream.empty))

    val response = stream.zipWith(stream2)(_ + _)
    val expected =
      Stream.cons(3,
        Stream.cons(5, Stream.empty))

    response.toList should be(expected.toList)
  }

  "ZipAll using unFold" should "Stream with element 3, each element is the sum of both" in {
    val stream2: Stream[Int] =
      Stream.cons(2,
        Stream.cons(3, Stream.empty))

    val response = stream.zipAll(stream2)
    val expected =
      Stream.cons((Some(1), Some(2)),
        Stream.cons((Some(2), Some(3)),
          Stream.cons((Some(3), None), Stream.empty)))

    response.toList should be(expected.toList)
  }

  "tails" should "transform stream to stream of stream" in {
    val response = stream.tails
    val expected =
      Stream.cons(
        Stream.cons(1,
          Stream.cons(2,
            Stream.cons(3, Stream.empty))),
        Stream.cons(
          Stream.cons(2,
            Stream.cons(3, Stream.empty)),
          Stream.cons(
            Stream.cons(3, Stream.empty),
            Stream.empty))
      )

    println(response.mapFR(_.toList).toList)
    println(expected.mapFR(_.toList).toList)

    response.mapFR(_.toList).toList should be(expected.mapFR(_.toList).toList)
  }
}
