package Utils

import java.io.{File, PrintWriter}

class Writter {
  val printWritter = new PrintWriter(new File("output.txt"))

  def printInFile(output: String): Unit = printWritter.write(output)

  def close(): Unit = printWritter.close()
}
