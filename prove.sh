#!/bin/sh

set -eu # x

sources=$(git ls-files | grep "scala" | grep -v "package.scala" | xargs)
# sources=$(\
#   find              \
#     proofs          \
#     instances       \
#     -name '*.scala' \
#     -prune -not -name 'package.scala' |\
#      xargs)

laws=$(\
  find proofs -name '*.scala'   |\
    xargs cat                   |\
    grep -Eo 'def [[:alnum:]]+' |\
    cut -d' ' -f2               |\
    paste -sd",")

run="stainless-prover/bin/stainless-scalac \
  $sources          \
  --functions=$laws \
  --verification    \
  --termination"

echo $run
time $run || ((cd stainless-prover; sbt compile) && time $run)
