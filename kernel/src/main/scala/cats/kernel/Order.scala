package cats.kernel

/**
 * The `Order` type class is used to define a total ordering on some type `A`.
 * An order is defined by a relation <=, which obeys the following laws:
 *
 * - either x <= y or y <= x (totality)
 * - if x <= y and y <= x, then x == y (antisymmetry)
 * - if x <= y and y <= z, then x <= z (transitivity)
 *
 * The truth table for compare is defined as follows:
 *
 * x <= y    x >= y      Int
 * true      true        = 0     (corresponds to x == y)
 * true      false       < 0     (corresponds to x < y)
 * false     true        > 0     (corresponds to x > y)
 *
 * By the totality law, x <= y and y <= x cannot be both false.
 */
trait Order[A] {
  /**
   * Result of comparing `x` with `y`. Returns an Int whose sign is:
   * - negative iff `x < y`
   * - zero     iff `x = y`
   * - positive iff `x > y`
   */
  def compare(x: A, y: A): Int

  /**
   * If x <= y, return x, else return y.
   */
  def min(x: A, y: A): A = if (lt(x, y)) x else y

  /**
   * If x >= y, return x, else return y.
   */
  def max(x: A, y: A): A = if (gt(x, y)) x else y

  // The following may be overridden for performance:

  /**
   * Returns true if `x` = `y`, false otherwise.
   */
  def eqv(x: A, y: A) =
    compare(x, y) == 0

  /**
   * Returns true if `x` != `y`, false otherwise.
   */
  def neqv(x: A, y: A) =
    compare(x, y) != 0

  /**
   * Returns true if `x` <= `y`, false otherwise.
   */
  def lteqv(x: A, y: A) =
    compare(x, y) <= 0

  /**
   * Returns true if `x` < `y`, false otherwise.
   */
  def lt(x: A, y: A) =
    compare(x, y) < 0

  /**
   * Returns true if `x` >= `y`, false otherwise.
   */
  def gteqv(x: A, y: A) =
    compare(x, y) >= 0

  /**
   * Returns true if `x` > `y`, false otherwise.
   */
  def gt(x: A, y: A) =
    compare(x, y) > 0
}
