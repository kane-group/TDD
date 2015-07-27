package ex02

object Form {
  type Password = String

  final case class SignUp(name: String, mail: String, password: Password, passwordConfirm: Password)

  object SignUp {
    def validate(f: SignUp): Either[Validation.Error, SignUp] = {
      for {
        _ <- Validation.min(f.name, 20).right
        _ <- Validation.mail(f.mail).right
        _ <- Validation.password(f.password).right
        _ <- Validation.equal(f.password, f.passwordConfirm).right
      } yield f
    }
  }
}
