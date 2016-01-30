package cats
package tests

import cats.laws.discipline.{InvariantMonoidalTests, SerializableTests}
import cats.{Semigroup, Eq}
import org.scalacheck.{Arbitrary, Gen, Prop}
import scala.util.Try

object CSV {
  type CSV = Seq[String]
}
import CSV.CSV

/**
 * Type class to read and write objects of type A to CSV.
 *
 * Obeys `forAll { (c: CsvCodec[A], a: A) => c.read(c.writes(a)) == (Some(a), List())`,
 * under the assumtion that `imap(f, g)` is always called with `f` and `g` such that
 * `forAll { (a: A) => g(f(a)) == a }`.
 */
trait CsvCodec[A] extends Serializable { self =>
  
  /** Reads the first value of a CSV, returning an optional value of type `A` and the remaining CSV. */
  def read(s: CSV): (Option[A], CSV)

  /** Writes a value of type `A` to CSV format. */
  def write(a: A): CSV

  def product[AA](i: CsvCodec[AA]): CsvCodec[(A, AA)] =
    new CsvCodec[(A, AA)] {
      def read(s: CSV): (Option[(A, AA)], CSV) = {
        val (a1, s1) = self.read(s)
        val (a2, s2) = i.read(s1)
        val o = (a1, a2) match {
          case (Some(s1), Some(s2)) => Some((s1, s2))
          case _ => None
        }
        (o, s2)
      }

      def write(a: (A, AA)): CSV =
        self.write(a._1) ++ i.write(a._2)
    }

  def imap[AA](f: A => AA, g: AA => A): CsvCodec[AA] =
    new CsvCodec[AA] {
      def read(s: CSV): (Option[AA], CSV) = {
        val (a1, s1) = self.read(s)
        (a1.map(f), s1)
      }

      def write(a: AA): CSV =
        self.write(g(a))
    }
}

object CsvCodec {
  implicit val csvCodecIsInvariantMonoidal: InvariantMonoidal[CsvCodec] =
    new InvariantMonoidal[CsvCodec] {
      def pure[A](a: A): CsvCodec[A] = new CsvCodec[A] {
        def read(s: CSV): (Option[A], CSV) = (Some(a), s)
        def write(a: A): CSV = Seq.empty
      }
      def product[A, AA](fa: CsvCodec[A], fb: CsvCodec[AA]): CsvCodec[(A, AA)] = fa.product(fb)
      def imap[A, AA](fa: CsvCodec[A])(f: A => AA)(g: AA => A): CsvCodec[AA] = fa.imap(f, g)
    }
}

class CsvCodecInvariantMonoidalTests extends CatsSuite {
  type CSV = Seq[String]

  def numericSystemCodec(base: Int): CsvCodec[Int] = 
    new CsvCodec[Int] {
      def read(s: CSV): (Option[Int], CSV) =
        (s.headOption.flatMap(head => Try(Integer.parseInt(head, base)).toOption), s.drop(1))

      def write(a: Int): CSV =
        Seq(Integer.toString(a, base))
    }

  implicit val arbNumericSystemCodec: Arbitrary[CsvCodec[Int]] =
    Arbitrary(Gen.choose(2, 16).map(numericSystemCodec))

  implicit val csvCodecsEqUsingExamples: Eq[CsvCodec[Int]] =
    new Eq[CsvCodec[Int]] {
      def eqv(x: CsvCodec[Int], y: CsvCodec[Int]): Boolean = {
        (0 to 22).forall { (i: Int) =>
          x.write(i) == y.write(i) && x.read(x.write(i)) == y.read(y.write(i))
        }
      }
    }

  implicit val csvCodecsEqUsingExamplesT: Eq[CsvCodec[(Int, Int, Int)]] =
    new Eq[CsvCodec[(Int, Int, Int)]] {
      def eqv(x: CsvCodec[(Int, Int, Int)], y: CsvCodec[(Int, Int, Int)]): Boolean = {
        (for { i <- 0 to 1; j <- 0 to 2; k <- 0 to 4 } yield { (i, j, k) })
          .forall { (p: (Int, Int, Int)) =>
            x.write(p) == y.write(p) && x.read(x.write(p)) == y.read(y.write(p))
          }
      }
    }
    
  implicit val csvCodecsEqUsingExamplesPP: Eq[CsvCodec[(Int, (Int, Int))]] =
    new Eq[CsvCodec[(Int, (Int, Int))]] {
      def eqv(x: CsvCodec[(Int, (Int, Int))], y: CsvCodec[(Int, (Int, Int))]): Boolean = {
        (for { i <- 0 to 1; j <- 0 to 2; k <- 0 to 4 } yield { (i, (j, k)) })
          .forall { (p: (Int, (Int, Int))) =>
            x.write(p) == y.write(p) && x.read(x.write(p)) == y.read(y.write(p))
          }
      }
    }

  checkAll("InvariantMonoidal[CsvCodec]",
    InvariantMonoidalTests[CsvCodec].invariantMonoidal[Int, Int, Int])

  checkAll("InvariantMonoidal[CsvCodec]",
    SerializableTests.serializable(InvariantMonoidal[CsvCodec]))
}
