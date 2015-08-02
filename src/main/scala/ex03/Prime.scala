package ex03

// http://bach.istc.kobe-u.ac.jp/lect/ProLang/org/scala-primeruler.html
object Prime {

  val isPrime = (n: Int) => (2 to math.sqrt(n).toInt).forall(n % _ != 0)

  @scala.annotation.tailrec
  def nextPrime(n: Int): Int = {
    if (isPrime(n + 1)) n + 1 else nextPrime(n + 1)
  }

  @scala.annotation.tailrec
  def primes(n: Int, acc: Seq[Int] = Seq(2)): Seq[Int] = {
    if (n == 1) acc.reverse else primes(n - 1, nextPrime(acc.head) +: acc)
  }

  def ruler(n: Int): Seq[Int] = {
    val p = primes(n)
    0 +: p :+ (p.last + 1)
  }
}
