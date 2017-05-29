package cats.kernel

/**
 * A group is a monoid where each element has an inverse.
 */
trait Group[A] extends Monoid[A] {

  /**
   * Find the inverse of `a`.
   *
   * `combine(a, inverse(a))` = `combine(inverse(a), a)` = `empty`.
   */
  def inverse(a: A): A
}
