package com.ponkotuy.hprof

import scala.io.Source

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
object Main extends App {
  val result = Parser(Source.fromFile("java.hprof.txt").getLines())
  println(result.traces.take(2))
}
