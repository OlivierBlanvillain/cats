package cats.kernel

/**
 * An commutative group (also known as an abelian group) is a group
 * whose combine operation is commutative.
 */
trait CommutativeGroup[A] extends Group[A] with CommutativeMonoid[A]
