package cats
package proofs

import stainless.lang._
import instances.RealInstance

class RealProofs extends RealInstance {
  // Order laws ---------------------------------------------------------------

  def realOrderTotality(x: Real, y: Real) =
    (compare(x, y) <= 0 || compare(y, x) <= 0).holds

  def realOrderAntisymmetry(x: Real, y: Real) =
    ((compare(x, y) <= 0 && compare(y, x) <= 0) ==> (compare(x, y) == 0)).holds

  def realOrderTransitivity(x: Real, y: Real, z: Real) =
    ((compare(x, y) <= 0 && compare(y, z) <= 0) ==> (compare(x, z) <= 0)).holds

  // CommutativeGroup laws ----------------------------------------------------

  def realSemigroupAssociative[A](a: Real, b: Real, c: Real) =
    (combine(combine(a, b), c) == combine(a, combine(b, c))).holds

  def realMonoidLeftIdentity[A](a: Real) =
    (combine(empty, a) == a).holds

  def realMonoidRightIdentity[A](a: Real) =
    (combine(a, empty) == a).holds

  def realCommutativeGroup[A](a: Real, b: Real) =
    (combine(a, b) == combine(b, a)).holds
}
