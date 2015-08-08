package ex05

import ex05.Observer.Publisher
import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers}

class ObserverSpec extends FunSpec with Matchers with BeforeAndAfterEach {

  val pub = TestPublisher
  val sub1 = new TestSubscriber
  val sub2 = new TestSubscriber

  override protected def beforeEach(): Unit = {
    super.beforeEach()
  }

  override protected def afterEach(): Unit = {
    super.afterEach()
    pub.unsubscribe(sub1)
    pub.unsubscribe(sub2)
  }

  describe("Publisher#subscribe") {
    it("should be 2") {
      pub.size shouldBe 0

      pub.subscribe(sub1)
      pub.subscribe(sub2)

      pub.size shouldBe 2
    }
  }

  describe("Publisher#unsubscribe") {
    it("should be 0") {
      pub.subscribe(sub1)
      pub.subscribe(sub2)

      pub.size shouldBe 2

      pub.unsubscribe(sub1)
      pub.unsubscribe(sub2)

      pub.size shouldBe 0
    }
  }

  describe("Publisher#publish") {
    it("should be subscriber called true") {
      sub1.called shouldBe false

      pub.subscribe(sub1)
      pub.publish()

      sub1.called shouldBe true
    }
  }
}

object TestPublisher extends Observer.Publisher

class TestSubscriber(var called: Boolean = false) extends Observer.Subscriber {
  def handler(p: Publisher): Unit = called = true
}
