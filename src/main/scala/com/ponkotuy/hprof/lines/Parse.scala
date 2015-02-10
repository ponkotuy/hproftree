package com.ponkotuy.hprof.lines

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
object Parse {
  import State._

  def apply(file: TraversableOnce[String]): List[Result] = {
    file.foldLeft[(State, List[Result])]((Start, Nil)) { case (before, line) =>
      val (state, xs) = before
      val result = state.parse(line)
      (result.next, result.result.map(_ :: xs).getOrElse(xs))
    }._2.reverse
  }
}
