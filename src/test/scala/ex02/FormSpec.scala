package ex02

import org.scalatest.FunSpec

class FormSpec extends FunSpec {

  describe("Form") {

    describe("SignUp validate") {
      it("return a error when name is 21 characters") {
        val f = Form.SignUp("a" * 21, "", "", "")
        val r = Form.SignUp.validate(f)
        assert(r.isLeft)
      }

      it("return a error when mail is invalid format") {
        val f = Form.SignUp("a", "test.com", "", "")
        val r = Form.SignUp.validate(f)
        assert(r.isLeft)
      }

      it("return a error when password is invalid format") {
        val f = Form.SignUp("a", "test@com", "123", "")
        val r = Form.SignUp.validate(f)
        assert(r.isLeft)
      }

      it("return a error when confirmPassword not equal password") {
        val f = Form.SignUp("a", "test@com", "aB1", "dummy")
        val r = Form.SignUp.validate(f)
        assert(r.isLeft)
      }

      it("return a formObject when valid parameters") {
        val f = Form.SignUp("a", "test@com", "aB1", "aB1")
        val r = Form.SignUp.validate(f)
        assert(r.isRight)
      }
    }
  }
}
