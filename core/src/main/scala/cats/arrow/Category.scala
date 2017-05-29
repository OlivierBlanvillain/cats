package cats
package arrow

/**
 * Must obey the laws defined in cats.laws.CategoryLaws.
 */
trait Category[F[_, _]] extends Compose[F] {
  def id[A]: F[A, A]
}
