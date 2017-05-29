package cats

/**
 * The combination of a Monad with a MonoidK
 */
trait MonadCombine[F[_]] extends MonadFilter[F] with Alternative[F]
