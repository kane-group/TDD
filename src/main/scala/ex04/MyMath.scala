package ex04

object MyMath {

  /**
   * 合計値
   * @param ns
   * @return
   */
  def sum(ns: List[Int]): Int =
    ns.foldLeft(0)(_ + _)

  /**
   * 平均値
   * @param ns
   * @return
   */
  def avg(ns: List[Int]): Double =
    sum(ns).toDouble / ns.length

  /**
   * 絶対値
   * @param n
   * @return
   */
  def abs(n: Int): Int =
    if (n < 0) -n else n

  /**
   * 二乗した値
   * @param n
   * @return
   */
  def square(n: Int): Int =
    n * n

  /**
   * 平方根
   * @param n
   * @return
   */
  def sqrt(n: Double): Double = ???

  /**
   * 平均偏差
   * 平均からどのくらい離れているかの平均
   * @param ns
   * @return
   */
  def meanDeviation(ns: List[Int]): Double = {
    val avg = MyMath.avg(ns)
    ns.foldLeft(0.0)((b, a) => b + (avg - abs(a))) / ns.length
  }

  /**
   * 標準偏差
   * 平均からの偏差を二乗したものを足し合わせて平均を取ったものを「分散」
   * 標準偏差は分散のルート
   * 面積の平均値(分散)に対して、「辺の長さを取り出す操作」
   * @param ns
   * @return
   */
  def standardDeviation(ns: List[Int]): Double = {
    val avg = MyMath.avg(ns)
    val sum = ns.foldLeft(0.0)((b, a) => b + square(avg.toInt - abs(a)))
    sqrt(sum / ns.length)
  }
}
