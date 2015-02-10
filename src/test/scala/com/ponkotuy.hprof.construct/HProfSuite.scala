package com.ponkotuy.hprof.construct

import org.scalatest.FunSpec

/**
 *
 * @author ponkotuy
 * Date: 15/02/11.
 */
class HProfSuite extends FunSpec {
  describe("split") {
    it("should split from [A, B, B, A, B] to [[A, B, B], [A, B]") {
      val value = HProf.split[Int](Vector(1, 2, 2, 1, 2), _ == 1)
      assert(value === Vector(Vector(1, 2, 2), Vector(1, 2)))
    }
  }
}
