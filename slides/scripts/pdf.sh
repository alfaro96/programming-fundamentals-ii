#!/bin/sh
# Exports every unit to a PDF in production/<mode>/, at the canvas size in reveal-md.json5
#
# mode: class (a page per step, as shown in class) or print (a page per slide, every step visible)

set -e

mode="$1"
case "$mode" in
  class) dir="production/class"; separate="--revealOptions.pdfSeparateFragments" ;;
  print) dir="production/print"; separate="" ;; # reveal-md.json5 already says false
  *) echo "$0: the mode is class or print" >&2; exit 1 ;;
esac

# Every page is exactly the canvas: width and height from reveal-md.json5
size=$(node scripts/config.mjs size)

# Chrome is Puppeteer's own, or the one PUPPETEER_EXECUTABLE_PATH names in .env (not in Git)
if [ -f .env ]; then set -a; . ./.env; set +a; fi

mkdir -p "$dir"
for f in content/unit_*.md; do
  out="$dir/$(basename "$f" .md).pdf"
  rm -f "$out"
  # Chrome now and then fails to start and reveal-md exits 0 anyway: retry
  for attempt in 1 2 3; do
    # Port 1959 so it runs next to npm run dev, which holds 1948
    npx --yes reveal-md "$f" --theme theme/custom.css --port 1959 \
      $separate \
      --print-size "$size" --print "$out"
    [ -f "$out" ] && break
  done
  [ -f "$out" ] || { echo "Could not export $out" >&2; exit 1; }
done
