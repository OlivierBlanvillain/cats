package cats
package instances

trait BigIntInstance {
  def combine(x: Int, y: Int): Int = x + y
  def empty: Int = 0
  def inverse(x: Int): Int = -x
  def remove(x: Int, y: Int): Int = x - y

  def compare(f1: Int, f2: Int): Int =
         if (f1 < f2) -1
    else if (f1 > f2) 1
    else 0
}
