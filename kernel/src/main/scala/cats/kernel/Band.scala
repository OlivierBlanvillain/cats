package cats.kernel

/**
 * Bands are semigroups whose operation
 * (i.e. combine) is also idempotent.
 */
trait Band[A] extends Semigroup[A]
