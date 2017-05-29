package cats

/**
 * Applicative functor.
 *
 * Allows application of a function in an Applicative context to a value in an Applicative context
 *
 * See: [[https://www.cs.ox.ac.uk/jeremy.gibbons/publications/iterator.pdf The Essence of the Iterator Pattern]]
 * Also: [[http://staff.city.ac.uk/~ross/papers/Applicative.pdf Applicative programming with effects]]
 *
 * Must obey the laws defined in cats.laws.ApplicativeLaws.
 */
trait Applicative[F[_]] extends Apply[F] {

  /**
   * `pure` lifts any value into the Applicative Functor.
   *
   * Applicative[Option].pure(10) = Some(10)
   */
  def pure[A](x: A): F[A]

  /**
   * Returns an `F[Unit]` value, equivalent with `pure(())`.
   *
   * A useful shorthand, also allowing implementations to optimize the
   * returned reference (e.g. it can be a `val`).
   */
  def unit: F[Unit] = pure(())

  def map[A, B](fa: F[A])(f: A => B): F[B]

  // def traverse[A, G[_], B](value: G[A])(f: A => F[B])(implicit G: Traverse[G]): F[G[B]] =
  //   G.traverse(value)(f)(this)

  // def sequence[G[_], A](as: G[F[A]])(implicit G: Traverse[G]): F[G[A]] =
  //   G.sequence(as)(this)

  /**
   * Returns the given argument if `cond` is `false`, otherwise, unit lifted into F.
   */
  def unlessA[A](cond: Boolean)(f: => F[A]): F[Unit] =
    if (cond) pure(()) else void(f)

  /**
   * Returns the given argument if `cond` is `true`, otherwise, unit lifted into F.
   */
  def whenA[A](cond: Boolean)(f: => F[A]): F[Unit] =
    if (cond) void(f) else pure(())
}
