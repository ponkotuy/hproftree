package com.ponkotuy.hprof.lines

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
sealed abstract class State {
  def parse(line: String): LineResult
}

object State {
  import Result._

  val trace = """^TRACE (\d+):$""".r
  val samplesBegin = """^CPU SAMPLES BEGIN \(total = (\d+)\) (.+)$""".r
  val sampleEnd = """^CPU SAMPLES END$""".r

  case object Start extends State {
    override def parse(line: String): LineResult = line match {
      case trace(tNum) => LineResult.ok(Trace, StartTrace(tNum.toInt))
      case _ => LineResult.none(Start)
    }
  }

  case object Trace extends State {
    override def parse(line: String): LineResult = line match {
      case trace(tNum) => LineResult.ok(Trace, StartTrace(tNum.toInt))
      case samplesBegin(total, date) => LineResult.ok(TableHeader, SamplesBegin(total.toInt))
      case _ => LineResult.ok(Trace, Code.fromLine(line))
    }
  }

  case object TableHeader extends State {
    override def parse(line: String): LineResult = LineResult.none(SampleRecord)
  }

  case object SampleRecord extends State {
    override def parse(line: String): LineResult = line match {
      case sampleEnd() => LineResult.none(End)
      case _ => LineResult.ok(SampleRecord, Sample.fromLine(line))
    }
  }

  case object End extends State {
    override def parse(line: String): LineResult = LineResult.none(End)
  }
}
