package ex05

/**
 * Pub/Sub
 */
object Observer {

  /**
   * 出版社
   */
  trait Publisher {
    private var subscribers: Set[Subscriber] = Set()

    def size: Int = subscribers.size

    def subscribe(subscriber: Subscriber): Unit =
      subscribers += subscriber

    def unsubscribe(subscriber: Subscriber): Unit =
      subscribers -= subscriber

    def publish(): Unit =
      subscribers foreach (_.handler(this))
  }

  /**
   * 購読者
   */
  trait Subscriber {
    def handler(p: Publisher)
  }

}
