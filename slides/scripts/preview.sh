#!/bin/sh
# Renders the pages of the PDFs (npm run pdf) to PNG, to look at them without a PDF viewer:
# production/preview/<version>/<unit>/page-NNN.png
#
# version: class (default) or print; unit: one unit's folder name, or every unit if left out

set -e

version="${1:-class}"
case "$version" in
  class | print) ;;
  *) echo "$0: the version is class or print" >&2; exit 1 ;;
esac

for pdf in production/"$version"/*.pdf; do
  [ -f "$pdf" ] || continue
  unit=$(basename "$pdf" .pdf)
  [ -n "$2" ] && [ "$2" != "$unit" ] && continue
  out="production/preview/$version/$unit"
  rm -rf "$out"
  mkdir -p "$out"
  pdftoppm -r 100 -png "$pdf" "$out/page"
  echo "$out"
done
