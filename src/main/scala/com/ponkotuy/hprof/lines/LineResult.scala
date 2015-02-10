package com.ponkotuy.hprof.lines

import scala.util.Try

/**
 *
 * @author ponkotuy
 * Date: 15/02/10.
 */
case class LineResult(next: State, result: Option[Result])

object LineResult {
  def ok(next: State, result: Result): LineResult = LineResult(next, Option(result))
  def none(next: State): LineResult = LineResult(next, None)
}

sealed abstract class Result

object Result {
  case class StartTrace(num: Int) extends Result

  case class Code(func: String, file: String, lineNum: Option[Int]) extends Result
  object Code {
    def fromLine(line: String): Code = {
      val (func, rest) = line.trim.span(_ != '(')
      val (file, withNum) = rest.tail.span(_ != ':')
      val lineNum = Try { withNum.tail.init.toInt }.toOption
      Code(func, file, lineNum)
    }
  }

  case class SamplesBegin(total: Int) extends Result

  case class Sample(rank: Int, self: Double, accum: Double, count: Int, trace: Int, method: String) extends Result
  object Sample {
    def fromLine(line: String): Sample = {
      val Array(rank, self, accum, count, trace, method) = line.trim.split(' ').filterNot(_.isEmpty)
      Sample(rank.toInt, fromPercent(self), fromPercent(accum), count.toInt, trace.toInt, method)
    }
  }

  private def fromPercent(str: String): Double = str.init.toDouble / 100
}
