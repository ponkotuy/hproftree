package com.ponkotuy.hprof

/**
 *
 * @author ponkotuy
 * Date: 15/02/10.
 */
object Parser {
  def apply(file: TraversableOnce[String]): construct.HProf = {
    val results = lines.Parse(file).toVector
    construct.HProf(results)
  }
}
