#!/bin/sh
# Builds every beamer unit (beamer/units/<unit>/main.tex) with latexmk and XeLaTeX into the two PDFs,
# named after the unit's folder: production/class (16:9, a page per step, as shown in class) and
# production/print (16:9 too, a page per slide with every step visible, to print or study)

set -e

root="$(pwd)"
mkdir -p production/class production/print

for f in beamer/units/*/main.tex; do
  [ -f "$f" ] || continue
  dir=$(dirname "$f")
  name=$(basename "$dir")
  (
    # The units find the shared files in beamer/, from their own folder
    cd "$dir"
    for version in class print; do
      # print sets \deckoptions before main.tex, so \documentclass gets handout
      pretex=""
      [ "$version" = print ] && pretex='\def\deckoptions{aspectratio=169, handout}'
      # The auxiliary files apart from the PDFs, kept so the next build only reruns what changed
      latexmk -xelatex -interaction=nonstopmode -halt-on-error -silent \
        -jobname="$name" ${pretex:+"-usepretex=$pretex"} \
        -outdir="$root/production/$version" -auxdir="$root/production/aux/$version" main.tex
    done
  )
done
