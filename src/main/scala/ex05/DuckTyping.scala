package ex05

/**
 * StructuralType
 * メソッド間で渡すものが「何」じゃなくて、「何ができる」を重視
 * http://adtech.cyberagent.io/scalablog/2015/03/30/rubyist%E3%81%8Cscala%E3%82%92%E5%8B%89%E5%BC%B7%E3%81%97%E3%81%9F%E8%A9%B1%EF%BC%88%E7%B6%9A%E3%81%8D%EF%BC%89/
 */
object DuckTyping {

  def main(args: Array[String]) {
    sayDuck(Duck)
    sayDuck(RobotDuck)
    sayDuck(SkyDuck)
//    sayDuck(NoneDuck) // compile err

    import IntExtensions._
    val one = 1
    one.double
  }

  def sayDuck(a: { def say(s: String): String }): Unit = {
    println(a.say("Ga-"))
  }

  object Duck {
    def say(s: String) = s.toUpperCase
  }

  object RobotDuck {
    def say(s: String) = s.toLowerCase
  }

  object SkyDuck {
    def say(s: String) = s"$s"
  }

  object NoneDuck {}
}

// Extensions
// Monkey Patch
object IntExtensions {
  implicit def doubleInt(n: Int): Object {def double: Int} = new {
    def double = n * n
  }
}
