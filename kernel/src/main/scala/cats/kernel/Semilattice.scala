package cats.kernel

/**
 * Semilattices are commutative semigroups whose operation
 * (i.e. combine) is also idempotent.
 */
trait Semilattice[A] extends Band[A] with CommutativeSemigroup[A]
