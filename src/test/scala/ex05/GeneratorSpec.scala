package ex05

import org.scalatest.{FunSpec, Matchers}

class GeneratorSpec extends FunSpec with Matchers {

  // TODO
  describe("MonadLaw") {

    val m = Generator.integers
    val x = 1
    val f: (Int) => Generator[Int] = { (n: Int) => Generator.integers }
    val g: (Int) => Generator[String] = { (n: Int) =>
      new Generator[String] {
        override def generate: String = if (n > 0) "+" else "-"
      }
    }

    it("associativity") {
      // m flatMap f flatMap g == m flatMap (x => f(x) flatMap g)
      m.flatMap(f).flatMap(g) == m.flatMap(f(_).flatMap(g))
    }

    it("left unit") {
      // unit(x) flatMap f == f(x)
      m.single(x).flatMap(f) == f(x)
    }

    it("right unit") {
      // m flatMap unit == m
      m.flatMap(m.single) == m
    }
  }
}
