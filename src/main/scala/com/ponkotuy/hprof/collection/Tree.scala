package com.ponkotuy.hprof.collection

/**
 *
 * @author ponkotuy
 * Date: 15/02/10.
 */
trait Tree[K, A] {
  def key: K
  def get: A
  def subs: Map[K, Tree[K, A]]
}

case class TreeImpl[K, A](key: K, me: A, subs: Map[K, Tree[K, A]]) extends Tree[K, A] {
  override def get: A = me
}
