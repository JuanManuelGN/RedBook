package part_2_functional_design_and_combinator_libraries.a_purely_funcional_parallelism

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.util.concurrent.ExecutorService

class ParSpec extends AnyFlatSpec with should.Matchers {

  "Filter list" should "return a par filtered list" in {
    val ls: List[Int] = (1 to 10).toList
    def predicate: Int => Boolean = _ % 2 == 1
    val response = Par.parFilter(ls)(predicate)
    val expected = Par.unit(List(1,3,5,7,9))
    val es = java.util.concurrent.Executors.newSingleThreadExecutor()
    response(es) should be(expected(es))
  }
}
