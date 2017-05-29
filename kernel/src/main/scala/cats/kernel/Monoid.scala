package cats.kernel

/**
 * A monoid is a semigroup with an identity. A monoid is a specialization of a
 * semigroup, so its operation must be associative. Additionally,
 * `combine(x, empty) == combine(empty, x) == x`. For example, if we have `Monoid[String]`,
 * with `combine` as string concatenation, then `empty = ""`.
 */
trait Monoid[A] extends Semigroup[A] {

  /**
   * Return the identity element for this monoid.
   */
  def empty: A

  /**
   * Tests if `a` is the identity.
   */
  def isEmpty(a: A)(implicit ev: Eq[A]) =
    ev.eqv(a, empty)
}
