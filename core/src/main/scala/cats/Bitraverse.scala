package cats

import cats.functor.Bifunctor

/**
 *  A type class abstracting over types that give rise to two independent [[cats.Traverse]]s.
 */
trait Bitraverse[F[_, _]] extends Bifoldable[F] with Bifunctor[F] {

  /** Traverse each side of the structure with the given functions */
  def bitraverse[G[_]: Applicative, A, B, C, D](fab: F[A, B])(f: A => G[C], g: B => G[D]): G[F[C, D]]

  /**
   * Sequence each side of the structure with the given functions.
   *
   * Example:
   * {{{
   * scala> import cats.implicits._
   *
   * scala> val rightSome: Either[Option[String], Option[Int]] = Either.right(Some(3))
   * scala> rightSome.bisequence
   * res0: Option[Either[String, Int]] = Some(Right(3))
   *
   * scala> val rightNone: Either[Option[String], Option[Int]] = Either.right(None)
   * scala> rightNone.bisequence
   * res1: Option[Either[String, Int]] = None
   *
   * scala> val leftSome: Either[Option[String], Option[Int]] = Either.left(Some("foo"))
   * scala> leftSome.bisequence
   * res2: Option[Either[String, Int]] = Some(Left(foo))
   *
   * scala> val leftNone: Either[Option[String], Option[Int]] = Either.left(None)
   * scala> leftNone.bisequence
   * res3: Option[Either[String, Int]] = None
   * }}}
   */
  def bisequence[G[_]: Applicative, A, B](fab: F[G[A], G[B]]): G[F[A, B]] =
    bitraverse(fab)(x => x, x => x)
}
