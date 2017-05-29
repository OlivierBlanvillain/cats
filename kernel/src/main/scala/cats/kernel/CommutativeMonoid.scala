  package cats.kernel

/**
 * CommutativeMonoid represents a commutative monoid.
 *
 * A monoid is commutative if for all x and y, x |+| y === y |+| x.
 */
trait CommutativeMonoid[A] extends Monoid[A] with CommutativeSemigroup[A]
