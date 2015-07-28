package ex02

object Validation1 {

  case class Error(message: String = "invalid")

  def notNull[A](a: A) = {
    if (a == null) Left(Error("is null")) else Right(a)
  }

  def text(s: String) = {
    if (s == null) Left(Error("is null"))
    else if (s == "") Left(Error("is empty"))
    else Right(s)
  }

  def min(s: String, n: Int) = {
    text(s).right.flatMap { v =>
      if (v.length > n) Left(Error(s"$s is min $n"))
      else Right(v)
    }
  }

  def mail(s: String) = {
    if (s.contains("@")) Right(s)
    else Left(Error("invalid mail format"))
  }

  def password(s: String) = {
    if (s.matches("""^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]+$""")) Right(s)
    else Left(Error("invalid password format"))
  }

  def equal[A](s1: A, s2: A) = {
    if (s1 == s2) Right(true)
    else Left(Error("not equal"))
  }
}
