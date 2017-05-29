package cats
package proofs

import stainless.collection._
import stainless.lang._
import stainless.proof._
import instances.ListInstance

class ListProofs[X] extends ListInstance[X] {
  // Monad laws ---------------------------------------------------------------

  def listMonadLeftIdentity[A, B](a: A, f: A => List[B]) =
    flatMap(pure(a))(f) == f(a)

  def listMonadRightIdentity[A](fa: List[A]) =
    flatMap(fa)(pure) == fa

  def listMonadAssociativity[A, B, C](fa: List[A], f: A => List[B], g: B => List[C]) =
    flatMap(flatMap(fa)(f))(g) == flatMap(fa)(x => flatMap(f(x))(g))

  // MonoidK laws -------------------------------------------------------------

  def listSemigroupKAssociative[A](a: List[A], b: List[A], c: List[A]) =
    combineK(combineK(a, b), c) == combineK(a, combineK(b, c))

  def listMonoidKLeftIdentity[A](a: List[A]): Boolean =
    (combineK(empty, a) == a).holds

  def listMonoidKRightIdentity[A](a: List[A]) =
    combineK(a, empty) == a

  // Order laws ---------------------------------------------------------------

  def listOrderTotality(x: List[X], y: List[X]) =
    compare(x, y) <= 0 || compare(y, x) <= 0

  def listOrderAntisymmetry(x: List[X], y: List[X]) =
    (compare(x, y) <= 0 && compare(y, x) <= 0) ==> (compare(x, y) == 0)

  def listOrderTransitivity(x: List[X], y: List[X], z: List[X]) =
    (compare(x, y) <= 0 && compare(y, z) <= 0) ==> (compare(x, z) <= 0)

  // CoflatMap laws -----------------------------------------------------------

  def listCoflatMapAssociativity[A, B, C](fa: List[A], f: List[A] => B, g: List[B] => C) =
    coflatMap(coflatMap(fa)(f))(g) == coflatMap(fa)(x => g(coflatMap(x)(f)))

  def listCoflattenThroughMap[A](fa: List[A]) =
    coflatten(coflatten(fa)) == map(coflatten(fa))(coflatten)

  def listCoflattenCoherence[A, B](fa: List[A], f: List[A] => B) =
    coflatMap(fa)(f) == map(coflatten(fa))(f)

  def listCoflatMapIdentity[A, B](fa: List[A]): Boolean =
    (coflatten(fa) == coflatMap(fa)(x => x)).holds

  // Proofs -------------------------------------------------------------------

  def listCoflatMapAssociativityProof[A, B, C](fa: List[A], f: List[A] => B, g: List[B] => C): Boolean = {
    listCoflatMapAssociativity(fa, f, g) because {
      fa match {
        case Nil()       => trivial
        case Cons(_, xs) => listCoflatMapAssociativityProof(xs, f, g)
      }
    }
  }.holds

  def listCoflattenThroughMapProof[A, B, C](fa: List[A]): Boolean = {
    listCoflattenThroughMap(fa) because {
      fa match {
        case Nil()       => trivial
        case Cons(_, xs) => listCoflattenThroughMapProof(xs)
      }
    }
  }.holds

  def listCoflattenCoherenceProof[A, B, C](fa: List[A], f: List[A] => B): Boolean = {
    listCoflattenCoherence(fa, f) because {
      fa match {
        case Nil()       => trivial
        case Cons(_, xs) => listCoflattenCoherenceProof(xs, f)
      }
    }
  }.holds

  def listOrderTotalityProof(x: List[X], y: List[X]): Boolean = {
    listOrderTotality(x, y) because {
      x match {
        case Nil()       => trivial
        case Cons(_, xs) => y match {
          case Nil()       => trivial
          case Cons(_, ys) => listOrderTotalityProof(xs, ys)
        }
      }
    }
  }.holds

  def listOrderAntisymmetryProof(x: List[X], y: List[X]): Boolean = {
    listOrderAntisymmetry(x, y) because {
      x match {
        case Nil()       => trivial
        case Cons(_, xs) => y match {
          case Nil()       => trivial
          case Cons(_, ys) => listOrderAntisymmetryProof(xs, ys)
        }
      }
    }
  }.holds

  def listOrderTransitivityProof(x: List[X], y: List[X], z: List[X]): Boolean = {
    listOrderTransitivity(x, y, z) because {
      x match {
        case Nil()       => trivial
        case Cons(_, xs) => y match {
          case Nil()       => trivial
          case Cons(_, ys) => z match {
            case Nil()       => trivial
            case Cons(_, zs) => listOrderTransitivityProof(xs, ys, zs)
          }
        }
      }
    }
  }.holds

  def listSemigroupKAssociativeProof[A](a: List[A], b: List[A], c: List[A]): Boolean = {
    listSemigroupKAssociative(a, b, c) because {
      a match {
        case Nil()       => trivial
        case Cons(x, xs) => listSemigroupKAssociativeProof(xs, b, c)
      }
    }
  }.holds

  def listMonoidKRightIdentityProof[B](b: List[B]): Boolean = {
    listMonoidKRightIdentity(b) because {
      b match {
        case Nil()       => trivial
        case Cons(x, xs) => listMonoidKRightIdentityProof(xs)
      }
    }
  }.holds

  def listMonadLeftIdentityProof[A, B](a: A, f: A => List[B]): Boolean = {
    listMonadLeftIdentity(a, f) because {
      listMonoidKRightIdentityProof(f(a))
    }
  }.holds

  def listMonadRightIdentityProof[A](fa: List[A]): Boolean = {
    listMonadRightIdentity(fa) because {
      fa match {
        case Cons(x, xs) => listMonadRightIdentityProof(xs)
        case Nil()       => trivial
      }
    }
  }.holds

  def listMonadAssociativityProof[A, B, C](
    fa: List[A],
    fb: List[B],
    fc: List[C],
    f: A => List[B],
    g: B => List[C]
  ): Boolean = {
    listMonadAssociativity(fa, f, g) because {
      (  combineK(combineK(fc, flatMap(fb)(g)), flatMap(fa)(x => flatMap(f(x))(g)))
      == combineK(fc, flatMap(combineK(fb, flatMap(fa)(f)))(g))) because {
        fc match {
          case Cons(_, xs) => listMonadAssociativityProof(fa, fb, xs, f, g)
          case Nil()       => fb match {
            case Cons(x, xs) => listMonadAssociativityProof(fa, xs, g(x), f, g)
            case Nil()       => fa match {
              case Cons(x, xs) => listMonadAssociativityProof(xs, f(x), Nil(), f, g)
              case Nil()       => trivial
            }
          }
        }
      }
    }
  }.holds
}
