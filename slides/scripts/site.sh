#!/bin/sh
# Builds in production/site/ what GitHub Pages publishes: the slides, their two PDFs (class, a page per step, and print, a page per slide) and an index page

set -e

site="production/site"

rm -rf "$site"
npx --yes reveal-md content --theme theme/custom.css --static "$site"
OUT="$site/pdf" sh scripts/pdf.sh class
OUT="$site/pdf" sh scripts/pdf.sh print
node scripts/site-index.mjs content "$site"
