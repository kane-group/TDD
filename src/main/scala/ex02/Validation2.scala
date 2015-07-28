package ex02

import ex02.Validation1.Error
import scalaz.Scalaz._
import scalaz.ValidationNel

/**
 * ses also
 * - http://eed3si9n.com/learning-scalaz/ja/Validation.html
 * - http://slides.pab-tech.net/either-and-validation/#18
 */
object Validation2 {

  def validateText(s: String): ValidationNel[Error, String] = {
    if (s == null) Error("is null").failureNel
    else if (s == "") Error("is empty").failureNel
    else s.successNel
  }

  def validateMin(s: String, n: Int): ValidationNel[Error, String] = {
    validateText(s).flatMap { v =>
      if (v.length > n) Error(s"$s is min $n").failureNel
      else v.successNel
    }
  }

  def validateMail(s: String): ValidationNel[Error, String] = {
    if (s.contains("@")) s.successNel
    else Error("invalid mail format").failureNel
  }

  def validatePassword(s: Password): ValidationNel[Error, Password] = {
    if (s.matches( """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]+$""")) s.successNel
    else Error("invalid password format").failureNel
  }

  def validateEqual[A](s1: A, s2: A): ValidationNel[Error, A] = {
    if (s1 == s2) s2.successNel
    else Error("not equal").failureNel
  }
}
