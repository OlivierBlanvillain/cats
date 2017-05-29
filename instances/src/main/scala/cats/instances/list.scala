package cats
package instances

import stainless.collection._

trait ListInstance[X] {
  def pure[A](x: A): List[A] = List(x)

  def empty[A]: List[A] = Nil[A]()

  def map[A, B](fa: List[A])(f: A => B): List[B] =
    fa match {
      case Nil()   => Nil()
      case Cons(x, xs) => Cons(f(x), map(xs)(f))
    }

  def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] =
    fa match {
      case Cons(x, xs) => combineK(f(x), flatMap(xs)(f))
      case Nil() => Nil()
    }

  def combineK[A](f1: List[A], f2: List[A]): List[A] =
    f1 match {
      case Cons(x, xs) => Cons(x, combineK(xs, f2))
      case Nil() => f2
    }

  def coflatMap[A, B](fa: List[A])(f: List[A] => B): List[B] =
    fa match {
      case Nil()   => Nil()
      case Cons(_, xs) => Cons(f(fa), coflatMap(xs)(f))
    }

  def coflatten[A](fa: List[A]): List[List[A]] =
    coflatMap(fa)(fa => fa)

  def compare(f1: List[X], f2: List[X]): Int =
    f1 match {
      case Nil() => f2 match {
        case Nil()      => 0
        case Cons(_, _) => -1
      }
      case Cons(x, xs) => f2 match {
        case Nil()       => 1
        case Cons(y, ys) => compare(xs, ys)
      }
    }
}
