package cats
package functor

/**
 * Must obey the laws defined in cats.laws.InvariantLaws.
 */
trait Invariant[F[_]] {
  def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]
}
