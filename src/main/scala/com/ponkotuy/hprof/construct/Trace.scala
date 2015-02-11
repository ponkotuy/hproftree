package com.ponkotuy.hprof.construct

import com.ponkotuy.hprof.lines.Result.{Sample, StartTrace, Code}

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
case class Trace(num: Int, codes: Vector[Code], sample: Sample)

object Trace {
  def fromResult(start: StartTrace, codes: Iterable[Code], sample: Sample) =
    Trace(start.num, codes.toVector, sample)
}
