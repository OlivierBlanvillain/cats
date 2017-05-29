package cats
package instances

trait Function1MonadReaderInstance[X] {
  def pure[R](r: R): X => R = x => r
  val ask: X => X = x => x

  def flatMap[A, B](fa: X => A)(f: A => X => B): X => B =
    t => f(fa(t))(t)

  def map[A, B](fa: X => A)(f: A => B): X => B =
    x => f(fa(x))

  def local[A](f: X => X)(fa: X => A): X => A =
    x => fa(f(x))

  def reader[A](f: X => A): X => A =
    map(ask)(f)
}

trait Function1ContravariantInstance[X] {
  def contramap[A, B](fa: A => X)(f: B => A): B => X =
    b => fa(f(b))
}

trait Function0BimonadInstance {
  def extract[A](x: () => A): A = x()
  def pure[A](x: A): () => A = () => x

  def map[A, B](fa: () => A)(f: A => B): () => B =
    () => f(fa())

  def flatMap[A, B](fa: () => A)(f: A => () => B): () => B =
    () => f(fa())()

  def coflatten[A](fa: () => A): () => (() => A) =
    coflatMap(fa)(fa => fa)

  def coflatMap[A, B](fa: () => A)(f: (() => A) => B): () => B =
    () => f(fa)
}
