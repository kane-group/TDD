package ex05

/**
 * Generator Monad
 * @tparam T
 */
trait Generator[+T] { self =>
  def generate: T

  def single[T](x: T): Generator[T] = new Generator[T] { def generate: T = x }

  def map[U](f: T => U): Generator[U] = new Generator[U] {
    def generate: U = f(self.generate)
  }

  def flatMap[U](f: T => Generator[U]): Generator[U] = new Generator[U] {
    def generate: U = f(self.generate).generate
  }
}

object Generator {
  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate: Int = rand.nextInt()
  }

  val pairs = new Generator[(Int, Int)] {
    override def generate: (Int, Int) = (integers.generate, integers.generate)
  }

  val booleans = new Generator[Boolean] {
    def generate: Boolean = integers.generate > 0
  }
}