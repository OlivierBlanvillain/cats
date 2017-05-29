package cats
package arrow

/**
  * `FunctionK[F[_], G[_]]` is a functor transformation from `F` to `G`
  * in the same manner that function `A => B` is a morphism from values
  * of type `A` to `B`.
  * An easy way to create a FunctionK instance is to use the Polymorphic
  * lambdas provided by non/kind-projector v0.9+. E.g.
  * {{{
  *   val listToOption = λ[FunctionK[List, Option]](_.headOption)
  * }}}
  */
trait FunctionK[F[_], G[_]] {

  /**
    * Applies this functor transformation from `F` to `G`
    */
  def apply[A](fa: F[A]): G[A]
}
