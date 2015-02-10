package com.ponkotuy.hprof

/**
 *
 * @author ponkotuy
 * Date: 15/02/10.
 */
case class Tree[A](me: A, subs: Vector[Tree[A]]) {
  def get: A = me
}
