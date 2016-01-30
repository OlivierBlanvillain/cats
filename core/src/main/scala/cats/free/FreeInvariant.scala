// package cats
// package free

// import cats.arrow.NaturalTransformation
// import cats.data.Const

// /** Invariant Cartesian for Free */
// sealed abstract class FreeInvariant[F[_], A] extends Product with Serializable { self =>
//   import FreeInvariant.{FA, Zip, Imap, lift}
  
//   def imap[B](f: A => B)(g: B => A): FA[F, B] =
//     Imap(this, f, g)
  
//   def product[B](fb: FA[F, B]): FA[F, (A, B)] =
//     Zip(this, fb)
  
//   /** Interprets/Runs the sequence of operations using the semantics of `InvariantMonoidal[G]` */
//   def foldMap[G[_]](nt: NaturalTransformation[F, G])(implicit im: InvariantMonoidal[G]): G[A]
//   // Note that implementing a concrete `foldMap` here does not work because of
//   // `Zip extends G[(A, B)]`, which breaks the pattern matching on this.
  
//   /** Interpret/run the operations using the semantics of `InvariantMonoidal[F]`. */
//   final def fold(implicit F: InvariantMonoidal[F]): F[A] =
//     foldMap(NaturalTransformation.id[F])

//   /** Interpret this algebra into another InvariantMonoidal */
//   final def compile[G[_]](f: F ~> G): FA[G, A] =
//     foldMap[FA[G, ?]] {
//       new NaturalTransformation[F, FA[G, ?]] {
//         def apply[B](fa: F[B]): FA[G, B] = lift(f(fa))
//       }
//     }
// }

// object FreeInvariant {
//   type FA[F[_], A] = FreeInvariant[F, A]
  
//   private final case class Suspend[F[_], A](fa: F[A]) extends FA[F, A] {
//     def foldMap[G[_]](nt: NaturalTransformation[F, G])(implicit im: InvariantMonoidal[G]): G[A] =
//       nt(fa)
//   }
  
//   private final case class Zip[F[_], A, B](fa: FA[F, A], fb: FA[F, B]) extends FA[F, (A, B)] {
//     def foldMap[G[_]](nt: NaturalTransformation[F, G])(implicit im: InvariantMonoidal[G]): G[(A, B)] =
//       im.product(fa.foldMap(nt), fb.foldMap(nt))
//   }
  
//   private final case class Imap[F[_], A, B](fa: FA[F, A], f: A => B, g: B => A) extends FA[F, B] {
//     def foldMap[G[_]](nt: NaturalTransformation[F, G])(implicit im: InvariantMonoidal[G]): G[B] =
//       im.imap(fa.foldMap(nt))(f)(g)
//   }
  
//   def lift[F[_], A](fa: F[A]): FA[F, A] =
//     Suspend(fa)
  
//   /** `FreeInvariant[S, ?]` has a FreeInvariant for any type constructor `S[_]`. */
//   implicit def freeInvariant[S[_]]: InvariantMonoidal[FA[S, ?]] =
//     new InvariantMonoidal[FA[S, ?]] {
//        def imap[A, B](fa: FA[S, A])(f: A => B)(g: B => A): FA[S, B] = fa.imap(f)(g)
//        def product[A, B](fa: FA[S, A], fb: FA[S, B]): FA[S, (A, B)] = fa.product(fb)
//     }
// }
