---
description: Adjust the global visual style (colors, background, typography) of all slides
---

The instructor wants to change the global visual style of the slides. Request:

$ARGUMENTS

1. Edit ONLY `theme/custom.css`. Within that file, change only the CSS variables in `:root`; don't touch structural rules (layout, sizing, columns) unless the request is explicitly about those.
2. Don't touch anything inside `content/`: the styling must stay centralized in one place.
3. Reach for the palette in `:root` first. When a color the palette lacks is really needed, define one that fits with it as a new named variable, never a loose hex in a rule, and keep the same color for the same role across slides.
4. If the request is ambiguous (e.g. "make it look more professional" with no further detail), propose 2-3 concrete options built from the palette before applying any, and wait for the instructor to pick one.
5. Check the result with `npm run preview` on screen and in the print version.
6. When done, summarize which variables you changed or added and to what values, as a short list.
