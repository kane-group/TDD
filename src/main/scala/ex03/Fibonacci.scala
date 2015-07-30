package ex03

object Fibonacci {

  val fibStream: Stream[Int] = 0 #:: fibStream.scanLeft(1)(_ + _)

  def fib(n: Int): Int = {
    @scala.annotation.tailrec
    def loop(n: Int, acc1: Int, acc2: Int): Int = {
      if (n <= 0) acc1 else loop(n - 1, acc2, acc1 + acc2)
    }
    loop(n, 0, 1)
  }
}
