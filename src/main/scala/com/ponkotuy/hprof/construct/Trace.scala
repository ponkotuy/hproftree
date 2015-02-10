package com.ponkotuy.hprof.construct

import com.ponkotuy.hprof.lines.Result.{StartTrace, Code}

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
case class Trace(num: Int, codes: Vector[Code])

object Trace {
  def fromResult(start: StartTrace, codes: Iterable[Code]) = Trace(start.num, codes.toVector)
}
