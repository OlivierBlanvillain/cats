package cats
package proofs

import stainless.collection._
import stainless.lang._
import stainless.proof._
import instances.OptionInstance

// Should ideally be formulated on F[_], but stainless doesn't seems to like HKT...
class MetaProofs[X] extends OptionInstance[X] {
  // Functor laws -------------------------------------------------------------

  def metaCovariantFunctorIdentity[A](fa: Option[A]) =
    map(fa)(x => x) == fa

  def metaCovariantFunctorComposition[A, B, C](fa: Option[A], f: A => B, g: B => C) =
    map(map(fa)(f))(g) == map(fa)((a: A) => g(f(a)))

  // Monad laws ---------------------------------------------------------------

  def metaMonadLeftIdentity[A, B](a: A, f: A => Option[B]) =
    flatMap(pure(a))(f) == f(a)

  def metaMonadRightIdentity[A](fa: Option[A]) =
    flatMap(fa)(pure) == fa

  def metaMonadAssociativity[A, B, C](fa: Option[A], f: A => Option[B], g: B => Option[C]) =
    flatMap(flatMap(fa)(f))(g) == flatMap(fa)(x => flatMap(f(x))(g))

  // Monad laws => Functor laws! ----------------------------------------------

  def metaCovariantFunctorIdentityProof[A, B, C](fa: Option[A]) = {
    metaMonadRightIdentity(fa) ==> metaCovariantFunctorIdentity(fa)
    //     map(fa)(x => x)                                -- By definition of rhs
    // <-> flatMap(fa)((a: A) => pure(((x: A) => x)(a)))  -- By definition of map
    // <-> flatMap(fa)((a: A) => pure(a))                 -- Beta reduction on identity
    // <-> flatMap(fa)(pure)                              -- Syntactic rewrite
    // <-> fa                                             -- From listMonadRightIdentity
  }.holds

  def metaCovariantFunctorCompositionProof[A, B, C](fa: Option[A], f: A => B, g: B => C) = {
    (     metaMonadRightIdentity(fa)
       && metaMonadAssociativity(fa, (a: A) => pure(f(a)), (b: B) => pure(g(b)))
    ) ==> metaCovariantFunctorComposition(fa, f, g)
  }.holds

}
