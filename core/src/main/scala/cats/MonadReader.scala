package cats

/** A monad that has the ability to read from an environment. */
trait MonadReader[F[_], R] extends Monad[F] {
  /** Get the environment */
  def ask: F[R]

  /** Modify the environment */
  def local[A](f: R => R)(fa: F[A]): F[A]

  /** Retrieves a function of the environment */
  def reader[A](f: R => A): F[A]
}
