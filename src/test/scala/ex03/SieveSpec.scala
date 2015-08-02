package ex03

import ex03.Sieve._
import org.scalatest.{FunSpec, ShouldMatchers}

class SieveSpec extends FunSpec with ShouldMatchers {

  describe("Sieve") {

    val expectedList = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)

    describe("prime") {
      it("return a prime list") {
        val actual = prime(30)
        actual should be(expectedList)
      }
    }

    describe("prime - lazy") {
      it("return a prime list") {
        val actual = primeStream.take(10).toList
        actual should be(expectedList)
      }

      it("return a first prime") {
        primeStream.head should be(2)
      }

      it("return a 305 prime") {
        primeStream(304) should be(2011)
      }

      it("return a first over 2000 prime") {
        val actual = primeStream.filter(_ >= 2000).head
        actual should be(2003)
      }

      it("return a first less than 2000 prime size") {
        val actual = primeStream.takeWhile(_ <= 2000).size
        actual should be(303)
      }
    }
  }
}
