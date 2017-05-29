package cats
package proofs

import stainless.lang._
import instances.BigIntInstance

class BitIntProofs extends BigIntInstance {
  // Order laws ---------------------------------------------------------------

  def bigintOrderTotality(x: Int, y: Int) =
    (compare(x, y) <= 0 || compare(y, x) <= 0).holds

  def bigintOrderAntisymmetry(x: Int, y: Int) =
    ((compare(x, y) <= 0 && compare(y, x) <= 0) ==> (compare(x, y) == 0)).holds

  def bigintOrderTransitivity(x: Int, y: Int, z: Int) =
    ((compare(x, y) <= 0 && compare(y, z) <= 0) ==> (compare(x, z) <= 0)).holds

  // CommutativeGroup laws ----------------------------------------------------

  def bigintSemigroupAssociative[A](a: Int, b: Int, c: Int) =
    (combine(combine(a, b), c) == combine(a, combine(b, c))).holds

  def bigintMonoidLeftIdentity[A](a: Int) =
    (combine(empty, a) == a).holds

  def bigintMonoidRightIdentity[A](a: Int) =
    (combine(a, empty) == a).holds

  def bigintCommutativeGroup[A](a: Int, b: Int) =
    (combine(a, b) == combine(b, a)).holds
}
