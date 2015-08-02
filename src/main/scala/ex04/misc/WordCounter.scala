package ex04.misc

// https://github.com/chatwork/scala-quiz/blob/master/quiz/01_WordCount.md
object WordCounter {

  /**
   * 文字列から単語数をカウントする。
   *
   * @param nx 文字列
   * @return Map[Key: 単語、Value: 単語数]
   */
  def countWords(nx: List[String]): Map[String, Int] = {
    nx.flatMap(_.split(" ")).groupBy(identity).map(e => e._1 -> e._2.size)
  }
}
