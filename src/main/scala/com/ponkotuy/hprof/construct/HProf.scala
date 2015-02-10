package com.ponkotuy.hprof.construct

import com.ponkotuy.hprof.lines.Result
import com.ponkotuy.hprof.lines.Result._

import scala.util.Try

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
case class HProf(traces: Vector[Trace], count: Int, samples: Vector[Sample])

object HProf {
  def apply(lines: Vector[Result]): HProf = {

    val traceLines = lines.collect {
      case x: StartTrace => x
      case x: Code => x
    }
    val traces = split[Result](traceLines, x => x.isInstanceOf[StartTrace]).collect {
      case (x: StartTrace) +: (xs: Vector[_]) => Trace.fromResult(x, xs.collect { case y: Code => y })
    }

    val count = lines.collectFirst { case x: SamplesBegin => x }.map(_.total).getOrElse(0)

    val samples = lines.collect { case x: Sample => x }
    HProf(traces, count, samples)
  }

  private[construct] def split[A](xs: Vector[A], f: A => Boolean): Vector[Vector[A]] = {
    if(xs.isEmpty) return Vector()
    val builder = Vector.newBuilder[Vector[A]]
    var head: Option[A] = xs.headOption
    var rest = xs.tail
    while(rest.nonEmpty && head.isDefined) {
      val (x, y) = rest.span(!f(_))
      builder += (head.get +: x)
      head = y.headOption
      rest = Try { y.tail }.getOrElse(Vector())
    }
    builder.result()
  }
}
