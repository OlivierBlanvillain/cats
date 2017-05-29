package cats

import cats.kernel._
import cats.arrow._
import stainless.lang._
import stainless.collection._

package object instances {
  implicit val bigIntInstance: Order[Int] with CommutativeGroup[Int] =
    new BigIntInstance with Order[Int] with CommutativeGroup[Int] {}

  implicit def eitherInstance[X, Y]: Any
      with Monad[Either[X, ?]]
      with SemigroupK[Either[X, ?]]
      with Order[Either[X, Y]] =
    new EitherInstance[X, Y]
      with Monad[Either[X, ?]]
      with SemigroupK[Either[X, ?]]
      with Order[Either[X, Y]] {}

  implicit def listInstance[X]: Any
      with Monad[List]
      with MonoidK[List]
      with Order[List[X]]
      with CoflatMap[List] =
    new ListInstance[X]
      with Monad[List]
      with MonoidK[List]
      with Order[List[X]]
      with CoflatMap[List] {}

  implicit def optionInstance[X]: Any
      with Monad[Option]
      with MonoidK[Option]
      with CoflatMap[Option]
      with Order[Option[X]] =
    new OptionInstance[X]
      with Monad[Option]
      with MonoidK[Option]
      with CoflatMap[Option]
      with Order[Option[X]] {}

  implicit val realInstance: Any
      with Order[Real]
      with CommutativeGroup[Real] =
    new RealInstance
      with Order[Real]
      with CommutativeGroup[Real] {}

  implicit val setInstance: Any
      with MonoidK[? => Boolean] =
    new SetInstance
      with MonoidK[? => Boolean] {}

  implicit def function1MonadReaderInstance[X]: Any
      with MonadReader[X => ?, X] =
    new Function1MonadReaderInstance[X]
      with MonadReader[X => ?, X] {}

  implicit def function1ContravariantInstance[X]: Any
      with functor.Contravariant[? => X] =
    new Function1ContravariantInstance[X]
      with functor.Contravariant[? => X] {}

  implicit val function0BimonadInstance: Any
      with Bimonad[Function0] =
    new Function0BimonadInstance
      with Bimonad[Function0] {}
}
