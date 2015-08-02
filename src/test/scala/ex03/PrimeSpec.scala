package ex03

import org.scalatest.{FunSpec, ShouldMatchers}

class PrimeSpec extends FunSpec with ShouldMatchers {

  describe("Prime") {

    val expectedSeq = Seq(0, 2, 3, 5, 7, 11, 13, 17, 18)

    describe("ruler") {

      val ruler = Prime.ruler(7)

      it("return a prime ruler") {
        ruler should be(expectedSeq)
      }

      it("return a 2 combination size") {
        // 組みわせ 9C2 = 36
        val expect = (expectedSeq.size * (expectedSeq.size - 1)) / 2
        ruler.combinations(2).size should be(expect)
      }

      it("return a 1 to 18 set") {
        // 集合
        val actual = ruler.combinations(2).map(x => x(1) - x.head).toSet
        val expect = (1 to 18).toSet
        actual should be(expect)
      }
    }
  }
}
