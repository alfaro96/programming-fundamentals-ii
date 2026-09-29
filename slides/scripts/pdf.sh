#!/bin/sh
# Exports every unit to a PDF in production/class/ or production/print/, at the canvas size set in reveal-md.json5 (OUT replaces production, as site.sh does)
#
# mode: class (one page per step, highlights kept, as shown in class) or print (one page per slide, every step visible, no step highlights)

set -e

mode="$1"
case "$mode" in
  class) dir="${OUT:-production}/class"; separate="--revealOptions.pdfSeparateFragments" ;;
  print) dir="${OUT:-production}/print"; separate="" ;; # reveal-md.json5 already says false
  *) echo "$0: the mode is class or print" >&2; exit 1 ;;
esac

# Every page is exactly the canvas: width and height from reveal-md.json5
size=$(node scripts/config.mjs size)

# Chrome is Puppeteer's own, or the one PUPPETEER_EXECUTABLE_PATH names in .env if this machine needs it (kept out of Git)
if [ -f .env ]; then set -a; . ./.env; set +a; fi

# Chrome runs without its sandbox on GitHub Actions, which sets CI (the Pages workflow runs this script there)
launch=""
[ -n "$CI" ] && launch="--puppeteer-launch-args=--no-sandbox"

mkdir -p "$dir"
for f in content/unit_*.md; do
  out="$dir/$(basename "$f" .md).pdf"
  rm -f "$out"
  # Chrome now and then fails to start and reveal-md exits 0 anyway: retry
  for attempt in 1 2 3; do
    # Port 1959 so it runs next to npm run dev, which holds 1948
    npx --yes reveal-md "$f" --theme theme/custom.css --port 1959 \
      $separate $launch \
      --print-size "$size" --print "$out"
    [ -f "$out" ] && break
  done
  [ -f "$out" ] || { echo "Could not export $out" >&2; exit 1; }
done
