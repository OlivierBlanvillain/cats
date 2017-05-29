package cats
package proofs

import stainless.lang._
import instances.SetInstance

class SetProofs extends SetInstance {
  // MonoidK laws -------------------------------------------------------------

  def setSemigroupKAssociative[A](a: A => Boolean, b: A => Boolean, c: A => Boolean, e: A) =
    (combineK(combineK(a, b), c)(e) == combineK(a, combineK(b, c))(e)).holds

  def setMonoidKLeftIdentity[A](a: A => Boolean, e: A) =
    (combineK(empty[A], a)(e) == a(e)).holds

  def setMonoidKRightIdentity[A](a: A => Boolean, e: A) =
    (combineK(a, empty[A])(e) == a(e)).holds
}
