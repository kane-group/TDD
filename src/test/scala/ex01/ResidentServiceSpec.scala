package ex01

import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

/**
 * study - mock
 * http://scalamock.org/quick-start/
 */
class ResidentServiceSpec extends FlatSpec with MockFactory {

  val income = 100

  "return a TaxService.getResidentTax when name is 'dummy'" should "50" in {
    val repo = stub[Repository[Resident]]
    (repo.findBy _).when("dummy").returns(
      Option(Resident("dummy", income, Option(CivilService)))
    )

    assert(TaxService.getResidentTax("dummy")(repo) == 50)
  }
}
