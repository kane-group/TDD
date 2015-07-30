package ex03

import org.scalatest.{ShouldMatchers, FunSpec}
import Fibonacci._
class FibonacciSpec extends FunSpec with ShouldMatchers {

  describe("Fibonacci") {

    val expectedList = List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)

    describe("fib - tailrec") {
      for ((v, i) <- expectedList.zipWithIndex) {
        it(s"$i: $v") {
          fib(i) should be (v)
        }
      }
    }

    describe("fibStream") {
      it("return a fibonacci list") {
        val actual = fibStream.take(expectedList.size).toList
        actual should be (expectedList)
      }
    }
  }
}
