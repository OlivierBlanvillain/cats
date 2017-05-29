package cats
package instances

import stainless.lang._

trait OptionInstance[X] {
  def pure[A](x: A): Option[A] = Some(x)

  def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
    fa match {
      case Some(a) => f(a)
      case None()  => None()
    }

  def map[A, B](fa: Option[A])(f: A => B): Option[B] =
    fa match {
      case Some(a) => Some(f(a))
      case None()  => None()
    }

  def coflatMap[A, B](fa: Option[A])(f: Option[A] => B): Option[B] =
    fa match {
      case None()   => None()
      case Some(a) => Some(f(fa))
    }

  def coflatten[A](fa: Option[A]): Option[Option[A]] =
    coflatMap(fa)(fa => fa)

  def combineK[A](f1: Option[A], f2: Option[A]): Option[A] =
    f1 match {
      case Some(_) => f1
      case None()  => f2
    }

  def empty[A]: Option[A] = None()

  def compare(x: Option[X], y: Option[X]): Int =
    x match {
      case None() => y match {
        case None()  => 0
        case Some(_) => -1
      }
      case Some(a) =>
        y match {
          case None()  => 1
          case Some(_) => 0
        }
    }
}
