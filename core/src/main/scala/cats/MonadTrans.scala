package cats

/**
 * A type class which abstracts over the ability to lift an M[A] into a
 * MonadTransformer
 */
trait MonadTrans[MT[_[_], _]] {

  /**
   * Lift a value of type M[A] into a monad transformer MT[M, A]
   */
  def liftT[M[_]: Monad, A](ma: M[A]): MT[M, A]
}
