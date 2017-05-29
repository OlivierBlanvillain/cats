package cats

/**
 * `TraverseFilter`, also known as `Witherable`, represents list-like structures
 * that can essentially have a [[traverse]] and a [[filter]] applied as a single
 * combined operation ([[traverseFilter]]).
 *
 * Must obey the laws defined in cats.laws.TraverseFilterLaws.
 *
 * Based on Haskell's [[https://hackage.haskell.org/package/witherable-0.1.3.3/docs/Data-Witherable.html Data.Witherable]]
 */
trait TraverseFilter[F[_]] extends Traverse[F] with FunctorFilter[F] {

  /**
   * A combined [[traverse]] and [[filter]]. Filtering is handled via `Option`
   * instead of `Boolean` such that the output type `B` can be different than
   * the input type `A`.
   *
   * Example:
   * {{{
   * scala> import cats.implicits._
   * scala> val m: Map[Int, String] = Map(1 -> "one", 3 -> "three")
   * scala> val l: List[Int] = List(1, 2, 3, 4)
   * scala> def asString(i: Int): Eval[Option[String]] = Now(m.get(i))
   * scala> val result: Eval[List[String]] = l.traverseFilter(asString)
   * scala> result.value
   * res0: List[String] = List(one, three)
   * }}}
   */
  def traverseFilter[G[_]: Applicative, A, B](fa: F[A])(f: A => G[Option[B]]): G[F[B]]
}
