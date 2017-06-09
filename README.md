# Cats + Stainless = ♥

[Report](https://github.com/OlivierBlanvillain/cats/blob/proofs/report/proving-the-cats-library.pdf)

[Presentation](https://docs.google.com/presentation/d/1RtMkQ_N623lF0iZHY7Wpo0RL-Pd8bdn-TV6JuniKdOU)

```bash
$ git clone --recursive --branch proofs git@github.com:OlivierBlanvillain/cats.git
$ sbt prove

$ # Go grap a coffee, this ^ takes more than 16 minutes to run on my laptop.
$ # See https://github.com/epfl-lara/stainless/issues/38.
```

```scala
  ┌──────────────────────┐
╔═╡ Verification Summary ╞═══════════════════════════════════════════════════════════════════╗
║ └──────────────────────┘                                                                   ║
║ bigintCommutativeGroup              postcondition         ?:?  valid    nativez3  0.009    ║
║ bigintMonoidLeftIdentity            postcondition         ?:?  valid    nativez3  0.340    ║
║ bigintMonoidRightIdentity           postcondition         ?:?  valid    nativez3  0.023    ║
║ bigintOrderAntisymmetry             postcondition         ?:?  valid    nativez3  0.022    ║
║ bigintOrderTotality                 postcondition         ?:?  valid    nativez3  0.023    ║
║ bigintOrderTransitivity             postcondition         ?:?  valid    nativez3  0.097    ║
║ bigintSemigroupAssociative          postcondition         ?:?  valid    nativez3  0.462    ║
║ contravariantComposition            postcondition         ?:?  valid    nativez3  0.282    ║
║ contravariantIdentity               postcondition         ?:?  valid    nativez3  0.190    ║
║ eitherMonadAssociativity            postcondition         ?:?  valid    nativez3  0.067    ║
║ eitherMonadLeftIdentity             postcondition         ?:?  valid    nativez3  0.037    ║
║ eitherMonadRightIdentity            postcondition         ?:?  valid    nativez3  0.033    ║
║ eitherOrderAntisymmetry             postcondition         ?:?  valid    nativez3  0.064    ║
║ eitherOrderTotality                 postcondition         ?:?  valid    nativez3  0.017    ║
║ eitherOrderTransitivity             postcondition         ?:?  valid    nativez3  0.035    ║
║ eitherSemigroupKAssociative         postcondition         ?:?  valid    nativez3  0.011    ║
║ function0CoflatMapAssociativity     postcondition         ?:?  valid    nativez3  0.071    ║
║ function0CoflatMapIdentity          postcondition         ?:?  valid    nativez3  0.052    ║
║ function0CoflattenCoherence         postcondition         ?:?  valid    nativez3  0.046    ║
║ function0CoflattenThroughMap        postcondition         ?:?  valid    nativez3  0.116    ║
║ function0MonadAssociativity         postcondition         ?:?  valid    nativez3  0.120    ║
║ function0MonadLeftIdentity          postcondition         ?:?  valid    nativez3  0.053    ║
║ function0MonadRightIdentity         postcondition         ?:?  valid    nativez3  0.063    ║
║ function1MonadReaderReaderAsk       postcondition         ?:?  valid    nativez3  0.039    ║
║ listCoflatMapAssociativityProof     postcondition         ?:?  valid    nativez3  0.175    ║
║ listCoflatMapIdentity               postcondition         ?:?  valid    nativez3  0.027    ║
║ listCoflattenCoherenceProof         postcondition         ?:?  valid    nativez3  0.075    ║
║ listCoflattenThroughMapProof        postcondition         ?:?  valid    nativez3  0.101    ║
║ listMonadAssociativityProof         postcondition         ?:?  valid    nativez3  0.583    ║
║ listMonadLeftIdentityProof          postcondition         ?:?  valid    nativez3  0.093    ║
║ listMonadRightIdentityProof         postcondition         ?:?  valid    nativez3  0.070    ║
║ listMonoidKLeftIdentity             postcondition         ?:?  valid    nativez3  0.034    ║
║ listMonoidKRightIdentityProof       postcondition         ?:?  valid    nativez3  0.031    ║
║ listOrderAntisymmetryProof          postcondition         ?:?  valid    nativez3  0.072    ║
║ listOrderTotalityProof              postcondition         ?:?  valid    nativez3  0.119    ║
║ listOrderTransitivityProof          postcondition         ?:?  valid    nativez3  0.209    ║
║ listSemigroupKAssociativeProof      postcondition         ?:?  valid    nativez3  0.061    ║
║ optionCoflatMapAssociativity        postcondition         ?:?  valid    nativez3  0.075    ║
║ optionCoflatMapIdentity             postcondition         ?:?  valid    nativez3  0.032    ║
║ optionCoflattenCoherence            postcondition         ?:?  valid    nativez3  0.041    ║
║ optionCoflattenThroughMap           postcondition         ?:?  valid    nativez3  0.067    ║
║ optionMonadAssociativity            postcondition         ?:?  valid    nativez3  0.060    ║
║ optionMonadLeftIdentity             postcondition         ?:?  valid    nativez3  0.021    ║
║ optionMonadRightIdentity            postcondition         ?:?  valid    nativez3  0.022    ║
║ optionMonoidKLeftIdentity           postcondition         ?:?  valid    nativez3  0.013    ║
║ optionMonoidKRightIdentity          postcondition         ?:?  valid    nativez3  0.018    ║
║ optionOrderAntisymmetry             postcondition         ?:?  valid    nativez3  0.021    ║
║ optionOrderTotality                 postcondition         ?:?  valid    nativez3  0.028    ║
║ optionOrderTransitivity             postcondition         ?:?  valid    nativez3  0.031    ║
║ optionSemigroupKAssociative         postcondition         ?:?  valid    nativez3  0.021    ║
║ realCommutativeGroup                postcondition         ?:?  valid    nativez3  0.014    ║
║ realMonoidLeftIdentity              postcondition         ?:?  valid    nativez3  0.020    ║
║ realMonoidRightIdentity             postcondition         ?:?  valid    nativez3  0.023    ║
║ realOrderAntisymmetry               postcondition         ?:?  valid    nativez3  0.026    ║
║ realOrderTotality                   postcondition         ?:?  valid    nativez3  0.025    ║
║ realOrderTransitivity               postcondition         ?:?  valid    nativez3  0.017    ║
║ realSemigroupAssociative            postcondition         ?:?  valid    nativez3  0.015    ║
║ setMonoidKLeftIdentity              postcondition         ?:?  valid    nativez3  0.080    ║
║ setMonoidKRightIdentity             postcondition         ?:?  valid    nativez3  0.053    ║
║ setSemigroupKAssociative            postcondition         ?:?  valid    nativez3  0.095    ║
╟--------------------------------------------------------------------------------------------╢
║ total: 60     valid: 60     invalid: 0      unknown: 0                              10.497 ║
╚════════════════════════════════════════════════════════════════════════════════════════════╝
```

```
  ┌─────────────────────┐
╔═╡ Termination Summary ╞══════════════════════════════════════════════════════════════════════╗
║ └─────────────────────┘                                                                      ║
║ contravariantComposition            ✓ Terminates (Non-recursive)                             ║
║ optionCoflattenCoherence            ✓ Terminates (Non-recursive)                             ║
║ listOrderTransitivityProof          ✓ Terminates (comparing sum of argument sizes)           ║
║ listCoflatMapAssociativity          ✓ Terminates (Non-recursive)                             ║
║ realOrderAntisymmetry               ✓ Terminates (Non-recursive)                             ║
║ eitherOrderTransitivity             ✓ Terminates (Non-recursive)                             ║
║ optionMonadRightIdentity            ✓ Terminates (Non-recursive)                             ║
║ optionOrderAntisymmetry             ✓ Terminates (Non-recursive)                             ║
║ function1MonadLeftIdentityProof     ✓ Terminates (Non-recursive)                             ║
║ listSemigroupKAssociative           ✓ Terminates (Non-recursive)                             ║
║ realMonoidLeftIdentity              ✓ Terminates (Non-recursive)                             ║
║ function0MonadRightIdentity         ✓ Terminates (Non-recursive)                             ║
║ realMonoidRightIdentity             ✓ Terminates (Non-recursive)                             ║
║ optionCoflatMapAssociativity        ✓ Terminates (Non-recursive)                             ║
║ setSemigroupKAssociative            ✓ Terminates (Non-recursive)                             ║
║ setMonoidKLeftIdentity              ✓ Terminates (Non-recursive)                             ║
║ function1MonadRightIdentityProof    ✓ Terminates (Non-recursive)                             ║
║ listOrderAntisymmetry               ✓ Terminates (Non-recursive)                             ║
║ function0MonadAssociativity         ✓ Terminates (Non-recursive)                             ║
║ listMonadAssociativityProof         ✓ Terminates (comparing argument lists lexicographically ║
║ optionSemigroupKAssociative         ✓ Terminates (Non-recursive)                             ║
║ realSemigroupAssociative            ✓ Terminates (Non-recursive)                             ║
║ listOrderAntisymmetryProof          ✓ Terminates (comparing sum of argument sizes)           ║
║ eitherMonadAssociativity            ✓ Terminates (Non-recursive)                             ║
║ optionMonadAssociativity            ✓ Terminates (Non-recursive)                             ║
║ realOrderTotality                   ✓ Terminates (Non-recursive)                             ║
║ eitherMonadLeftIdentity             ✓ Terminates (Non-recursive)                             ║
║ eitherOrderAntisymmetry             ✓ Terminates (Non-recursive)                             ║
║ function0CoflatMapAssociativity     ✓ Terminates (Non-recursive)                             ║
║ function0CoflatMapIdentity          ✓ Terminates (Non-recursive)                             ║
║ function1MonadReaderLocalPureProof  ✓ Terminates (Non-recursive)                             ║
║ realCommutativeGroup                ✓ Terminates (Non-recursive)                             ║
║ function0MonadLeftIdentity          ✓ Terminates (Non-recursive)                             ║
║ eitherMonadRightIdentity            ✓ Terminates (Non-recursive)                             ║
║ eitherOrderTotality                 ✓ Terminates (Non-recursive)                             ║
║ function1MonadLeftIdentity          ✓ Terminates (Non-recursive)                             ║
║ bigintOrderTransitivity             ✓ Terminates (Non-recursive)                             ║
║ function0CoflattenThroughMap        ✓ Terminates (Non-recursive)                             ║
║ listCoflattenThroughMap             ✓ Terminates (Non-recursive)                             ║
║ optionMonoidKLeftIdentity           ✓ Terminates (Non-recursive)                             ║
║ function1MonadReaderLocalAskProof   ✓ Terminates (Non-recursive)                             ║
║ listMonoidKRightIdentity            ✓ Terminates (Non-recursive)                             ║
║ function1MonadRightIdentity         ✓ Terminates (Non-recursive)                             ║
║ optionOrderTransitivity             ✓ Terminates (Non-recursive)                             ║
║ function0CoflattenCoherence         ✓ Terminates (Non-recursive)                             ║
║ listSemigroupKAssociativeProof      ✓ Terminates (comparing sum of argument sizes)           ║
║ listMonoidKLeftIdentity             ✓ Terminates (Non-recursive)                             ║
║ listMonadLeftIdentity               ✓ Terminates (Non-recursive)                             ║
║ listMonoidKRightIdentityProof       ✓ Terminates (comparing sum of argument sizes)           ║
║ listOrderTotalityProof              ✓ Terminates (comparing sum of argument sizes)           ║
║ listOrderTotality                   ✓ Terminates (Non-recursive)                             ║
║ function1MonadReaderReaderAsk       ✓ Terminates (Non-recursive)                             ║
║ listCoflatMapAssociativityProof     ✓ Terminates (comparing sum of argument sizes)           ║
║ function1MonadAssociativity         ✓ Terminates (Non-recursive)                             ║
║ bigintCommutativeGroup              ✓ Terminates (Non-recursive)                             ║
║ listMonadRightIdentity              ✓ Terminates (Non-recursive)                             ║
║ eitherSemigroupKAssociative         ✓ Terminates (Non-recursive)                             ║
║ optionCoflatMapIdentity             ✓ Terminates (Non-recursive)                             ║
║ function1MonadAssociativityProof    ✓ Terminates (Non-recursive)                             ║
║ bigintSemigroupAssociative          ✓ Terminates (Non-recursive)                             ║
║ listCoflattenCoherenceProof         ✓ Terminates (comparing sum of argument sizes)           ║
║ function1MonadReaderLocalAsk        ✓ Terminates (Non-recursive)                             ║
║ listMonadRightIdentityProof         ✓ Terminates (comparing sum of argument sizes)           ║
║ optionOrderTotality                 ✓ Terminates (Non-recursive)                             ║
║ bigintMonoidRightIdentity           ✓ Terminates (Non-recursive)                             ║
║ listOrderTransitivity               ✓ Terminates (Non-recursive)                             ║
║ listCoflattenCoherence              ✓ Terminates (Non-recursive)                             ║
║ listMonadAssociativity              ✓ Terminates (Non-recursive)                             ║
║ bigintOrderTotality                 ✓ Terminates (Non-recursive)                             ║
║ optionMonadLeftIdentity             ✓ Terminates (Non-recursive)                             ║
║ optionMonoidKRightIdentity          ✓ Terminates (Non-recursive)                             ║
║ contravariantIdentity               ✓ Terminates (Non-recursive)                             ║
║ listCoflatMapIdentity               ✓ Terminates (Non-recursive)                             ║
║ optionCoflattenThroughMap           ✓ Terminates (Non-recursive)                             ║
║ bigintOrderAntisymmetry             ✓ Terminates (Non-recursive)                             ║
║ setMonoidKRightIdentity             ✓ Terminates (Non-recursive)                             ║
║ realOrderTransitivity               ✓ Terminates (Non-recursive)                             ║
║ listCoflattenThroughMapProof        ✓ Terminates (comparing sum of argument sizes)           ║
║ bigintMonoidLeftIdentity            ✓ Terminates (Non-recursive)                             ║
║ function1MonadReaderLocalPure       ✓ Terminates (Non-recursive)                             ║
║ contravariantCompositionProof       ✓ Terminates (Non-recursive)                             ║
║ listMonadLeftIdentityProof          ✓ Terminates (Non-recursive)                             ║
╟----------------------------------------------------------------------------------------------╢
║ Analysis time:  16.536                                                                       ║
╚══════════════════════════════════════════════════════════════════════════════════════════════╝
```
