package cats
package arrow

trait Choice[F[_, _]] extends Category[F] {
  /**
   * Given two `F`s (`f` and `g`) with a common target type, create a new `F`
   * with the same target type, but with a source type of either `f`'s source
   * type OR `g`'s source type.
   */
  def choice[A, B, C](f: F[A, C], g: F[B, C]): F[Either[A, B], C]

  /**
   * An `F` that, given a source `A` on either the right or left side, will
   * return that same `A` object.
   *
   * Example:
   * {{{
   * scala> import cats.implicits._
   * scala> val f: (Either[Int, Int]) => Int = Choice[Function1].codiagonal[Int]
   *
   * scala> f(Right(3))
   * res0: Int = 3
   *
   * scala> f(Left(3))
   * res1: Int = 3
   * }}}
   */
  def codiagonal[A]: F[Either[A, A], A] = choice(id, id)
}
