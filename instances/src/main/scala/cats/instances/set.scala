package cats
package instances

trait SetInstance {
  def empty[A]: A => Boolean =
    e => false

  def combineK[A](x: A => Boolean, y: A => Boolean): A => Boolean =
    e => x(e) || y(e)
}
