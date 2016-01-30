---
layout: default
title:  "InvariantMonoidal"
section: "typeclasses"
source: "https://github.com/non/cats/blob/master/core/src/main/scala/cats/InvariantMonoi dal.scala"
scaladoc: "#cats.InvariantMonoidal"
---
# Invariant Monoidal

`InvariantMonoidal` combines the [`Invariant`](invariant.html) type class with the [`Monoidal`](monoidal.html) type class with no additional methods. As such, it provides both `imap` and `product` functions with the following signature:

```scala
def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]
def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
```

# Examples

# Monoid & Semigroup

[`Invariant`](invariant.html)

# Codecs
