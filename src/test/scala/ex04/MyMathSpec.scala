package ex04

import org.scalatest.{FunSpec, Matchers}

class MyMathSpec extends FunSpec with Matchers {

  describe("MyMath#sum") {
    it("should return a value list sum") {
      val xs = List(96, 63, 85, 66, 91, 89, 77) // 567
      MyMath.sum(xs) shouldBe 567
    }
  }

  describe("MyMath#avg") {
    it("should return a value list avg") {
      val xs = (1 to 10).toList // 55 / 10
      MyMath.avg(xs) shouldBe 5.5

      val xs2 = List(96, 63, 85, 66, 91, 89, 77) // 567
      MyMath.avg(xs2) shouldBe 81
    }
  }

  describe("MyMath#abs") {
    it("should return a value abs") {
      MyMath.abs(-3) shouldBe 3
      MyMath.abs(3) shouldBe 3
    }
  }

  describe("MyMath#square") {
    it("should return a value square") {
      MyMath.square(-3) shouldBe 9
      MyMath.square(3) shouldBe 9
    }
  }

  describe("MyMath#sqrt") {
    it("should return a value sqrt") {}
  }

  describe("MyMath#meanDeviation") {
    it("should return a value meanDeviation") {}
  }

  describe("MyMath#standardDeviation") {
    it("should return a value standardDeviation") {}
  }
}
