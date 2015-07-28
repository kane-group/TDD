package ex02

import ex02.Validation1.Error

import scalaz.Scalaz._
import scalaz.ValidationNel

object Form {

  /**
   * Form Class
   */
  final case class SignUp(name: String, mail: Mail, password: Password, passwordConfirm: Password)

  /**
   * Either
   */
  def validate1(f: SignUp): Either[Error, SignUp] = {
    import Validation1._
    for {
      _ <- min(f.name, 20).right
      _ <- mail(f.mail).right
      _ <- password(f.password).right
      _ <- equal(f.password, f.passwordConfirm).right
    } yield f
  }

  /**
   * scalaz.Validation
   */
  def validate2(name: String, mail: Mail, password: Password, passwordConfirm: Password): ValidationNel[Error, SignUp] = {
    import Validation2._
    (validateMin(name, 20) |@| validateMail(mail) |@| validatePassword(password) |@|
      validateEqual(password, passwordConfirm))(SignUp)
  }
}
