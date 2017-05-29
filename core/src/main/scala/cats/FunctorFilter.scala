package cats

trait FunctorFilter[F[_]] extends Functor[F] {

  /**
   * A combined [[map]] and [[filter]]. Filtering is handled via `Option`
   * instead of `Boolean` such that the output type `B` can be different than
   * the input type `A`.
   *
   * Example:
   * {{{
   * scala> import cats.implicits._
   * scala> val m: Map[Int, String] = Map(1 -> "one", 3 -> "three")
   * scala> val l: List[Int] = List(1, 2, 3, 4)
   * scala> def asString(i: Int): Option[String] = m.get(i)
   * scala> l.mapFilter(i => m.get(i))
   * res0: List[String] = List(one, three)
   * }}}
   */
  def mapFilter[A, B](fa: F[A])(f: A => Option[B]): F[B]

  /**
   * "Flatten" out a structure by collapsing `Option`s.
   *
   * Example:
   * {{{
   * scala> import cats.implicits._
   * scala> val l: List[Option[Int]] = List(Some(1), None, Some(3), None)
   * scala> l.flattenOption
   * res0: List[Int] = List(1, 3)
   * }}}
   */
  def flattenOption[A](fa: F[Option[A]]): F[A] = mapFilter(fa)(x => x)
}
