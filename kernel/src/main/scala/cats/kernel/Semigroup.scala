package cats.kernel

/**
 * A semigroup is any set `A` with an associative operation (`combine`).
 */
trait Semigroup[A] {
  /**
   * Associative operation taking which combines two values.
   */
  def combine(x: A, y: A): A
}
