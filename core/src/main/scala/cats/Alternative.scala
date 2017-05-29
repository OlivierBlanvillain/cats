package cats

trait Alternative[F[_]] extends Applicative[F] with MonoidK[F]
