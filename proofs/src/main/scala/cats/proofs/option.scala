package cats
package proofs

import stainless.lang._
import instances.OptionInstance

class OptionProofs[X] extends OptionInstance[X] {
  // Monad laws ---------------------------------------------------------------

  def optionMonadLeftIdentity[A, B](a: A, f: A => Option[B]) =
    (flatMap(pure(a))(f) == f(a)).holds

  def optionMonadRightIdentity[A](fa: Option[A]) =
    (flatMap(fa)(pure) == fa).holds

  def optionMonadAssociativity[A, B, C](fa: Option[A], f: A => Option[B], g: B => Option[C]) =
    (flatMap(flatMap(fa)(f))(g) == flatMap(fa)(x => flatMap(f(x))(g))).holds

  // MonoidK laws -------------------------------------------------------------

  def optionSemigroupKAssociative[A](a: Option[A], b: Option[A], c: Option[A]) =
    (combineK(combineK(a, b), c) == combineK(a, combineK(b, c))).holds

  def optionMonoidKLeftIdentity[A](a: Option[A]) =
    (combineK(empty, a) == a).holds

  def optionMonoidKRightIdentity[A](a: Option[A]) =
    (combineK(a, empty) == a).holds

  // Order laws ---------------------------------------------------------------

  def optionOrderTotality(x: Option[X], y: Option[X]) =
    (compare(x, y) <= 0 || compare(y, x) <= 0).holds

  def optionOrderAntisymmetry(x: Option[X], y: Option[X]) =
    ((compare(x, y) <= 0 && compare(y, x) <= 0) ==> (compare(x, y) == 0)).holds

  def optionOrderTransitivity(x: Option[X], y: Option[X], z: Option[X]) =
    ((compare(x, y) <= 0 && compare(y, z) <= 0) ==> (compare(x, z) <= 0)).holds

  // CoflatMap laws -----------------------------------------------------------

  def optionCoflatMapAssociativity[A, B, C](fa: Option[A], f: Option[A] => B, g: Option[B] => C) =
    (coflatMap(coflatMap(fa)(f))(g) == coflatMap(fa)(x => g(coflatMap(x)(f)))).holds

  def optionCoflattenThroughMap[A](fa: Option[A]) =
    (coflatten(coflatten(fa)) == map(coflatten(fa))(coflatten)).holds

  def optionCoflattenCoherence[A, B](fa: Option[A], f: Option[A] => B) =
    (coflatMap(fa)(f) == map(coflatten(fa))(f)).holds

  def optionCoflatMapIdentity[A, B](fa: Option[A]) =
    (coflatten(fa) == coflatMap(fa)(x => x)).holds
}
