package cats
package proofs

import stainless.lang._
import instances.EitherInstance

class EitherProofs[X, Y] extends EitherInstance[X, Y] {
  // Monad laws ---------------------------------------------------------------

  def eitherMonadLeftIdentity[A, B](a: A, f: A => Either[X, B]) =
    (flatMap(pure(a))(f) == f(a)).holds

  def eitherMonadRightIdentity[A](fa: Either[X, A]) =
    (flatMap(fa)(pure) == fa).holds

  def eitherMonadAssociativity[A, B, C](fa: Either[X, A], f: A => Either[X, B], g: B => Either[X, C]) =
    (flatMap(flatMap(fa)(f))(g) == flatMap(fa)(x => flatMap(f(x))(g))).holds

  // SemigroupK laws ----------------------------------------------------------

  def eitherSemigroupKAssociative[A](a: Either[X, A], b: Either[X, A], c: Either[X, A]) =
    (combineK(combineK(a, b), c) == combineK(a, combineK(b, c))).holds

  // Order laws ---------------------------------------------------------------

  def eitherOrderTotality(x: Either[X, Y], y: Either[X, Y]) =
    (compare(x, y) <= 0 || compare(y, x) <= 0).holds

  def eitherOrderAntisymmetry(x: Either[X, Y], y: Either[X, Y]) =
    ((compare(x, y) <= 0 && compare(y, x) <= 0) ==> (compare(x, y) == 0)).holds

  def eitherOrderTransitivity(x: Either[X, Y], y: Either[X, Y], z: Either[X, Y]) =
    ((compare(x, y) <= 0 && compare(y, z) <= 0) ==> (compare(x, z) <= 0)).holds
}
