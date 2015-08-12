package ex05

import scala.util.Try

// https://gist.github.com/j5ik2o/5471851
// https://monolog.linkode.co.jp/articles/kotoh/Scala%E3%81%A7%E4%BE%8B%E5%A4%96%E5%87%A6%E7%90%86
object Exception {

  def main(args: Array[String]) {
    showNameLength(getNameLength("")) // length = zero length
    showNameLength(getNameLength("xbc")) // length = prefix invalid
    showNameLength(getNameLength("abc")) // length = length = 3
  }

  def showNameLength(l: Try[Int]) {
    val msg = l.map(e => s"length = $e").recover {
      case ex: ZeroLengthException => "zero length"
      case ex: PrefixInvalidException => "prefix invalid"
    }.get
    println(s"length = $msg")
  }

  def getNameLength(s: String): Try[Int] = Try {
    s match {
      case _ if s.length == 0 => throw ZeroLengthException(s)
      case _ if !s.startsWith("a") => throw PrefixInvalidException(s)
      case _ => s.length
    }
  }
}

case class ZeroLengthException(name: String) extends Exception(s"$name is zero length")
case class PrefixInvalidException(name: String) extends Exception(s"$name's prefix is invalid")