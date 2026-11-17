#!/bin/sh
# Builds the statement of every exercise and laboratory assignment in ../code/ with latexmk and XeLaTeX, from
# the LaTeX that /latex writes in each task's latex/ folder: production/exercises/<unit>/<N>.pdf and
# production/assignments/<unit>/<N>.pdf, numbered from 0 across the course, in the order of the units and lessons

set -e

root="$(pwd)"
assignment=0

for main in beamer/units/*/main.tex; do
  [ -f "$main" ] || continue
  unit=$(basename "$(dirname "$main")")
  number=${unit#unit_}
  number=${number%%-*}
  # The lesson in ../code/ is the unit's cover title without "Unit N: "
  name=$(sed -n 's/^\\title{Unit [0-9]*: *\(.*\)}$/\1/p' "$main")
  lesson="../code/$name"
  [ -f "$lesson/lesson-info.yaml" ] || continue
  # The lesson's tasks, in its order: the lines after content: that start with "- "
  sed -n '/^content:/,/^[^ -]/s/^ *- *"\{0,1\}\([^"]*\)"\{0,1\}$/\1/p' "$lesson/lesson-info.yaml" |
  while IFS= read -r task; do
    dir="$lesson/$task/latex"
    if [ -f "$dir/goals.tex" ]; then
      kind=assignment; n=$assignment
    else
      n=${task#Exercise }
      [ -f "$dir/$n.tex" ] || continue
      kind=exercise
    fi
    job="$unit-$kind-$n"
    # Copied to a path with no spaces, which \input cannot take
    work="production/aux/statements/$job"
    rm -rf "$work"
    mkdir -p "$work" "production/${kind}s/$unit"
    if [ "$kind" = exercise ]; then cp "$dir/$n.tex" "$work/body.tex"; else cp "$dir"/*.tex "$work"; fi
    pretex="\\def\\statementdir{../$work}\\def\\statementunit{$number}\\def\\statementunitname{$name}\\def\\statementnumber{$n}"
    (
      cd statements
      latexmk -xelatex -interaction=nonstopmode -halt-on-error -silent \
        -jobname="$n" "-usepretex=$pretex" \
        -outdir="$root/production/${kind}s/$unit" -auxdir="$root/$work" "$kind.tex"
    )
  done
  # The assignments go on counting across units: the loop above runs in a subshell
  for task in "$lesson"/*/latex/goals.tex; do [ -f "$task" ] && assignment=$((assignment + 1)); done
done
