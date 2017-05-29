package cats

trait Bimonad[F[_]] extends Monad[F] with Comonad[F]
