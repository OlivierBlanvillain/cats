package cats

/**
 * Data structures that can be folded to a summary value.
 *
 * In the case of a collection (such as `List` or `Set`), these
 * methods will fold together (combine) the values contained in the
 * collection to produce a single result. Most collection types have
 * `foldLeft` methods, which will usually be used by the associated
 * `Foldable[_]` instance.
 *
 * Foldable[F] is implemented in terms of the basic method:
 *
 *  - `foldLeft(fa, b)(f)` eagerly folds `fa` from left-to-right.
 *
 * See: [[http://www.cs.nott.ac.uk/~pszgmh/fold.pdf A tutorial on the universality and expressiveness of fold]]
 */
trait Foldable[F[_]] {
  /**
   * Left associative fold on 'F' using the function 'f'.
   */
  def foldLeft[A, B](fa: F[A], b: B)(f: (B, A) => B): B
}
