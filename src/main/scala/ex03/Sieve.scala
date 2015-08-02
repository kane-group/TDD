package ex03

// http://bach.istc.kobe-u.ac.jp/lect/ProLang/org/scala-sieve.html
object Sieve {

//  @scala.annotation.tailrec
  def sieve(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) {
      Nil
    } else {
      xs.head :: sieve(xs.tail.filter(_ % xs.head != 0))
    }
  }

  val prime = (n: Int) => sieve((2 to n).toList)
//  @scala.annotation.tailrec

  /**
   * Lazy
   */
  def sieve(xs: Stream[Int]): Stream[Int] = {
    xs.head #:: sieve(xs.tail.filter(_ % xs.head != 0))
  }

  val primeStream = sieve(from(2))

  private def from(start: Int): Stream[Int] = Stream.cons(start, from(start + 1))
}
