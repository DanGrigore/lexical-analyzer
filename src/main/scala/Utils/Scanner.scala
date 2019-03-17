package Utils

import scala.io.Source
import Utils.Analyzer.Token

class Scanner {
  private val bufferedSource = Source.fromFile("input.txt")
  val lines: String = bufferedSource mkString
  /* val lines = Source.fromFile("/Users/Al/.bash_profile").getLines.toList */
  /* val fileContents = Source.fromFile(filename).getLines.mkString */



  def returnLines: String = lines

  def close(): Unit = bufferedSource.close()

}
