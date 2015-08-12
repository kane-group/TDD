package ex05

/**
 * 合成関数 (g∘f)(x) = g(f(x)) は、
 * まず x を f に適用して、その結果を g に適用
 */
object Composition {

  val f = (i: Int) => i.toString
  val g = (s: String) => s + s + s
  val h = g compose f

  def main(args: Array[String]) {
    println(h(1)) // 111

    val f1 = (a: Int, b: Int, c: Int) => a + b + c
    val curr = f1.curried
    println(curr(1)(2)(3)) // 6

    val partial = curr(1)(2)(_)
    println(partial(4)) // 7
  }
}
