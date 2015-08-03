package ex04

/**
 * Maybe Monad
 * @tparam A Covariant
 */
sealed trait MyOption[+A] {
  def get: A

  def isEmpty: Boolean

  def isDefined: Boolean

  def getOrElse[B >: A](elseValue: B): B =
    if (isEmpty) elseValue else get

  def orElse[B >: A](elseValue: => MyOption[B]): MyOption[B] =
    map(MySome(_)) getOrElse elseValue

  def fold[B](ifEmpty: => B)(f: A => B): B =
    if (isEmpty) ifEmpty else f(get)

  def foreach(f: A => Unit): Unit =
    if (isDefined) f(get)

  def forall(p: A => Boolean): Boolean =
    isEmpty || p(get)

  def exists(p: A => Boolean): Boolean =
    isDefined && p(get)

  def map[B](f: A => B): MyOption[B] =
    if (isEmpty) MyNone else MySome(f(get))

  def flatMap[B](f: A => MyOption[B]): MyOption[B] =
    if (isEmpty) MyNone else f(get)

  def filter(f: A => Boolean): MyOption[A] =
    flatMap(a => if (f(a)) MySome(a) else MyNone)
}

/**
 * Nothing
 */
case object MyNone extends MyOption[Nothing] {
  override def get = throw new NoSuchElementException("MyNone.get")

  override def isEmpty: Boolean = true

  override def isDefined: Boolean = false
}

/**
 * Just
 * @param x
 * @tparam A
 */
case class MySome[+A](x: A) extends MyOption[A] {
  override def get: A = this.x

  override def isEmpty: Boolean = false

  override def isDefined: Boolean = true
}

object MyOption {

  /**
   * factory
   */
  def apply[A](x: A): MyOption[A] = if (x == null) MyNone else MySome(x)

  def empty[A]: MyOption[A] = MyNone
}