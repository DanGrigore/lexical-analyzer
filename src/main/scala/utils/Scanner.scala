package utils

import scala.io.Source

class Scanner {
  private val bufferedSource = Source.fromFile("input.txt")
  private val lines: String = bufferedSource mkString

  def getLines: String = lines

  def close(): Unit = bufferedSource.close()

}
