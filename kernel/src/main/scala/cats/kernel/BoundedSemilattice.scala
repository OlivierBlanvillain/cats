package cats.kernel

trait BoundedSemilattice[A] extends Semilattice[A] with CommutativeMonoid[A]
