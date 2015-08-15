package ex05

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

/**
 * http://qiita.com/reki2000/items/13f94745c6db80a586a8
 * http://docs.scala-lang.org/ja/overviews/core/futures.html
 * - Future を生成するとすぐに、implicit な ExecutionContext によってスレッドが生成され、実行開始される。
 * - 終了を待ちたいときは Await.result(mytask, timeout) を使う
 */
object Asynchronous {

  def main(args: Array[String]) {
    val f: Future[String] = Future { Thread.sleep(1000); "a" * 5 }

    println(f.isCompleted) // false

    // f.value.get match {}
    f onComplete {
      case Success(m) => println(m)
      case Failure(t) => println(t)
    }

    Await.ready(f, Duration.Inf)

    sample1()

    sample2().onComplete {
      case Success(m) => println(m)
      case Failure(t) => println(t)
    }
  }

  def sample1(): Unit = {
    val f = Future { 5 } // 時間のかかる処理と仮定
    val g = f.map(_ + 1) // Future[6]
    val h = f.flatMap(n => Future { n + 1 }) // Future[6]

    (for {
      x <- Future { 5 }
      y <- Future { x + 1 }
    } yield y + 2) foreach println
    // 5, x+1, y+2 がそれぞれ終了次第順次実行して 8 が表示される

    val f1 = Future { Thread.sleep(5000); 5 }
    val f2 = Future { Thread.sleep(5000); 3 }
    (for {
      x <- f1
      y <- f2
    } yield x + y) foreach println
    // 2つの sleep を並列実行して、5秒後に 8 が表示される
    // 順次実行の例のようにfor の中に Future の生成を書いてしまうと、
    // f1 が終わるまで f2 が始まらないので並列実行にならないことに注意

    // ゼロ除算でエラーになるので recover が走り 0 が表示される
    f.flatMap {
      n => Future { n / 0 }
    } recover {
      case x => 0
    }
  }

  def sample2(): Future[String] = {
    def heavyCalc(s: String, interval: Int): Unit = {
      (0 until 5).foreach { (i) =>
        Thread.sleep(interval)
        println(s"$s: $i")
      }
      println(s" ----- end $s ----- ")
    }

    val f1 = Future { heavyCalc("future1", 1000); "finish1" }
    val f2 = Future { heavyCalc("future2", 500); "finish2" }
    val f3 = Future { heavyCalc("future3", 800); "finish3" }
    val f4 = Future { heavyCalc("future4", 50); "finish4" }

    for {
      v1 <- f1
      v2 <- f2
      v3 <- f3
      v4 <- f4
    } yield { s"$v1 $v2 $v3 $v4" }
  }
}
