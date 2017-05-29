package cats
package arrow

/**
 * Must obey the laws defined in cats.laws.ComposeLaws.
 */
trait Compose[F[_, _]] {

  def compose[A, B, C](f: F[B, C], g: F[A, B]): F[A, C]

  def andThen[A, B, C](f: F[A, B], g: F[B, C]): F[A, C] =
    compose(g, f)
}
