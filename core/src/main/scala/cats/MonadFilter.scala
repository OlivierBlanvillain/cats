package cats

/**
 * a Monad equipped with an additional method which allows us to
 * create an "empty" value for the Monad (for whatever "empty" makes
 * sense for that particular monad). This is of particular interest to
 * us since it allows us to add a `filter` method to a Monad, which is
 * used when pattern matching or using guards in for comprehensions.
 */
trait MonadFilter[F[_]] extends Monad[F] with FunctorFilter[F] {
  def empty[A]: F[A]
}
