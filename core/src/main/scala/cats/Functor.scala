package cats

/**
 * Functor.
 *
 * The name is short for "covariant functor".
 *
 * Must obey the laws defined in cats.laws.FunctorLaws.
 */
trait Functor[F[_]] extends functor.Invariant[F] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def imap[A, B](fa: F[A])(f: A => B)(fi: B => A): F[B] = map(fa)(f)

  /**
   * Lift a function f to operate on Functors
   */
  def lift[A, B](f: A => B): F[A] => F[B] = map(_)(f)

  /**
   * Empty the fa of the values, preserving the structure
   */
  def void[A](fa: F[A]): F[Unit] = map(fa)(_ => ())

  /**
   * Tuple the values in fa with the result of applying a function
   * with the value
   */
  def fproduct[A, B](fa: F[A])(f: A => B): F[(A, B)] = map(fa)(a => a -> f(a))

  /**
   * Replaces the `A` value in `F[A]` with the supplied value.
   */
  def as[A, B](fa: F[A], b: B): F[B] = map(fa)(_ => b)

  /**
    * Tuples the `A` value in `F[A]` with the supplied `B` value, with the `B` value on the left.
    */
  def tupleLeft[A, B](fa: F[A], b: B): F[(B, A)] = map(fa)(a => (b, a))

  /**
    * Tuples the `A` value in `F[A]` with the supplied `B` value, with the `B` value on the right.
    */
  def tupleRight[A, B](fa: F[A], b: B): F[(A, B)] = map(fa)(a => (a, b))
}
