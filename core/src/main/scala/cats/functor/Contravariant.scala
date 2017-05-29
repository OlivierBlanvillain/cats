package cats
package functor

/**
 * Must obey the laws defined in cats.laws.ContravariantLaws.
 */
trait Contravariant[F[_]] extends Invariant[F] {
  def contramap[A, B](fa: F[A])(f: B => A): F[B]
  override def imap[A, B](fa: F[A])(f: A => B)(fi: B => A): F[B] = contramap(fa)(fi)
}
