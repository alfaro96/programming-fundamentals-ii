#!/bin/sh
# Screenshots units into production/preview/<mode>/<unit>/ to check how they look
#
# mode: screen (every slide with all its steps, the default), print or class (every PDF page)
# unit: the unit file name without .md; every unit if left out

set -e

# Chrome is Puppeteer's own, or the one PUPPETEER_EXECUTABLE_PATH names in .env (not in Git)
if [ -f .env ]; then set -a; . ./.env; set +a; fi

mode="${1:-screen}"
unit="$2"
case "$mode" in
  screen|print|class) ;;
  *) echo "$0: the mode is screen, print or class" >&2; exit 1 ;;
esac

# The static site the screenshots are taken from
site="production/preview/site"
rm -rf "$site"
npx --yes reveal-md content --theme theme/custom.css --static "$site" > /dev/null

# Puppeteer comes with reveal-md, in the npx cache
PUPPETEER_DIR=$(ls -d "$HOME"/.npm/_npx/*/node_modules/puppeteer 2> /dev/null | head -n 1)
[ -n "$PUPPETEER_DIR" ] || { echo "Puppeteer not found: run npm run pdf once first" >&2; exit 1; }
export PUPPETEER_DIR

for f in content/unit_*.md; do
  base=$(basename "$f" .md)
  [ -n "$unit" ] && [ "$unit" != "$base" ] && continue
  out="production/preview/$mode/$base"
  rm -rf "$out"
  mkdir -p "$out"
  node scripts/preview.mjs "$site/$base.html" "$out" "$mode"
  echo "$out: $(ls "$out" | wc -l | tr -d ' ') images"
done
