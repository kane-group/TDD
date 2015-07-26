package ex01

import org.scalatest.{BeforeAndAfterEach, FlatSpec}

/**
 * http://azu.github.io/promises-book/#chapter3-promise-testing
 * 結局非同期のテスト(Promise/Future返す)
 * はtimer仕掛けて、指定秒数止めてからオブジェクト比較するしかなさそう
 * http://qiita.com/nomadmonad/items/d2c283f9b9ad33a2b32a
 */
class ResidentSpec extends FlatSpec with BeforeAndAfterEach {

  val income = 1000

  override def beforeEach(): Unit = {
  }

  override def afterEach(): Unit = {
  }

  "return a calcTax when jobType is civil service" should "income * 0.5" in {
    val obj = Resident("1", income, Option(CivilService))
    assert(obj.calcTax == 500)
  }

  "return a calcTax when jobType is engineer" should "income * 0.3" in {
    val obj = Resident("1", income, Option(Engineer))
    assert(obj.calcTax == 300)
  }

  "return a calcTax when jobType is deputy" should "income * 0.9" in {
    val obj = Resident("1", income, Option(Deputy))
    assert(obj.calcTax == 900)
  }

   "return a calcTax when jobType is null" should "0" in {
    val obj = Resident("1", income, Option(null))
    assert(obj.calcTax == 0.0)
  }
}
