package cats

import functor.Contravariant

/**
 * [[ContravariantCartesian]] is nothing more than something both contravariant
 * and Cartesian. It comes up enough to be useful, and composes well
 */
trait ContravariantCartesian[F[_]] extends Cartesian[F] with Contravariant[F]
