package cats
package proofs

import stainless.lang._
import stainless.proof._
import instances._

class Function1MonadReaderProof[X] extends Function1MonadReaderInstance[X] {
  // Monad Reader laws --------------------------------------------------------

  val function1MonadReaderAskIdempotent =
    (flatMap(ask)(_ => ask) == ask).holds

  def function1MonadReaderLocalAsk(f: X => X) =
    (local(f)(ask) == map(ask)(f))


  def function1MonadReaderLocalPure[A](a: A, f: X => X) =
    local(f)(pure(a)) == pure(a)

  // Hangs on a lambda precondition.
  // def function1MonadReaderLocalFlatMap[A, B](fra: X => A, f: A => X => B, g: X => X, x: X) =
  //   (local(g)(flatMap(fra)(f))(x) == flatMap(local(g)(fra))(a => local(g)(f(a)))(x)).holds

  def function1MonadReaderReaderAsk[A](f: X => A) =
    (reader(f) == map(ask)(f)).holds

  // Monad laws ---------------------------------------------------------------

  def function1MonadLeftIdentity[A, B](a: A, f: A => X => B) =
    flatMap(pure(a))(f) == f(a)

  def function1MonadRightIdentity[A](fa: X => A) =
    flatMap(fa)(pure) == fa

  def function1MonadAssociativity[A, B, C](fa: X => A, f: A => X => B, g: B => X => C) =
    flatMap(flatMap(fa)(f))(g) == flatMap(fa)(x => flatMap(f(x))(g))

  // Proofs -------------------------------------------------------------------

  def function1MonadReaderLocalAskProof(f: X => X, x: X) =
    function1MonadReaderLocalAsk(f) because {
      local(f)(ask)(x) == map(ask)(f)(x)
    }.holds

  def function1MonadReaderLocalPureProof[A](a: A, f: X => X, x: X) =
    function1MonadReaderLocalPure(a, f) because {
      local(f)(pure(a))(x) == pure(a)(x)
    }.holds

  def function1MonadLeftIdentityProof[A, B](a: A, f: A => X => B, x: X) =
    function1MonadLeftIdentity(a, f) because {
      flatMap(pure(a))(f)(x) == f(a)(x)
    }.holds

  def function1MonadRightIdentityProof[A](fa: X => A, x: X) =
    function1MonadRightIdentity(fa) because {
      flatMap(fa)(pure)(x) == fa(x)
    }.holds

  def function1MonadAssociativityProof[A, B, C](fa: X => A, f: A => X => B, g: B => X => C, x: X) =
    function1MonadAssociativity(fa, f, g) because {
      flatMap(flatMap(fa)(f))(g)(x) == flatMap(fa)(x => flatMap(f(x))(g))(x)
    }.holds
}

class Function1ContravariantProof[X] extends Function1ContravariantInstance[X] {
  // Contravariant Functor laws -----------------------------------------------

  def contravariantIdentity[A](fa: A => X, a: A) =
    (contramap(fa)((x: A) => x)(a) == fa(a)).holds

  def contravariantComposition[A, B, C](fa: A => X, f: B => A, g: C => B) =
    (contramap(contramap(fa)(f))(g) == contramap(fa)((c: C) => f(g(c))))

  // Proofs -------------------------------------------------------------------

  def contravariantCompositionProof[A, B, C](fa: A => X, f: B => A, g: C => B, c: C) =
    contravariantComposition(fa, f, g) because {
      (contramap(contramap(fa)(f))(g)(c) == contramap(fa)((c: C) => f(g(c)))(c))
    }.holds
}

class Function0BimonadProof extends Function0BimonadInstance {
  // Monad laws ---------------------------------------------------------------

  def function0MonadLeftIdentity[A, B](a: A, f: A => () => B) =
    (flatMap(pure(a))(f).apply == f(a).apply).holds

  def function0MonadRightIdentity[A](fa: () => A) =
    (flatMap(fa)(pure) == fa).holds

  def function0MonadAssociativity[A, B, C](fa: () => A, f: A => () => B, g: B => () => C) =
    (flatMap(flatMap(fa)(f))(g) == flatMap(fa)(x => flatMap(f(x))(g))).holds

  // CoflatMap laws -----------------------------------------------------------

  def function0CoflatMapAssociativity[A, B, C](fa: () => A, f: (() => A) => B, g: (() => B) => C) =
    (coflatMap(coflatMap(fa)(f))(g) == coflatMap(fa)(x => g(coflatMap(x)(f)))).holds

  def function0CoflattenThroughMap[A](fa: () => A) =
    (coflatten(coflatten(fa)) == map(coflatten(fa))(coflatten)).holds

  def function0CoflattenCoherence[A, B](fa: () => A, f: (() => A) => B) =
    (coflatMap(fa)(f) == map(coflatten(fa))(f)).holds

  def function0CoflatMapIdentity[A, B](fa: () => A) =
    (coflatten(fa) == coflatMap(fa)(x => x)).holds
}
