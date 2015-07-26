package ex01

sealed abstract class JobType(val taxRate: Double)
case object CivilService extends JobType(0.5)
case object Engineer extends JobType(0.3)
case object Deputy extends JobType(0.9)

case class Resident(name: String, income: Double, jobType: Option[JobType]) {
  val calcTax = jobType.map(income * _.taxRate).getOrElse(0.0)
}

object TaxService {
  def getResidentTax(name: String)(implicit repo: Repository[Resident]): Double = {
    repo.findBy(name).flatMap { resident =>
      resident.jobType.map(resident.income * _.taxRate)
    }.getOrElse(0.0)
  }
}

trait Repository[A] {
  def findBy(x: Any): Option[A]
}

