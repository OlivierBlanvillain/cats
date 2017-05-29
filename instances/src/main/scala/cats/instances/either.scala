package cats
package instances

import stainless.lang._

trait EitherInstance[X, Y] {
  def pure[A](x: A): Either[X, A] = Right(x)

  def flatMap[A, B](fa: Either[X, A])(f: A => Either[X, B]): Either[X, B] =
    fa match {
      case Right(a) => f(a)
      case Left(x)  => Left(x)
    }

  def map[A, B](fa: Either[X, A])(f: A => B): Either[X, B] =
    fa match {
      case Right(a) => Right(f(a))
      case Left(x)  => Left(x)
    }

  def combineK[A](f1: Either[X, A], f2: Either[X, A]): Either[X, A] =
    f1 match {
      case Right(_) => f1
      case Left(_)  => f2
    }

  def compare(x: Either[X, Y], y: Either[X, Y]): Int =
    x match {
      case Left(_) => y match {
        case Left(_)  => 0
        case Right(_) => -1
      }
      case Right(a) =>
        y match {
          case Left(_)  => 1
          case Right(_) => 0
        }
    }

}
