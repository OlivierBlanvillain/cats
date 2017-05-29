package cats
package instances

import stainless.lang._

trait RealInstance {
  def combine(x: Real, y: Real): Real = x + y
  def empty: Real = Real(0)
  def inverse(x: Real): Real = -x
  def remove(x: Real, y: Real): Real = x - y

  def compare(f1: Real, f2: Real): Int =
         if (f1 < f2) -1
    else if (f1 > f2) 1
    else 0
}
